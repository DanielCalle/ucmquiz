package negocio.asignatura;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import negocio.EntityManagerUtil;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

public class SAAsignaturaImp implements SAAsignatura {

	@Override
	public Contexto readAll() {
		List<Asignatura> lista = null;
		Events event = null;
		Filter filter = new Filter();
		Contexto contexto = null;
		try {
			EntityManager entitymanager = EntityManagerUtil.getEntityManager();
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			entitytransaction.begin();
				
			TypedQuery<Asignatura> query = entitymanager.createNamedQuery("negocio.asignatura.Asignatura.readAll", Asignatura.class);
			lista = query.getResultList();
			if (lista.isEmpty()) {
				entitytransaction.rollback();
				

				event = Events.CRUD_READ_ALL_PREGUNTA_KO;
				filter.addFilter("reason","no existen asignaturas");
				
				filter.addFilter("info", "");
				
				event.setFilter(filter);
				
				contexto = new Contexto(event,lista);
			}
			else {
			entitytransaction.commit();
			event = Events.CRUD_READ_ALL_PREGUNTA_OK;
			
			filter.addFilter("info", "");
			
			event.setFilter(filter);
			
			contexto = new Contexto(event,lista);
			} 
			entitymanager.close();
		}
		catch(PersistenceException ex) {
		}
	return contexto;	
	}

}
