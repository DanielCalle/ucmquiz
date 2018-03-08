package negocio.pregunta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import negocio.EntityManagerUtil;



public class SAPreguntaImp implements SAPregunta{

	@Override
	public int create(Pregunta pregunta) {
		int id = 1;
		if (pregunta != null) {
			EntityManager entitymanager = EntityManagerUtil.getEntityManager();
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			entitytransaction.begin();
			TypedQuery<Pregunta> query = entitymanager
					.createNamedQuery("negocio.pregunta.Pregunta.findBytitulo", Pregunta.class)
					.setParameter("titulo", pregunta.getTitulo());
			List<Pregunta> lista = query.getResultList();
			if (lista.isEmpty()) {
				entitymanager.persist(pregunta);
				entitytransaction.commit();
				id = pregunta.getId();
			} else {
				if (!lista.get(0).getActivo()) {
					Pregunta preguntaResult = lista.get(0);
					preguntaResult.setActivo(true);
					entitytransaction.commit();
					id = 1;
				} else {
					entitytransaction.rollback();
					id = -1;
				}

			}
			entitymanager.close();
		}
		return id;
	}

	@Override
	public Pregunta read(int id) {
		Pregunta pregunta = null;
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();
		pregunta = entitymanager.find(Pregunta.class, id);
		if (pregunta == null)
			entitytransaction.rollback();
		else
			entitytransaction.commit();
		entitymanager.close();
		return pregunta;
	}

	@Override
	public List<Pregunta> readAll() {
		List<Pregunta> lista = null;
		
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();

		TypedQuery<Pregunta> query = entitymanager.createNamedQuery("negocio.pregunta.Pregunta.readAll", Pregunta.class);
		lista = query.getResultList();
		entitytransaction.commit();

		entitymanager.close();

		return lista;
	}

	@Override
	public int update(Pregunta pregunta) {
		int id = -1;

		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();
		
		Pregunta preguntaResult = entitymanager.find(Pregunta.class, pregunta.getId());
		if(pregunta.getActivo()) {
			preguntaResult.setTitulo(pregunta.getTitulo());
			preguntaResult.setActivo(pregunta.getActivo());
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
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();
		Pregunta pregunta = entitymanager.find(Pregunta.class, id);
		if (pregunta != null) {
			if (pregunta.getActivo()) {

				pregunta.setActivo(false);

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