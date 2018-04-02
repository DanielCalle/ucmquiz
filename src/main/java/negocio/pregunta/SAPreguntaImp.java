package negocio.pregunta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import presentacion.Events;

public class SAPreguntaImp implements SAPregunta {

	@Override
	public int activarPregunta(int id) {
		
		if (ComprobadorSintactico.isPositive(id)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Pregunta pregunta = entityManager.find(Pregunta.class, id);

			if (pregunta != null) {
				if (!pregunta.getActiva()) {
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
				return Events.NO_ENTITY;
			}
			
			entityManager.close();
			
		}
		else return Events.WRONG_TYPE_PARAMETER;
		
		return id;
	}
	
	@Override
	public int desactivarPregunta(int id) {
		
		if (ComprobadorSintactico.isPositive(id)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Pregunta pregunta = entityManager.find(Pregunta.class, id);

			if (pregunta != null) {
				if (pregunta.getActiva()) {
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
				return Events.NO_ENTITY;
			}
			
			entityManager.close();
		
		}
		else return Events.WRONG_TYPE_PARAMETER;
		
		return id;
	}
	
}
