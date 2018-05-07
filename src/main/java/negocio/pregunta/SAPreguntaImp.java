
package negocio.pregunta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import negocio.respuesta.Respuesta;
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
				
				List<Respuesta> respuestas = pregunta.getRespuestas();
				if (respuestas.size() < 2) {
					event = Events.CRUD_CREATE_PREGUNTA_KO;

					filter.addFilter("reason", "menos de dos respuestas");

					filter.addFilter("info", "");

					event.setFilter(filter);

					contexto = new Contexto(event, null);
					
					entitytransaction.rollback();

				}
				else {
					boolean correcta = false;
					for (int i = 0; i < respuestas.size(); i++) {
						if (respuestas.get(i).isCorrecta())
							correcta = true;
					}
					if (correcta) {
		
						event = Events.CRUD_CREATE_PREGUNTA_OK;
		
						filter.addFilter("info", "");
		
						event.setFilter(filter);

						entitymanager.persist(pregunta);
						
						
						for (Respuesta r: pregunta.getRespuestas())
							r.setPregunta(pregunta);
							
						entitytransaction.commit();
						
						contexto = new Contexto(event, pregunta.getId());
					}
					else {

						event = Events.CRUD_CREATE_PREGUNTA_KO;

						filter.addFilter("reason", "no tiene respuesta correcta");

						filter.addFilter("info", "");

						event.setFilter(filter);

						contexto = new Contexto(event, null);
						entitytransaction.rollback();
					}
				}

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
		public Contexto readAll() {
			Events e;
			Filter filter = new Filter();
			List<Pregunta> lista = null;
			
			try {
				EntityManager entitymanager = EntityManagerUtil.getEntityManager();
				EntityTransaction entitytransaction = entitymanager.getTransaction();
				entitytransaction.begin();
					
				TypedQuery<Pregunta> query = entitymanager.createNamedQuery("negocio.pregunta.Pregunta.readAll", Pregunta.class);
				lista = query.getResultList();
				entitytransaction.commit();
				e = Events.CRUD_READ_ALL_PREGUNTA_OK;
				filter.addFilter("info","");
					
				entitymanager.close();
			} catch(PersistenceException ex) {
				e = Events.CRUD_READ_ALL_PREGUNTA_KO;
				filter.addFilter("reason","problemas tecnicos");
				filter.addFilter("info","");
			}
			
			return new Contexto(e,lista);
		}

		/**
		 * Lee una pregunta
		 * @param id Id de la pregunta
		 * @return Contexto con la pregunta correspondiente si se encontro y null si no
		 * @author Zihao
		 */
		@Override
		public Contexto read(int id) {
			Events event = null;
			Contexto contexto = null;
			// Filtro de mensajes
			Filter filter = new Filter();
			
			// Comprobar si el id es un int positivo
			if (ComprobadorSintactico.isPositive(id)) {
				EntityManager entitymanager = EntityManagerUtil.getEntityManager();
				EntityTransaction entitytransaction = entitymanager.getTransaction();
				entitytransaction.begin();
	
				// Busqueda en base de datos
				Pregunta pregunta = entitymanager.find(Pregunta.class, id); // se busca el objeto por la clave primaria (el id)
	
				if(pregunta != null) {
					entitytransaction.commit();
					event = Events.CRUD_READ_PREGUNTA_OK;
					filter.addFilter("info", "");
					event.setFilter(filter);
					contexto = new Contexto(event, pregunta);
				}
				else {
					entitytransaction.rollback();
					event = Events.CRUD_READ_PREGUNTA_KO;
					filter.addFilter("reason", "la pregunta con id {id} no existe");
					filter.addFilter("id", id + "");
					event.setFilter(filter);
					contexto = new Contexto(event, null);
				}
			}
			else {
				event = Events.CRUD_READ_PREGUNTA_KO;
				filter.addFilter("reason","el ID que se quiere borrar no tiene formato adecuado");
				filter.addFilter("info","con ID " + id);
			}
			
			return contexto;
		}
	
	/**
	 * Método que activa una pregunta existente y desactivada.
	 * @param id Identificador de la pregunta que se va a activar.
	 */
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
	
	/**
	 * Metodo que desactiva una pregunta existente y activa.
	 * @param id Identificador de la pregunta que se va a desactivar.
	 */
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
