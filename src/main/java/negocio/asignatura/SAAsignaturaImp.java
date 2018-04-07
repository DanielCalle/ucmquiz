package negocio.asignatura;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import negocio.EntityManagerUtil;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

/**
 * Servicio de aplicacion de la entidad Asignatura.
 */
public class SAAsignaturaImp implements SAAsignatura {

	/**
	 * @param asignatura --> Objeto que contiene los datos de la asignatura que se quiere crear.
	 * @return Contexto --> Objeto que contiene el resultado de la operacion y un dato.
	 */
	@Override
	public Contexto create(Asignatura asignatura) {
		
		Events event = null;
		
		Contexto contexto = null;
		
		Filter filter = new Filter();
		
		if (asignatura != null) {
			
			EntityManager entitymanager = EntityManagerUtil.getEntityManager();
			
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			
			entitytransaction.begin();
			
			TypedQuery<Asignatura> query = entitymanager.createNamedQuery("negocio.asignatura.Asignatura.findBytitulo", Asignatura.class);
			
			query.setParameter("titulo", asignatura.getTitulo());
			
			List<Asignatura> lista = query.getResultList();
			
			if (lista.isEmpty()) {
				
				entitymanager.persist(asignatura);
				
				entitytransaction.commit();
				
				event = Events.CRUD_CREATE_ASIGNATURA_OK;
			
				filter.addFilter("info", asignatura.getTitulo());
				
				event.setFilter(filter);
				
				contexto = new Contexto(event, asignatura.getId());
				
			} else {
				
				if (!lista.get(0).isActivo()) {
					
					Asignatura asignaturaResult = lista.get(0);
					
					asignaturaResult.setActivo(true);
					
					entitytransaction.commit();
					
					event = Events.CRUD_CREATE_ASIGNATURA_OK;
					
					filter.addFilter("info", asignaturaResult.getTitulo());
					
					event.setFilter(filter);
					
					contexto = new Contexto(event, asignaturaResult.getId());
				
				} else {
					
					entitytransaction.rollback();
	
					event = Events.CRUD_CREATE_ASIGNATURA_KO;
					
					filter.addFilter("reason","que ya esta activa");
					
					filter.addFilter("info","la asignatura " + asignatura.getTitulo());
					
					event.setFilter(filter);
					
					contexto = new Contexto(event, null);
					
				}

			}
			
			entitymanager.close();
		
		}
		
		return contexto;

	}

}
