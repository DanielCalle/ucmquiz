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
		
		Events e;
		Filter filter = new Filter();

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
						e = Events.CRUD_DELETE_ASIGNATURA_OK;
					}
					else {
						entityTransaction.rollback();
						e = Events.CRUD_DELETE_ASIGNATURA_KO;
					}
				}
				else {
					entityTransaction.rollback();
					e = Events.CRUD_DELETE_ASIGNATURA_KO;
				}
			}
			else {
				entityTransaction.rollback();
				e = Events.CRUD_DELETE_ASIGNATURA_KO;
			}
			
			entityManager.close();
		}
		else e = Events.CRUD_DELETE_ASIGNATURA_KO;
		
		filter.addFilter("info", "");
		e.setFilter(filter);
		
		return new Contexto(e,id);
	}
	
}
