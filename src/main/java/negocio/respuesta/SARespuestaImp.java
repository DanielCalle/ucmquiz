package negocio.respuesta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import negocio.EntityManagerUtil;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

public class SARespuestaImp implements SARespuesta {

	@Override
	public Contexto create(Respuesta respuesta) {
		
		Contexto contexto = new Contexto(Events.CRUD_CREATE_RESPUESTA_KO, null);
		Filter filter = new Filter()
				.addFilter("entity", "respuesta")
				.addFilter("operation", "crear");

		if (respuesta != null) {

			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			TypedQuery<Respuesta> query = entityManager.createNamedQuery("negocio.respuesta.Respuesta.findBytexto", Respuesta.class);
			query.setParameter("texto", respuesta.getTexto());

			List<Respuesta> lista = query.getResultList();

			if (lista.isEmpty()) {

				entityManager.persist(respuesta);
				entityTransaction.commit();

				filter.addFilter("info", "");
				
				contexto = new Contexto(
						Events.CRUD_CREATE_RESPUESTA_OK.setFilter(filter),
						respuesta.getId());

			} 
			else {
				if (!lista.get(0).getActiva()) {

					lista.get(0).setActiva(true);
					entityTransaction.commit();

					filter.addFilter("info", "");

					contexto = new Contexto(
							Events.CRUD_CREATE_RESPUESTA_OK.setFilter(filter),
							lista.get(0).getId());

				} 
				else {
					entityTransaction.rollback();

					filter.addFilter("reason", "que ya existia");
					filter.addFilter("info", "");

					contexto = new Contexto(Events.CRUD_CREATE_RESPUESTA_KO.setFilter(filter), null);
				}

			}

			entityManager.close();

		}
		else {
			filter.addFilter("reason", "null object");
			filter.addFilter("info", "");

			contexto = new Contexto(Events.CRUD_CREATE_RESPUESTA_KO.setFilter(filter), null);
		}

		return contexto;
	}

}
