package negocio.asignatura;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;

public class SAAsignaturaImp implements SAAsignatura {

	@Override
	public int acticaDesactivaAsignatura(int id) {
		if (ComprobadorSintactico.isPositive(id)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Asignatura asignatura = entityManager.find(Asignatura.class, id);

			if (asignatura != null) {
				if (asignatura.isActivo()) {
					asignatura.setActivo(false);
					entityTransaction.commit();
				} else {
					asignatura.setActivo(true);
					entityTransaction.commit();
				}
			} else {
				id = -1;
				entityTransaction.rollback();
			}
			entityManager.close();
		}
		return id;
	}

}
