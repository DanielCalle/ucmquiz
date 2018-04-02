package negocio.respuesta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceException;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import negocio.pregunta.Pregunta;

public class SARespuestaImp implements SARespuesta {

	/*
	 * Error:
	 * -1: Respuesta null o Pregunta null
	 * -2: Error sintactico
	 * -3: El pregunta no existe o no esta activa
	 * -100: Error en la gestión de la BDD.
	 */
	@Override
	public int create(Respuesta respuesta) {
		int id = -1;
		if(respuesta != null && respuesta.getPregunta() != null) {
			String texto = respuesta.getTexto();
			int idPregunta = respuesta.getPregunta().getId();
			
			if(!texto.isEmpty() && ComprobadorSintactico.isPositive(idPregunta)) {
				try {
					EntityManager entitymanager = EntityManagerUtil.getEntityManager();
					EntityTransaction entitytransaction = entitymanager.getTransaction();
					entitytransaction.begin();
					Pregunta preguntaResult = entitymanager.find(Pregunta.class, idPregunta, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
					if(preguntaResult != null && preguntaResult.getActiva()) {
						respuesta.setActiva(true);
						respuesta.setPregunta(preguntaResult);
						entitymanager.persist(respuesta);
						entitytransaction.commit();
					}
					else {
						//Se ha dado de alta un trabajador con un departamento que no existe
						id = -3;
						entitytransaction.rollback();
					}
					entitymanager.close();
				} catch(PersistenceException ex) {
					id = -100;
				}
			}
			else
				id = -2;
			
		}
		return id;
	}
}
