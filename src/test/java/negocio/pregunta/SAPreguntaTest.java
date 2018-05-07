package negocio.pregunta;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import negocio.FactoriaNegocio;
import negocio.respuesta.Respuesta;
import presentacion.Contexto;
import presentacion.Events;

public class SAPreguntaTest {

	/**
	 * Test que comprueba la creación de una pregunta.
	 */
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
	
	/**
	 * Test que comprueba que no se puedan crear preguntas nulas.
	 */
	@Test
	public void testCrearPreguntaNula() {
		
		Pregunta p = null;
		
		assertNull("No se puede crear una pregunta nula.", FactoriaNegocio.getInstance().generateSAPregunta().create(p) );
		
	}
	
	/**
	 * Test que comprueba que se puedan crear preguntas inactivas.
	 */
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
	
	/**
	 * Test que comprueba que se puedan borrar correctamente las preguntas.
	 */
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

	
	@Test
	
	public void responderPreguntaTestOk () {
		
		Pregunta p = new Pregunta("¿ Cual es la definicion sencilla del teorema de la integral de Riemann ?", true);
		Respuesta r1 = new Respuesta("A",true,true);
		Respuesta r2 = new Respuesta("B",false,true);
		List<Respuesta> respuestas = new ArrayList<>();
		respuestas.add(r1);
		respuestas.add(r2);
		p.setRespuestas(respuestas);
		
		SAPregunta sa = FactoriaNegocio.getInstance().generateSAPregunta();
		
		Contexto contexto = sa.create(p);
		
		Integer id = (Integer) contexto.getDato();
		
		contexto = sa.read(id);
		
		assertEquals("El evento de la operacion Read en Pregunta tiene que estar OK"
				,contexto.getEvent(),
				Events.CRUD_READ_PREGUNTA_OK);
		
		
	}
	
	@Test
	public void responderPreguntaTestKO () {
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
		
		c = sap.read(id);
		
		assertEquals("El evento de la operacion Read en Pregunta tiene que estar KO"
				,c.getEvent(),
				Events.CRUD_READ_PREGUNTA_KO);
	}

	/**
	 * Test que comprueba que no puedan activarse preguntas inexistentes.
	 */
	@Test
	public void activarPreguntaParametroIncorrectoPreguntaTest() {
		
		Contexto contexto = FactoriaNegocio.getInstance().generateSAPregunta().activatePregunta(-1);
		
		assertThat(
			"La pregunta no tiene un indice asignado.", 
			contexto.getDato(), 
			nullValue() 
		);
			
		assertThat(
			"No es posible de activarse la pregunta con un id negativo.", 
			contexto.getEvent(),
			is(equalTo(Events.PREGUNTA_ACTIVATE_KO)) 
		);
		
	}
	
	/**
	 * Test que comprueba que las preguntas se activan correctamente.
	 */
	@Test
	public void activarPreguntaTest() {
		
		Integer idBeta = null;
		
		Integer idAlpha = null;
		
		Contexto contexto = null;
		
		Pregunta pregunta = new Pregunta("¿ Es NAND un conjunto universal ?",false);
		
		Respuesta r1 = new Respuesta("Si",true,true);
		Respuesta r2 = new Respuesta("No",false,true);
		List<Respuesta> respuestas = new ArrayList<>();
		respuestas.add(r1);
		respuestas.add(r2);
		pregunta.setRespuestas(respuestas);
		
		SAPregunta saPregunta = FactoriaNegocio.getInstance().generateSAPregunta();
		
		idAlpha = (Integer) saPregunta.create(pregunta).getDato();
		
		contexto = saPregunta.activatePregunta(idAlpha);
		
		idBeta = (Integer) contexto.getDato();
		
		assertThat(
			"La pregunta ya existe.", 
			idAlpha != null && idBeta != null && idAlpha >= 0 && idAlpha >= 0 && idAlpha == idBeta, 
			is(equalTo(true)) 
		);
		
		assertThat(
			"La pregunta ha debido de activarse correctamente.", 
			contexto.getEvent(),
			is(equalTo(Events.PREGUNTA_ACTIVATE_OK)) 
		);
		
		saPregunta.borrarPregunta(idAlpha);
		
	}
	
