package negocio.asignatura;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;

public class SAAsignaturaImp implements SAAsignatura {

	@Override
	public boolean activaAsignatura(int id) {
		boolean estado = true;
		
		if (ComprobadorSintactico.isPositive(id)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Asignatura asignatura = entityManager.find(Asignatura.class, id);
			estado = asignatura.isActivo();
			
			if (!estado) {
				asignatura.setActivo(true);
				estado = true;
				entityTransaction.commit();
			} else {
				entityTransaction.rollback();
			}
			entityManager.close();
		}
		
		return estado;
	}
	
	@Override
	public boolean desactivaAsignatura(int id) {
		boolean estado = true;
		
		if (ComprobadorSintactico.isPositive(id)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Asignatura asignatura = entityManager.find(Asignatura.class, id);
			estado = asignatura.isActivo();

			if (estado) {
				asignatura.setActivo(false);
				estado = false;
				entityTransaction.commit();
			} else {			entityTransaction.rollback();
			}
			entityManager.close();
		}
		return estado ;
	}

}
