
package negocio.pregunta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import negocio.EntityManagerUtil;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

public class SAPreguntaImp implements SAPregunta {

	/**
	 * @param pregunta Objeto que contiene los datos de una pregunta que se  quiere crear. 
	 * @return Objeto que contiene un evento que indica el resultado de las operaciones y un dato.
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
				
				TypedQuery<Pregunta> query = entitymanager.createNamedQuery("negocio.pregunta.Pregunta.findBytexto", Pregunta.class);
				
				query.setParameter("texto", pregunta.getTexto());
				
				List<Pregunta> lista = query.getResultList();
				
				if (lista.isEmpty()) {
					
					entitymanager.persist(pregunta);
					
					entitytransaction.commit();
		
					event = Events.CRUD_CREATE_PREGUNTA_OK;
					
					filter.addFilter("info", "");
					
					event.setFilter(filter);
					
					contexto = new Contexto(event,pregunta.getId());
					
				} else {
					
					if (!lista.get(0).getActiva()) {
						
						Pregunta preguntaResult = lista.get(0);
						
						preguntaResult.setActiva(true);
						
						entitytransaction.commit();
					
						event = Events.CRUD_CREATE_PREGUNTA_OK;
						
						filter.addFilter("info", "");
						
						event.setFilter(filter);
						
						contexto = new Contexto(event,preguntaResult.getId());
					
					} else {
						
						entitytransaction.rollback();
		
						event = Events.CRUD_CREATE_PREGUNTA_KO;
						
						filter.addFilter("reason","que ya existia");
						
						filter.addFilter("entity", "");
						
						event.setFilter(filter);
						
						contexto = new Contexto(event,null);
						
					}

				}
				
				entitymanager.close();
			
			}
			
			return contexto;
		
	}

}