	/**
	 * Test que comprueba que no se puede activar una pregunta ya activa.
	 */
	@Test
	public void activarPreguntaYaActivaPreguntaTest() {
		
		Integer idBeta = null;
		
		Integer idAlpha = null;
		
		Contexto contexto = null;
		
		Pregunta pregunta = new Pregunta("¿ Es NAND un conjunto universal ?",true);
		
		Respuesta r1 = new Respuesta("Si",true,true);
		Respuesta r2 = new Respuesta("No",false,true);
		List<Respuesta> respuestas = new ArrayList<>();
		respuestas.add(r1);
		respuestas.add(r2);
		pregunta.setRespuestas(respuestas);
		
		SAPregunta saPregunta = FactoriaNegocio.getInstance().generateSAPregunta();
		
		idAlpha = (Integer) saPregunta.create(pregunta).getDato();
		
		contexto = saPregunta.activatePregunta(idAlpha);
		
		idBeta = (Integer) contexto.getDato();
		
		assertThat(
			"La pregunta ya existe.", 
			idAlpha != null && idBeta != null && idAlpha >= 0 && idAlpha >= 0 && idAlpha == idBeta, 
			is(equalTo(true)) 
		);
		
		assertThat(
			"No es posible de activarse pues ya esta activa.", 
			contexto.getEvent(),
			is(equalTo(Events.PREGUNTA_ACTIVATE_KO)) 
		);
		
		saPregunta.borrarPregunta(idAlpha);
	
	}

	/**
	 * Test que comprueba que no se pueda activar una pregunta inexistente.
	 */
	@Test
	public void activarPreguntaInexistentePreguntaTest() {
		
		Integer id = null;
		
		SAPregunta saPregunta = FactoriaNegocio.getInstance().generateSAPregunta();
		
		Pregunta pregunta = new Pregunta("¿ Es XOR un conjunto universal ?",true);
		
		Respuesta r1 = new Respuesta("Si",true,true);
		Respuesta r2 = new Respuesta("No",false,true);
		List<Respuesta> respuestas = new ArrayList<>();
		respuestas.add(r1);
		respuestas.add(r2);
		pregunta.setRespuestas(respuestas);
		
		id = (Integer) saPregunta.create(pregunta).getDato();
		
		saPregunta.borrarPregunta(id);
		
		Contexto contexto = FactoriaNegocio.getInstance().generateSAPregunta().activatePregunta(id);
		
		assertThat(
			"La pregunta no tiene un indice asignado.", 
			contexto.getDato() != null && (Integer) contexto.getDato() >= 0, 
			is(equalTo(true))
		);
			
		assertThat(
			"No es posible de activar una pregunta inexistente.", 
			contexto.getEvent(),
			is(equalTo(Events.PREGUNTA_ACTIVATE_KO)) 
		);
		
	}
	
	/**
	 * Test que comprueba que no se pueda desactivar una pregunta inexistente.
	 */
	@Test
	public void desactivarPreguntaParametroIncorrectoPreguntaTest() {
		
		Contexto contexto = FactoriaNegocio.getInstance().generateSAPregunta().deactivatePregunta(-1);
		
		assertThat(
			"La pregunta no tiene un indice asignado.", 
			contexto.getDato(), 
			nullValue() 
		);
			
		assertThat(
			"No es posible de desactivarse la pregunta con un id negativo.", 
			contexto.getEvent(),
			is(equalTo(Events.PREGUNTA_DESACTIVATE_KO)) 
		);
		
	}
	
