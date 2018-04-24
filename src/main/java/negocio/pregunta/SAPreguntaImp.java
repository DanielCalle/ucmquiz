
package negocio.pregunta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

public class SAPreguntaImp implements SAPregunta {

	public Contexto borrarPregunta(int idPregunta) {
		
		Events event = null;

		Contexto contexto = null;

		Filter filter = new Filter();

		Pregunta p = null;
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();

		p = entitymanager.find(Pregunta.class, idPregunta); // se busca el objeto por la clave primaria (el id)

		if(p != null) {
			entitymanager.remove(p);
			//entitymanager.refresh(p); // se actualiza la entidad en bbdd
			//p.setActiva(false);
			entitytransaction.commit();
			event = Events.CRUD_DELETE_PREGUNTA_OK;
			
			filter.addFilter("info", "");

			event.setFilter(filter);
			contexto = new Contexto(event, null);
		}else {
			// si el id pasado no existe el entitymanager soltar� error
			entitytransaction.rollback();
			event = Events.CRUD_DELETE_PREGUNTA_KO;
			filter.addFilter("reason", "que el id introducido no exista");
			
			filter.addFilter("info", "");

			event.setFilter(filter);
			contexto = new Contexto(event, null);
		}
		
		return contexto;
	}

		/**
	 * @param pregunta
	 *            Objeto que contiene los datos de una pregunta que se quiere crear.
	 * @return Objeto que contiene un evento que indica el resultado de las
	 *         operaciones y un dato.
	 */
	@Override
	public Contexto create(Pregunta pregunta) {

		Events event = null;

		Contexto contexto = null;

		Filter filter = new Filter();

		if (pregunta != null) {

			EntityManager entitymanager = EntityManagerUtil.getEntityManager();

			EntityTransaction entitytransaction = entitymanager.getTransaction();

			entitytransaction.begin();

			TypedQuery<Pregunta> query = entitymanager.createNamedQuery("negocio.pregunta.Pregunta.findBytexto",
					Pregunta.class);

			query.setParameter("texto", pregunta.getTexto());

			List<Pregunta> lista = query.getResultList();

			if (lista.isEmpty()) {

				entitymanager.persist(pregunta);

				entitytransaction.commit();

				event = Events.CRUD_CREATE_PREGUNTA_OK;

				filter.addFilter("info", "");

				event.setFilter(filter);

				contexto = new Contexto(event, pregunta.getId());

			} else {

				if (!lista.get(0).getActiva()) {

					Pregunta preguntaResult = lista.get(0);

					preguntaResult.setActiva(true);

					entitytransaction.commit();

					event = Events.CRUD_CREATE_PREGUNTA_OK;

					filter.addFilter("info", "");

					event.setFilter(filter);

					contexto = new Contexto(event, preguntaResult.getId());

				} else {

					entitytransaction.rollback();

					event = Events.CRUD_CREATE_PREGUNTA_KO;

					filter.addFilter("reason", "que ya existia");

					filter.addFilter("info", "");

					event.setFilter(filter);

					contexto = new Contexto(event, null);

				}

			}

			entitymanager.close();

		}

		return contexto;

	}
	
	@Override
	public Contexto activatePregunta(int id) {
		
		Events events = null;
		Integer identifier = null;
		Filter filter = new Filter();
		
		if (ComprobadorSintactico.isPositive(id)) {
			
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			
			entityTransaction.begin();

			Pregunta pregunta = entityManager.find(Pregunta.class, id);
			
			if(pregunta != null) { 
				
				if(!pregunta.getActiva()) {
			
					pregunta.setActiva(true);
					entityManager.persist(pregunta);
					entityTransaction.commit();
			
					identifier = id;
					events = Events.PREGUNTA_ACTIVATE_OK;
					filter.addFilter("id",Integer.toString(id)+".");
					
				} else {
					
					entityTransaction.rollback();
				
					identifier = id;
					events = Events.PREGUNTA_ACTIVATE_KO;
					filter.addFilter("reason", "que la pregunta ya esta activa.");
					
				}
			
			} else {
				
				entityTransaction.rollback();

				identifier = id;
				events = Events.PREGUNTA_ACTIVATE_KO;
				filter.addFilter("reason", "que la pregunta no existe.");
			
			}
			
			entityManager.close();
		
		} else {
			
			events = Events.PREGUNTA_ACTIVATE_KO;
			filter.addFilter("reason", "que el parametro de busqueda fue incorrecto.");
		
		}
		
		return new Contexto(events.setFilter(filter),identifier);
		
	}
	
	@Override
	public Contexto deactivatePregunta(int id) {
	
		Events events = null;
		Integer identifier = null;
		Filter filter = new Filter();
		
		if (ComprobadorSintactico.isPositive(id)) {
			
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			
			entityTransaction.begin();

			Pregunta pregunta = entityManager.find(Pregunta.class, id);

			if(pregunta != null) {
				
				if(pregunta.getActiva()) {
					
					pregunta.setActiva(false);
					entityManager.persist(pregunta);
					entityTransaction.commit();
				
					identifier = id;
					events = Events.PREGUNTA_DESACTIVATE_OK;
					filter.addFilter("id",Integer.toString(id)+".");
					
				} else {			
					
					entityTransaction.rollback();
				
					identifier = id;
					events = Events.PREGUNTA_DESACTIVATE_KO;
					filter.addFilter("reason", "que la pregunta ya esta desactivada.");
					
				}
				
			} else {
				
				entityTransaction.rollback();
				
				identifier = id;
				events = Events.PREGUNTA_DESACTIVATE_KO;
				filter.addFilter("reason", "que la pregunta no existe.");
			
			}
			
			entityManager.close();
		
		} else {
			
			events = Events.PREGUNTA_DESACTIVATE_KO;
			filter.addFilter("reason", "que el parametro de busqueda fue incorrecto.");
		
		}
		
		return new Contexto(events.setFilter(filter),identifier);
	
	}

}
