package negocio.respuesta;


import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import negocio.FactoriaNegocio;

import presentacion.Contexto;
import presentacion.Events;


public class SARespuestaTest {
	
	//Descomentar cuando haya un createRespuesta
	@Test
	public void borrarRespuestaTest() {
		Respuesta r = new Respuesta("Hector", true, true);
		
		SARespuesta sar = new SARespuestaImp();
		
		Contexto c = sar.create(r);
		
		Integer id = (Integer) c.getDato();
		
		c = sar.borrarRespuesta(id);
		
		assertEquals("El evento de la operacion Delete en Respuesta tiene que estar OK"
				,c.getEvent(),
				Events.CRUD_DELETE_RESPUESTA_OK);
	}

	@Test
	public void testCrearRespuesta() {
		
		Respuesta respuesta = new Respuesta("Esto es una respuesta", true, true);
		
		Contexto contexto = FactoriaNegocio.getInstance().generateSARespuesta().create(respuesta);
		
		Integer id = (Integer) contexto.getDato();
		
		assertTrue(
			"Si la respuesta no existe el id es positivo, si existe el id es null",  
			id >= 0 ||  id == null
		);
		
	}
	
	@Test
	public void testCrearRespuestaNula() {
		
		assertNull(
			"No se puede crear una respuesta nula.", 
			FactoriaNegocio.getInstance().generateSARespuesta().create(null).getDato()
		);
		
	}
	
	@Test
	public void testCrearRespuestaNoActiva() {
		
		Respuesta respuesta = new Respuesta("Esto es una respuesta", true, false);
		
		Contexto contexto = FactoriaNegocio.getInstance().generateSARespuesta().create(respuesta);
	
		Integer id = (Integer) contexto.getDato();
		
		assertTrue( 
			"Es posible meter una asignatura no activa. " +
			"Si se mete una segunda vez, esta es reactivada automaticamente " +
			"si su estado de activo en la BBDD es false, en caso contrario devuelve null."
			,  
			id == null || id > 0
		);
	
	}
	
//	------------ Test de integración de respuesta -----------------
	
	@Test
	public void respuestaIntegrationTest() {
		
//		#Create respuesta activa
		Respuesta respuesta = new Respuesta("Esto es una respuesta activa", true, true);
		Contexto contexto = FactoriaNegocio.getInstance().generateSARespuesta().create(respuesta);
		
		assertEquals("El evento de la operacion CREATE en Respuesta tiene que estar OK"
				,contexto.getEvent(),
				Events.CRUD_CREATE_RESPUESTA_OK);
		
//		#Create respuesta inactiva
		respuesta = new Respuesta("Esto es una respuesta inactiva", true, false);
		contexto = FactoriaNegocio.getInstance().generateSARespuesta().create(respuesta);
		Integer id = (Integer) contexto.getDato();
		
		assertTrue( 
			"Es posible meter una respuesta no activa. " +
			"Si se mete una segunda vez, esta es reactivada automaticamente " +
			"si su estado de activo en la BBDD es false, en caso contrario devuelve null."
			,  
			id == null || id > 0
		);
		
//		#Create respuesta null
		assertNull(
				"No se puede crear una respuesta nula.", 
				FactoriaNegocio.getInstance().generateSARespuesta().create(null).getDato()
			);
			
//		#Borrar respuesta
		contexto = FactoriaNegocio.getInstance().generateSARespuesta().borrarRespuesta(id);
		assertEquals("El evento de la operacion Delete en Respuesta tiene que estar OK"
				,contexto.getEvent(),
				Events.CRUD_DELETE_RESPUESTA_OK);
	}

}
