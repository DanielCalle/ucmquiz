package negocio.pregunta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import negocio.EntityManagerUtil;

public class SAPreguntaImp implements SAPregunta {

	public void borrarPregunta(int idPregunta) {
		Pregunta p = null;
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();
		
		try {
			p = entitymanager.find(Pregunta.class, idPregunta); //se busca el objeto por la clave primaria (el id)
		
			p.setAsignatura(null); //se desvincula la pregunta con la asignatura (haciendo la dependencia null)
			entitymanager.refresh(p); //se actualiza la entidad en bbdd
			entitytransaction.commit();
			
		}catch(IllegalArgumentException iae) { //si el id pasado no existe el entitymanager soltará error
			iae.printStackTrace();
			entitytransaction.rollback();
		}finally {
			entitymanager.close();
		}
	}

}
