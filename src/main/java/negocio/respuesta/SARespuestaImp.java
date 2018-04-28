package negocio.respuesta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import negocio.EntityManagerUtil;

import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

public class SARespuestaImp implements SARespuesta {

		public Contexto borrarRespuesta(String respuesta) {
			Events event = null;

			Contexto contexto = null;

			Filter filter = new Filter();

			Respuesta r = null;
			EntityManager entitymanager = EntityManagerUtil.getEntityManager();
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			entitytransaction.begin();
			
			TypedQuery<Respuesta> query = entitymanager.createNamedQuery("negocio.respuesta.Respuesta.findBytexto", Respuesta.class);
			query.setParameter("titulo", respuesta);
			List<Respuesta> lista = query.getResultList();
			
			if(!lista.isEmpty()) {
				r = lista.get(0);
				entitymanager.remove(r);
				entitytransaction.commit();
				event = Events.CRUD_DELETE_RESPUESTA_OK;
				
				filter.addFilter("info", "");

				event.setFilter(filter);
				contexto = new Contexto(event, null);
			}else {
				entitytransaction.rollback();
				event = Events.CRUD_DELETE_RESPUESTA_KO;
				filter.addFilter("reason", "que la respuesta introducida no exista");
				
				filter.addFilter("info", "");

				event.setFilter(filter);
				contexto = new Contexto(event, null);
			}
				
			return contexto;
		}
		
		public Contexto borrarRespuesta(int idRespuesta) {
			Events event = null;

			Contexto contexto = null;

			Filter filter = new Filter();

			Respuesta r = null;
			EntityManager entitymanager = EntityManagerUtil.getEntityManager();
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			entitytransaction.begin();
			
			r = entitymanager.find(Respuesta.class, idRespuesta);
			
			if(r != null) {
				entitymanager.remove(r);
				entitytransaction.commit();
				event = Events.CRUD_DELETE_RESPUESTA_OK;
				
				filter.addFilter("info", "");

				event.setFilter(filter);
				contexto = new Contexto(event, null);
			}else {
				entitytransaction.rollback();
				event = Events.CRUD_DELETE_RESPUESTA_KO;
				filter.addFilter("reason", "que la respuesta introducida no exista");
				
				filter.addFilter("info", "");

				event.setFilter(filter);
				contexto = new Contexto(event, null);
			}
				
			return contexto;
		}

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
	/**
	 * Realiza la operacion de crear una Respuesta
	 * @param respuesta La respuesta que se quiere crear.
	 * @return Devuelve un contexto del id de la Respuesta creada y del estado de la operacion.
	 * @author Daniel Calle
	 */
	/*@Override
	public Contexto create(Respuesta respuesta) {
		Events e;
		Filter filter = new Filter();
		int id = -1;
		//Se comprueba que no hay referencias null
		if(respuesta != null && respuesta.getPregunta() != null) {
			String texto = respuesta.getTexto();
			int idPregunta = respuesta.getPregunta().getId();
			//Se comprueba que el texto no es vacio y el id de pregunta no sea negativo
			if(!texto.isEmpty() && ComprobadorSintactico.isPositive(idPregunta)) {
				try {
					EntityManager entitymanager = EntityManagerUtil.getEntityManager();
					EntityTransaction entitytransaction = entitymanager.getTransaction();
					entitytransaction.begin();
					Pregunta preguntaResult = entitymanager.find(Pregunta.class, idPregunta, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
					//Se comprueba que existe la pregunta a la que asociar la respuesta
					if(preguntaResult != null) {
						respuesta.setActiva(true);
						respuesta.setPregunta(preguntaResult);
						//Se crea la respuesta
						entitymanager.persist(respuesta);
						entitytransaction.commit();
						e = Events.CRUD_CREATE_RESPUESTA_OK;
						filter.addFilter("info", respuesta.getTexto());
					} else {
						e = Events.CRUD_CREATE_RESPUESTA_KO;
						filter.addFilter("reason","ya existe");
						filter.addFilter("info", respuesta.getTexto());
					}
					entitymanager.close();
				} catch(PersistenceException ex) {
					e = Events.CRUD_CREATE_RESPUESTA_KO;
					filter.addFilter("reason","problemas técnicos");
					filter.addFilter("info", respuesta.getTexto());
				}
			} else
				e = Events.CRUD_CREATE_RESPUESTA_KO;
				filter.addFilter("reason","la sintaxis no es correcta");
				filter.addFilter("info", respuesta.getTexto());
			
		} else {
			e = Events.CRUD_CREATE_RESPUESTA_KO;
			filter.addFilter("reason","los datos se han formado mal");
			filter.addFilter("info", respuesta.getTexto());
		}
		
		e.setFilter(filter);
		
		return new Contexto(e,id);
	}*/
}