	/**
	 * Test que comprueba que las preguntas se desactivan correctamente.
	 */
	@Test
	public void desactivarPreguntaTest() {
		
		Integer idBeta = null;
		
		Integer idAlpha = null;
		
		Contexto contexto = null;
		
		Pregunta pregunta = new Pregunta("¿ Es NAND un conjunto universal ?",true);
		
		Respuesta r1 = new Respuesta("Si",true,true);
		Respuesta r2 = new Respuesta("No",false,true);
		List<Respuesta> respuestas = new ArrayList<>();
		respuestas.add(r1);
		respuestas.add(r2);
		pregunta.setRespuestas(respuestas);
		
		SAPregunta saPregunta = FactoriaNegocio.getInstance().generateSAPregunta();
		
		idAlpha = (Integer) saPregunta.create(pregunta).getDato();
		
		contexto = saPregunta.deactivatePregunta(idAlpha);
		
		idBeta = (Integer) contexto.getDato();
		
		assertThat(
			"La pregunta ya existe.", 
			idAlpha != null && idBeta != null && idAlpha >= 0 && idAlpha >= 0 && idAlpha == idBeta, 
			is(equalTo(true)) 
		);
		
		assertThat(
			"La pregunta ha debido de desactivarse correctamente.", 
			contexto.getEvent(),
			is(equalTo(Events.PREGUNTA_DESACTIVATE_OK)) 
		);
		
		saPregunta.borrarPregunta(idAlpha);
		
	}
	
	/**
	 * Test que comprueba que no se  pueda desactivar una pregunta ya desactivada.
	 */
	@Test
	public void desactivarPreguntaYaDesactivadaPreguntaTest() {
		
		Integer idBeta = null;
		
		Integer idAlpha = null;
		
		Contexto contexto = null;
		
		Pregunta pregunta = new Pregunta("¿ Es NOR un conjunto universal ?",false);
		
		Respuesta r1 = new Respuesta("Si",true,true);
		Respuesta r2 = new Respuesta("No",false,true);
		List<Respuesta> respuestas = new ArrayList<>();
		respuestas.add(r1);
		respuestas.add(r2);
		pregunta.setRespuestas(respuestas);
		
		SAPregunta saPregunta = FactoriaNegocio.getInstance().generateSAPregunta();
		
		idAlpha = (Integer) saPregunta.create(pregunta).getDato();
		
		contexto = saPregunta.deactivatePregunta(idAlpha);
		
		idBeta = (Integer) contexto.getDato();
		
		assertThat(
			"La pregunta ya existe.", 
			idAlpha != null && idBeta != null && idAlpha >= 0 && idAlpha >= 0 && idAlpha == idBeta, 
			is(equalTo(true)) 
		);
		
		assertThat(
			"No es posible de activarse pues ya esta desactivada.", 
			contexto.getEvent(),
			is(equalTo(Events.PREGUNTA_DESACTIVATE_KO)) 
		);
		
		saPregunta.borrarPregunta(idAlpha);
	
	}
	
	/**
	 * Test que comprueba que no se puede activar una pregunta inexitente.
	 */
	@Test
	public void desactivarPreguntaInexistentePreguntaTest() {
		
		Integer id = null;
		
		SAPregunta saPregunta = FactoriaNegocio.getInstance().generateSAPregunta();
		
		Pregunta pregunta = new Pregunta("¿ Es AND, NOT, OR un conjunto universal ?",true);
		
		Respuesta r1 = new Respuesta("Si",true,true);
		Respuesta r2 = new Respuesta("No",false,true);
		List<Respuesta> respuestas = new ArrayList<>();
		respuestas.add(r1);
		respuestas.add(r2);
		pregunta.setRespuestas(respuestas);
		
		id = (Integer) saPregunta.create(pregunta).getDato();
		
		saPregunta.borrarPregunta(id);
		
		Contexto contexto = FactoriaNegocio.getInstance().generateSAPregunta().deactivatePregunta(id);
		
		assertThat(
			"La pregunta no tiene un indice asignado.", 
			contexto.getDato() != null && (Integer) contexto.getDato() >= 0, 
			is(equalTo(true))
		);
			
		assertThat(
			"No es posible de desactivar una pregunta inexistente.", 
			contexto.getEvent(),
			is(equalTo(Events.PREGUNTA_DESACTIVATE_KO)) 
		);
		
	}
	
}
