package negocio.asignatura;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import negocio.pregunta.Pregunta;
import presentacion.Events;
import presentacion.Filter;

public class SAAsignaturaImp implements SAAsignatura {

	@Override
	public Events delete(int id) {

		Filter filter = new Filter();
		filter
			.addFilter("entity", "Asignatura")
			.addFilter("operation", "delete");
		
		if (ComprobadorSintactico.isPositive(id)) {
			filter.addFilter("id", Integer.toString(id));
			
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			
			Asignatura asignatura = entityManager.find(Asignatura.class, id);
			
			if (asignatura != null) {
				if (asignatura.isActivo()) {
					TypedQuery<Pregunta> query = entityManager.createNamedQuery("negocio.trabajador.Trabajador.findByAsignatura", Pregunta.class).setParameter("asignatura", asignatura);
					List<Pregunta> lista = query.getResultList();
					if(lista.isEmpty()) {
						entityManager.remove(asignatura);
						entityTransaction.commit();
					}
					else {
						entityTransaction.rollback();
						return Events.ENTITY_WITH_DEPENDENCIES.setFilter(filter);
					}
				}
				else {
					entityTransaction.rollback();
					return Events.ENTITY_NOT_ACTIVE.setFilter(filter);
				}
			}
			else {
				entityTransaction.rollback();
				return Events.NO_ENTITY.setFilter(filter);
			}
			
			entityManager.close();
		}
		else return Events.WRONG_TYPE_PARAMETER.setFilter(filter);
		
		return Events.CRUD_DELETE_OK.setFilter(filter);
	}
	
}
