package negocio.pregunta;

//<<<<<<< HEAD
import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import negocio.EntityManagerUtil;
//=======
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import presentacion.Contexto;
//>>>>>>> refs/remotes/origin/develop

public class SAPreguntaTest {

	@Test
	// <<<<<<< HEAD
	public void borrar_pregunta_deberia_dejar_su_asignatura_en_null(int idPregunta) {
		Pregunta p = null;
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();

		try {
			p = entitymanager.find(Pregunta.class, idPregunta); // se busca el objeto por la clave primaria (el id)

			assertEquals(p.getAsignatura(), null);

		} catch (IllegalArgumentException iae) { // si el id pasado no existe el entitymanager soltar� error
			iae.printStackTrace();
			entitytransaction.rollback();
		} finally {
			entitymanager.close();
		}
	}

	// =======
	public void testCrearPregunta() {

		Pregunta p = new Pregunta("¿ Cual es la definicion sencilla del teorema de la integral de Riemann ?", true);

		SAPreguntaImp sa = new SAPreguntaImp();

		Contexto contexto = sa.create(p);

		Integer id = (Integer) contexto.getDato();

		assertTrue("Si la pregunta ya esta en la BBDD se ha de retornar "
				+ "-1, si no el identificador ha de ser un numero positivo.", id == null || id > 0);

	}

	@Test
	public void testCrearPreguntaNula() {

		Pregunta p = null;

		SAPreguntaImp sa = new SAPreguntaImp();

		assertNull("No se puede crear una pregunta nula.", sa.create(p).getDato());

	}

	@Test
	public void testCrearPreguntaInactiva() {

		Pregunta p = new Pregunta("¿ Cual es la definicion de Kernel para un sistema operativo ?", false);

		SAPreguntaImp sa = new SAPreguntaImp();

		Contexto contexto = sa.create(p);

		Integer id = (Integer) contexto.getDato();

		assertTrue("Podemos insertar una pregunta desactivada, su identificador "
				+ "es positivo. Si se vuelve a reinsertar y su estado de activacion "
				+ "en la BBDD es false, esta es automaticamente reactivada. Si ya existia "
				+ "y su estado de activacion era true, entonces se ha de retornar -1.", id == null || id > 0);

		// >>>>>>> refs/remotes/origin/develop
	}

}
