package negocio.asignatura;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class SAAsignaturaTest {

	@Test
	public void testCrearAsignatura() {
		
		Asignatura asignatura = new Asignatura("MMI", true);
		
		SAAsignatura sa = new  SAAsignaturaImp();
		
		int id = sa.create(asignatura);
		
		assertTrue("Si la asignatura no existe id > 0, si existe id = -1.", id > 0 || id == -1);
		
	}
	
	@Test
	public void testCrearAsignaturaNula() {
		
		Asignatura asignatura = null;
		
		SAAsignatura sa = new  SAAsignaturaImp();
		
		int id = sa.create(asignatura);
	
		assertThat( "No se puede crear una asignatura nula." , id , IsEqual.equalTo(-1) );
	
	}
	
	@Test
	public void testCrearAsignaturaNoActiva() {
		
		Asignatura asignatura = new Asignatura("GPS", false);
		
		SAAsignatura sa = new  SAAsignaturaImp();
		
		int id = sa.create(asignatura);
	
		assertTrue( 
			"Es posible meter una asignatura no activa. " +
			"Si se mete una segunda vez, esta es reactivada automaticamente " +
			"si su estado de activo en la BBDD es false, en caso contrario devuelve -1."
			,  
			id > 0 || id == -1
		);
	
	}
	
}
