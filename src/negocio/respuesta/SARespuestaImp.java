package negocio.respuesta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import negocio.pregunta.Pregunta;

public class SARespuestaImp implements SARespuesta {

	@Override
	public int create(Respuesta respuesta) {
		int id = 1;
		if (respuesta != null) {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("");
			try {
				EntityManager entitymanager = emfactory.createEntityManager();
				EntityTransaction entitytransaction = entitymanager.getTransaction();
				entitytransaction.begin();
				TypedQuery<Respuesta> query = entitymanager
						.createNamedQuery("negocio.respuesta.respuesta.findBytitulo", Respuesta.class)
						.setParameter("titulo", respuesta.getTitulo());
				List<Respuesta> lista = query.getResultList();
				if (lista.isEmpty()) {
					entitymanager.persist(respuesta);
					entitytransaction.commit();
					id = respuesta.getId();
				} else {
					if (!lista.get(0).getActivo()) {
						Respuesta respuestaResult = lista.get(0);
						respuestaResult.setActivo(true);
						entitytransaction.commit();
						id = 1;
					} else {
						entitytransaction.rollback();
						id = -1;
					}

				}
				entitymanager.close();
				emfactory.close();
			} catch (PersistenceException ex) {
				id = -1;
			}
		}
		return id;
	}

	@Override
	public Respuesta read(int id) {
		Respuesta respuesta = null;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("");
		try {
			EntityManager entitymanager = emfactory.createEntityManager();
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			entitytransaction.begin();
			respuesta = entitymanager.find(Respuesta.class, id);
			if (respuesta == null)
				entitytransaction.rollback();
			else
				entitytransaction.commit();
			entitymanager.close();
			emfactory.close();
		} catch (PersistenceException ex) {
		}
		return respuesta;
	}

	@Override
	public List<Respuesta> readAll() {
		List<Respuesta> lista = null;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("");

		EntityManager entitymanager = emfactory.createEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();

		TypedQuery<Respuesta> query = entitymanager.createNamedQuery("negocio.respuesta.Respuesta.readAll", Respuesta.class);
		lista = query.getResultList();
		entitytransaction.commit();

		entitymanager.close();
		emfactory.close();

		return lista;
	}

	@Override
	public int update(Respuesta respuesta) {
		int id = -1;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("");

		EntityManager entitymanager = emfactory.createEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();
		
		Respuesta respuestaResult = entitymanager.find(Respuesta.class, respuesta.getId());
		if(respuesta.getActivo()) {
			respuestaResult.setTitulo(respuestaResult.getTitulo());
			respuestaResult.setActivo(respuestaResult.getActivo());
			entitytransaction.commit();
			id = 1;
		}
		else {
			entitytransaction.rollback();
		}
		return id;
	}

	@Override
	public int delete(int id) {
		int id_res = -1;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("");
		try {
			EntityManager entitymanager = emfactory.createEntityManager();
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
			emfactory.close();
		} catch (PersistenceException ex) {
			id = -100;
		}
		return id_res;
	}

}
