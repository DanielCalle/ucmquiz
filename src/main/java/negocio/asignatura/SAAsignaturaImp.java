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

public class SAAsignaturaImp implements SAAsignatura {

	@Override
	public Contexto delete(int id) {
		
		if (ComprobadorSintactico.isPositive(id)) {
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
						return new Contexto(Events.CRUD_DELETE_KO, id);
					}
				}
				else {
					entityTransaction.rollback();
					return new Contexto(Events.CRUD_DELETE_KO, id);
				}
			}
			else {
				entityTransaction.rollback();
				return new Contexto(Events.CRUD_DELETE_KO, id);
			}
			
			entityManager.close();
		}
		else return new Contexto(Events.CRUD_DELETE_KO, id);
		
		return new Contexto(Events.CRUD_DELETE_OK, id);
	}
	
}
