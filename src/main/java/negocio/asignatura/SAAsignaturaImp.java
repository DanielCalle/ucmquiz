package negocio.asignatura;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;


/**
 * Servicio de aplicacion de la entidad Asignatura.
 */
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

public class SAAsignaturaImp implements SAAsignatura {
/*
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
	}*/
	/*public Contexto readAll() {
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
			entitytransaction.commit();
			event = Events.CRUD_READ_ALL_ASIGNATURA_OK;
			filter.addFilter("info", "");
			event.setFilter(filter);
			contexto = new Contexto(event,lista);
			entitymanager.close();
		}
		catch(PersistenceException ex) {
			event = Events.CRUD_READ_ALL_ASIGNATURA_KO;
			filter.addFilter("info", "");
			filter.addFilter("reason", "problemas técnicos");
			event.setFilter(filter);
		}
	return contexto;	
	}*/

	
	 /**
	 * Realiza la operacion de un borrado fisico sobre la entidad Asignatura
	 * @param id El identificador de la Asignatura a borrar.
	 * @return Devuelve un contexto del id de la Asignatura borrada y del estado de la operacion.
	 * @author Zihao
	 */
	@Override
	public Contexto delete(int id) {
		
		Events e;
		Filter filter = new Filter();

		if (ComprobadorSintactico.isPositive(id)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			
			Asignatura asignatura = entityManager.find(Asignatura.class, id);
			
			if (asignatura != null) {
				if (asignatura.isActivo()) {
					TypedQuery<Pregunta> query = entityManager.createNamedQuery("negocio.pregunta.Pregunta.findByAsignatura", Pregunta.class).setParameter("asignatura", asignatura);
					List<Pregunta> lista = query.getResultList();
					if(lista.isEmpty()) {
						entityManager.remove(asignatura);
						entityTransaction.commit();
						e = Events.CRUD_DELETE_ASIGNATURA_OK;
						filter.addFilter("info","con ID " + id);
					}
					else {
						entityTransaction.rollback();
						e = Events.CRUD_DELETE_ASIGNATURA_KO;
						filter.addFilter("reason","que tiene preguntas asignadas");
						filter.addFilter("info","con ID " + id);
					}
				}
				else {
					entityTransaction.rollback();
					e = Events.CRUD_DELETE_ASIGNATURA_KO;
					filter.addFilter("reason","que la asignatura no esta activa");
					filter.addFilter("info","con ID " + id);
				}
			}
			else {
				entityTransaction.rollback();
				e = Events.CRUD_DELETE_ASIGNATURA_KO;
				filter.addFilter("reason","que la asignatura no existe");
				filter.addFilter("info","con ID " + id);
			}
			
			entityManager.close();
		}
		else {
			e = Events.CRUD_DELETE_ASIGNATURA_KO;
			filter.addFilter("reason","el ID que se quiere borrar no tiene formato adecuado");
			filter.addFilter("info","con ID " + id);
		}
		
		e.setFilter(filter);
		
		return new Contexto(e,id);
	}
	
	/*
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

	@Override
	public Contexto activeAsignatura(int id) {
		
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
					entityManager.persist(asignatura);
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
		
		return new Contexto(Events.ASIGNATURA_ACTIVATE_OK.setFilter(filter), id);
	}
	
	@Override
	public Contexto desactiveAsignatura(int id) {
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
					entityManager.persist(asignatura);
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
		
		return new Contexto(Events.ASIGNATURA_DESACTIVATE_OK.setFilter(filter), id);
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
