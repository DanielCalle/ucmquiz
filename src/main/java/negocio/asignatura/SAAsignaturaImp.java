package negocio.asignatura;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

public class SAAsignaturaImp implements SAAsignatura {

	@Override
	public Contexto activaAsignatura(int id) {
		
		// Filtro para los mensajes que se vayan a mostrar
		Filter filter = new Filter();
		filter
			.addFilter("entity", "asignatura")
			.addFilter("operation", "activar");
		
		if (ComprobadorSintactico.isPositive(id)) {
			
			filter.addFilter("id", Integer.toString(id));
			
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Asignatura asignatura = entityManager.find(Asignatura.class, id);
			if (asignatura != null) { 
				if (!asignatura.isActivo()) {
					asignatura.setActivo(true);
					entityManager.refresh(asignatura);
					entityTransaction.commit();
				} else {
					entityTransaction.rollback();
				}
			}
			else {
				entityTransaction.rollback();
				return new Contexto(Events.NO_ENTITY.setFilter(filter), id);
			}
			
			entityManager.close();
		}
		else return new Contexto(Events.WRONG_TYPE_PARAMETER.setFilter(filter), id);
		
		return new Contexto(Events.COMMAND_ASIGNATURA_ACTIVATE.setFilter(filter), id);
	}
	
	@Override
	public Contexto desactivaAsignatura(int id) {
		// Filtro para los mensajes que se vayan a mostrar
		Filter filter = new Filter();
		filter
			.addFilter("entity", "asignatura")
			.addFilter("operation", "activar");
		
		if (ComprobadorSintactico.isPositive(id)) {
			
			filter.addFilter("id", Integer.toString(id));
			
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Asignatura asignatura = entityManager.find(Asignatura.class, id);

			if (asignatura != null) {
				if (asignatura.isActivo()) {
					asignatura.setActivo(false);
					entityManager.refresh(asignatura);
					entityTransaction.commit();
				} 
				else {			
					entityTransaction.rollback();
				}
			}
			else {
				entityTransaction.rollback();
				return new Contexto(Events.NO_ENTITY.setFilter(filter), id);
			}
			
			entityManager.close();
		}
		else return new Contexto(Events.WRONG_TYPE_PARAMETER.setFilter(filter), id);
		
		return new Contexto(Events.COMMAND_ASIGNATURA_DESACTIVATE.setFilter(filter), id);
	}
	
	/**
	 * Devuelve la lista con todas las asignaturas
	 * @author Daniel Calle
	 */
	@Override
	public Contexto readAll() {
		Events e;
		Filter filter = new Filter();
		List<Asignatura> lista = null;
		
		try {
			EntityManager entitymanager = EntityManagerUtil.getEntityManager();
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			entitytransaction.begin();
				
			TypedQuery<Asignatura> query = entitymanager.createNamedQuery("negocio.asignatura.Asignatura.readAll", Asignatura.class);
			lista = query.getResultList();
			entitytransaction.commit();
			e = Events.CRUD_READ_ALL_ASIGNATURA_OK;
			filter.addFilter("info","");
				
			entitymanager.close();
		} catch(PersistenceException ex) {
			e = Events.CRUD_READ_ALL_ASIGNATURA_KO;
			filter.addFilter("reason","problemas técnicos");
			filter.addFilter("info","");
		}
		
		return new Contexto(e,lista);
	}

}
