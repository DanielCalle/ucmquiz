package negocio.asignatura;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import negocio.pregunta.Pregunta;
import presentacion.Events;

public class SAAsignaturaImp implements SAAsignatura {

	@Override
	public int delete(int id) {
		
		if (ComprobadorSintactico.isPositive(id)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			
			Asignatura asignatura = entityManager.find(Asignatura.class, id);
			
			if (asignatura != null) {
				if (asignatura.isActivo()) {
					TypedQuery<Pregunta> query = entityManager.createNamedQuery("negocio.trabajador.Trabajador.findBydepartamento", Pregunta.class).setParameter("asignatura", asignatura);
					List<Pregunta> lista = query.getResultList();
					if(lista.isEmpty()) {
						asignatura.setActivo(false);
						entityManager.persist(asignatura);
						entityTransaction.commit();
					}
					else {
						entityTransaction.rollback();
						return Events.ENTITY_WITH_DEPENDENCIES;
					}
				}
				else {
					entityTransaction.rollback();
					return Events.ENTITY_NOT_ACTIVE;
				}
			}
			else {
				entityTransaction.rollback();
				return Events.NO_ENTITY;
			}
			
			entityManager.close();
		}
		else return Events.WRONG_TYPE_PARAMETER;
		
		return id;
	}
	
}
