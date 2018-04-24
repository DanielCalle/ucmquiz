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
}

