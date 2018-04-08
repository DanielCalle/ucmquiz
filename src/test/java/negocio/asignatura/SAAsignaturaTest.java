package negocio.asignatura;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import presentacion.Contexto;

public class SAAsignaturaTest {

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
		
		Asignatura asignatura = null;
		
		SAAsignatura sa = new  SAAsignaturaImp();
		
		Contexto contexto = sa.create(asignatura);
	
		Integer id = (Integer) contexto.getDato();
		
		assertNull(
			"No se puede crear una asignatura nula.", 
			id
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
	
}
