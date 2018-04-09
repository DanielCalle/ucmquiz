package negocio.asignatura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import negocio.FactoriaNegocio;
import presentacion.Contexto;
import presentacion.Events;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import presentacion.Filter;

public class SAAsignaturaTest {
	
	/**
	 * 
	 * Test para probar la clase activaAsignatura
	 * Se ingresa el id de una asignatura y se espera COMMAND_ASIGNATURA_ACTIVATE si se ha hecho correctamente
	 */
	
	@Test
	public void testActivaAsignatura () {
	
		
		Asignatura asignatura = new Asignatura("MDL", false);
		
		SAAsignatura sa = new  SAAsignaturaImp();
		
		Contexto contexto = sa.create(asignatura);
		
		int id = (int) contexto.getDato();
		
		contexto = sa.activeAsignatura(id);
		
		assertEquals("El evento de la operacion Activar en Asignatura tiene que dar el comando COMMAND_ASIGNATURA_ACTIVATE"
				,contexto.getEvent(),Events.ASIGNATURA_ACTIVATE_OK);
		
	
	}
	
	/**
	 * Test para probar la clase desactivaAsignatura
	 * Se ingresa el id de una asignatura y se espera COMMAND_ASIGNATURA_DESACTIVATE si se ha hecho correctamente
	 */
	
	@Test 
	public void testDesactivaAsignatura () {
		
		Filter filter = new Filter();
		filter
			.addFilter("entity", "asignatura")
			.addFilter("operation", "desactivar");
		
		Asignatura asignatura = new Asignatura("IS", true);
		
		SAAsignatura sa = new  SAAsignaturaImp();
		
		Contexto contexto = sa.create(asignatura);
		
		int id = (int) contexto.getDato();
		
		contexto = sa.desactiveAsignatura(id);
		
		assertEquals("El evento de la operacion Desactivar en Asignatura tiene que dar el comando COMMAND_ASIGNATURA_DESACTIVATE "
				,contexto.getEvent(),Events.ASIGNATURA_DESACTIVATE_OK.setFilter(filter));
//		assertEquals("El evento de la operacion Desactivar en Asignatura tiene que dar el comando COMMAND_ASIGNATURA_DESACTIVATE "
//				,contexto.getEvent(),Events.COMMAND_ASIGNATURA_DESACTIVATE.setFilter(filter));
	
		
	}
	
	@Test
	public void testCrearAsignatura() {
		
		Asignatura asignatura = new Asignatura("MMI", true);
		
		SAAsignatura sa = new  SAAsignaturaImp();
		
		Contexto contexto = sa.create(asignatura);
		
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
		
		SAAsignatura sa = new  SAAsignaturaImp();
		
		Contexto contexto = sa.create(asignatura);
	
		Integer id = (Integer) contexto.getDato();
		
		assertTrue( 
			"Es posible meter una asignatura no activa. " +
			"Si se mete una segunda vez, esta es reactivada automaticamente " +
			"si su estado de activo en la BBDD es false, en caso contrario devuelve null."
			,  
			id > 0 || id == null
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
