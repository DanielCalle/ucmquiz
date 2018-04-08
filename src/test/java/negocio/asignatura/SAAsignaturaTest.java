package negocio.asignatura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import negocio.FactoriaNegocio;
import presentacion.Contexto;
import presentacion.Events;

public class SAAsignaturaTest {

	@Test
	public void testCrearAsignaturaNueva() {
		
		Asignatura asignatura = new Asignatura("ABD", true);
		
		SAAsignatura saAsignatura = FactoriaNegocio.getInstance().generateSAAsignatura();
		
		Contexto contexto = saAsignatura.create(asignatura);
		
		Integer id = (Integer) contexto.getDato();
		
		assertTrue(
			"Si la asignatura no existe el id es positivo, si existe el id es null",  
			id >= 0 ||  id == null
		);
		
	}
	
	@Test
	public void testCrearAsignaturaNula() {
	
		assertNull(
			"No se puede crear una asignatura nula.", 
			FactoriaNegocio.getInstance().generateSAAsignatura().create(null)
		);
		
	}
	
	@Test
	public void testCrearAsignaturaNoActiva() {
		
		Asignatura asignatura = new Asignatura("GPS", false);
	
		Integer id = (Integer) FactoriaNegocio.getInstance().generateSAAsignatura().create(asignatura).getDato();
		
		assertTrue( 
			"Es posible meter una asignatura no activa. " +
			"Si se mete una segunda vez, esta es reactivada automaticamente " +
			"si su estado de activo en la BBDD es false, en caso contrario devuelve null."
			,  
			id >= 0 || id == null
		);
	
	}
	
	@Test
	public void testBorrarAsignatura() {
		
		Asignatura asignatura = new Asignatura("GPS5", true);
		
		SAAsignatura sa = new  SAAsignaturaImp();
		
		Contexto contexto = sa.create(asignatura);
		
		Integer id = (Integer) contexto.getDato();
		
		contexto = sa.delete(id);
		
		assertEquals("El evento de la operacion Delete en Asignatura tiene que estar OK"
				,contexto.getEvent(),
				Events.CRUD_DELETE_ASIGNATURA_OK);
		
		
	}
	
}
