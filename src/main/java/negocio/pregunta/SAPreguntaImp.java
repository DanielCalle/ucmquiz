package negocio.pregunta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

public class SAPreguntaImp implements SAPregunta {

	/**
	 * Realiza un activado logico de la entidad pregunta con un id especificado
	 * @param id El identificador de la pregunta a activar
	 * @return contexto que tiene el id de la pregunta activada y el estado de la operacion
	 * @author Zihao
	 */
	@Override
	public Contexto activarPregunta(int id) {
		
		// Filtro para los mensajes que se vayan a mostrar
				Filter filter = new Filter();
				filter
					.addFilter("entity", "Pregunta")
					.addFilter("operation", "activar");
				
		// Comprueba la sintaxis del parametro de entrada que tiene que ser un int positivo		
		if (ComprobadorSintactico.isPositive(id)) {
			
			// Se comienza la transaccion
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			// Busca en la base de datos la Pregunta
			Pregunta pregunta = entityManager.find(Pregunta.class, id);

			// Si se encuentra
			if (pregunta != null) {
				
				// Si no esta activo
				if (!pregunta.getActiva()) {
					// Activado, actualizado y commit
					pregunta.setActiva(true);
					entityManager.refresh(pregunta);
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
		
		return new Contexto(Events.CRUD_DELETE_OK.setFilter(filter), id);
	}
	
	/**
	 * Realiza un desactivado logico de la entidad pregunta con un id especificado
	 * @param id El identificador de la pregunta a desactivar
	 * @return contexto que tiene el id de la pregunta desactivada y el estado de la operacion
	 * @author Zihao
	 */
	@Override
	public Contexto desactivarPregunta(int id) {
		
		// Filtro para los mensajes que se vayan a mostrar
		Filter filter = new Filter();
		filter
			.addFilter("entity", "Pregunta")
			.addFilter("operation", "desactivar");
		
		// Comprueba la sintaxis del parametro de entrada que tiene que ser un int positivo
		if (ComprobadorSintactico.isPositive(id)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			// Busca en la base de datos la Pregunta
			Pregunta pregunta = entityManager.find(Pregunta.class, id);

			// Si se encuentra
			if (pregunta != null) {
				
				// Si ests activo
				if (pregunta.getActiva()) {
					
					// Desactivado, actualizado y commit
					pregunta.setActiva(false);
					entityManager.refresh(pregunta);
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
		
		return new Contexto(Events.CRUD_DELETE_OK.setFilter(filter), id);
	}
	
}
