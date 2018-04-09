package negocio.asignatura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import presentacion.Contexto;
import presentacion.Events;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class SAAsignaturaTest {
	
	@Test
	public void testActivaAsignatura () {
		boolean estado = false;
		
		//Asignatura asignatura = new Asignatura("MOG", false); //8
		SAAsignatura sa = new  SAAsignaturaImp();
		//int idAsig = sa.create(asignatura);
		//System.out.println(idAsig);
		
		//estado = sa.activaAsignatura(8);
		
		System.out.println(estado);
	
	
		assertThat(estado, IsEqual.equalTo(true));
	}
	
	@Test 
	public void testDesactivaAsignatura () {
		
		boolean estado = true;
		
		//Asignatura asignatura = new Asignatura("ASR", true); //7
		SAAsignatura sa = new  SAAsignaturaImp();
		//int idAsig = sa.create(asignatura);
		//System.out.println(idAsig);
		
		
		//estado = sa.desactivaAsignatura(7);
		
		System.out.println(estado);
	
	
		assertThat(estado, IsEqual.equalTo(false));
		
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
