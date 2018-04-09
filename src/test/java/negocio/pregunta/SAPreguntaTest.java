package negocio.pregunta;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import negocio.EntityManagerUtil;

public class SAPreguntaTest {

	@Test
	public void borrar_pregunta_deberia_dejar_su_asignatura_en_null(int idPregunta) {
		Pregunta p = null;
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();
		
		try {
			p = entitymanager.find(Pregunta.class, idPregunta); //se busca el objeto por la clave primaria (el id)
		
			assertEquals(p.getAsignatura(), null);
			
		}catch(IllegalArgumentException iae) { //si el id pasado no existe el entitymanager soltará error
			iae.printStackTrace();
			entitytransaction.rollback();
		}finally {
			entitymanager.close();
		}
	}
}
