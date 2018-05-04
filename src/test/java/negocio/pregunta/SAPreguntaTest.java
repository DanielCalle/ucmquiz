package negocio.pregunta;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import negocio.FactoriaNegocio;
import negocio.respuesta.Respuesta;
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
		Respuesta r1 = new Respuesta("A",true,true);
		Respuesta r2 = new Respuesta("B",false,true);
		List<Respuesta> respuestas = new ArrayList<>();
		respuestas.add(r1);
		respuestas.add(r2);
		p.setRespuestas(respuestas);
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
		Respuesta r1 = new Respuesta("A",true,true);
		Respuesta r2 = new Respuesta("B",false,true);
		List<Respuesta> respuestas = new ArrayList<>();
		respuestas.add(r1);
		respuestas.add(r2);
		p.setRespuestas(respuestas);
		
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
	
	@Test
	public void borrarPreguntaTest() {
		
		Pregunta p = new Pregunta("¿Quien es mejor profesor, Hector o Antonio?", true);
		Respuesta r1 = new Respuesta("A",true,true);
		Respuesta r2 = new Respuesta("B",false,true);
		List<Respuesta> respuestas = new ArrayList<>();
		respuestas.add(r1);
		respuestas.add(r2);
		p.setRespuestas(respuestas);
		
		SAPregunta sap = FactoriaNegocio.getInstance().generateSAPregunta();
		
		Contexto c = sap.create(p);
		
		Integer id = (Integer) c.getDato();
		
		c = sap.borrarPregunta(id);
		
		assertEquals("El evento de la operacion Delete en Pregunta tiene que estar OK"
				,c.getEvent(),
				Events.CRUD_DELETE_PREGUNTA_OK);
	}
	
	@Test
	public void listarPreguntaTest() {
		SAPregunta sap = new SAPreguntaImp();
		Contexto c = sap.readAll();
		
		assertEquals("El evento de la operacion ReadAll en Pregunta tiene que dar el comando CRUD_READ_ALL_PREGUNTA_OK"
				,c.getEvent(),Events.CRUD_READ_ALL_PREGUNTA_OK);
	}
}
