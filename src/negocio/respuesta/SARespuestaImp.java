package negocio.respuesta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import negocio.EntityManagerUtil;
import negocio.pregunta.Pregunta;

public class SARespuestaImp implements SARespuesta {

	@Override
	public int create(Respuesta respuesta) {
		int id = 1;
		if (respuesta != null) {
			EntityManager entitymanager = EntityManagerUtil.getEntityManager();
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			entitytransaction.begin();
			int pregunta_id = respuesta.getPregunta().getId();

			Pregunta preguntaResult = entitymanager.find(Pregunta.class, pregunta_id);
			respuesta.setPregunta(preguntaResult);
			entitymanager.persist(respuesta);
			entitytransaction.commit();
			id = respuesta.getId();

			entitymanager.close();
		}
		return id;
	}

	@Override
	public Respuesta read(int id) {
		Respuesta respuesta = null;
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();
		respuesta = entitymanager.find(Respuesta.class, id);
		if (respuesta == null)
			entitytransaction.rollback();
		else
			entitytransaction.commit();
		entitymanager.close();
		return respuesta;
	}

	@Override
	public List<Respuesta> readAll() {
		List<Respuesta> lista = null;
		
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();

		TypedQuery<Respuesta> query = entitymanager.createNamedQuery("negocio.respuesta.Respuesta.readAll",
				Respuesta.class);
		lista = query.getResultList();
		entitytransaction.commit();

		entitymanager.close();

		return lista;
	}

	@Override
	public int update(Respuesta respuesta) {
		int id = -1;
		
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();

		Respuesta respuestaResult = entitymanager.find(Respuesta.class, respuesta.getId());
		if (respuesta.getActivo()) {
			respuestaResult.setTitulo(respuestaResult.getTitulo());
			respuestaResult.setActivo(respuestaResult.getActivo());
			entitytransaction.commit();
			id = 1;
		} else {
			entitytransaction.rollback();
		}
		return id;
	}

	@Override
	public int delete(int id) {
		int id_res = -1;
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();
		Respuesta respuesta = entitymanager.find(Respuesta.class, id);
		if (respuesta != null) {
			if (respuesta.getActivo()) {

				respuesta.setActivo(false);

				entitytransaction.commit();
				id_res = 1;
			} else {
				entitytransaction.rollback();
			}

		} else {
			entitytransaction.rollback();
		}
		entitymanager.close();
		return id_res;
	}

}
