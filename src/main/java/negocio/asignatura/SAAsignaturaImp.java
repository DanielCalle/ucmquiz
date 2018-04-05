package negocio.asignatura;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

public class SAAsignaturaImp implements SAAsignatura {

	/**
	 * Realiza la operacion de un borrado fisico sobre la entidad Asignatura
	 * @param id El identificador de la Asignatura a borrar.
	 * @return Devuelve un contexto del id de la Asignatura borrada y del estado de la operacion.
	 * @author Zihao
	 */
	@Override
	public Contexto delete(int id) {
		
		// Filtro para los mensajes que se vayan a mostrar
		Filter filter = new Filter();
		filter
			.addFilter("entity", "Asignatura")
			.addFilter("operation", "delete");
		
		// Comprueba la sintaxis del parametro de entrada que tiene que ser un int positivo
		if (ComprobadorSintactico.isPositive(id)) {
			filter.addFilter("id", Integer.toString(id));
			
			// Se comienza la transaccion
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			
			// Busca en la base de datos la Asignatura
			Asignatura asignatura = entityManager.find(Asignatura.class, id);
			
			// Si se encuentra
			if (asignatura != null) {
				// Si es activo
				if (asignatura.isActivo()) {
					// Se crea la query para ver si hay preguntas que dependen de esta asignatura a borrar
					TypedQuery<Pregunta> query = entityManager.createNamedQuery("negocio.trabajador.Trabajador.findByAsignatura", Pregunta.class).setParameter("asignatura", asignatura);
					List<Pregunta> lista = query.getResultList();
					// Si no hay dependencias
					if(lista.isEmpty()) {
						// Borrado y commit
						entityManager.remove(asignatura);
						entityTransaction.commit();
					}
					else {
						entityTransaction.rollback();
						return new Contexto(Events.ENTITY_WITH_DEPENDENCIES.setFilter(filter), id);
					}
				}
				else {
					entityTransaction.rollback();
					return new Contexto(Events.ENTITY_NOT_ACTIVE.setFilter(filter), id);
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
