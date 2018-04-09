package negocio.pregunta;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import negocio.FactoriaNegocio;
import presentacion.Contexto;
import presentacion.Events;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import negocio.EntityManagerUtil;

public class SAPreguntaTest {

	@Test
	public void testCrearPregunta() {
		
		Pregunta p = new Pregunta("¿ Cual es la definicion sencilla del teorema de la integral de Riemann ?", true);
		
		Integer id = (Integer) FactoriaNegocio.getInstance().generateSAPregunta().create(p).getDato();
		
		assertTrue(
			"Si la pregunta ya esta en la BBDD se ha de retornar " +
			"-1, si no el identificador ha de ser un numero positivo."
			, 
			id == null || id > 0
		);
		
	}
	
	@Test
	public void testCrearPreguntaNula() {
		
		Pregunta p = null;
		
		assertNull("No se puede crear una pregunta nula.", FactoriaNegocio.getInstance().generateSAPregunta().create(p) );
		
	}
	
	@Test
	public void testCrearPreguntaInactiva() {
		 
		Pregunta p = new Pregunta("¿ Cual es la definicion de Kernel para un sistema operativo ?", false);
		
		Integer id = (Integer) FactoriaNegocio.getInstance().generateSAPregunta().create(p).getDato(); 
		
		assertTrue(
			"Podemos insertar una pregunta desactivada, su identificador " +
			"es positivo. Si se vuelve a reinsertar y su estado de activacion " + 
			"en la BBDD es false, esta es automaticamente reactivada. Si ya existia " +
			"y su estado de activacion era true, entonces se ha de retornar -1."
			, 
			id == null || id > 0
		);
		
	}
	
	public void borrar_pregunta_deberia_dejar_su_asignatura_en_null(int idPregunta) {
		
		Pregunta p = new Pregunta("�Quien es mejor profesor, Hector o Antonio?", true);
		
		SAPregunta sap = new SAPreguntaImp();
		
		Contexto c = sap.create(p);
		
		Integer id = (Integer) c.getDato();
		
		c = sap.borrarPregunta(id);
		
		assertEquals("El evento de la operacion Delete en Pregunta tiene que estar OK"
				,c.getEvent(),
				Events.CRUD_DELETE_PREGUNTA_OK);
	}
}
