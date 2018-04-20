package negocio.respuesta;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import negocio.FactoriaNegocio;
import negocio.asignatura.Asignatura;
import negocio.asignatura.SAAsignatura;
import negocio.asignatura.SAAsignaturaImp;
import presentacion.Contexto;

public class SARespuestaTest {

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
	
}
