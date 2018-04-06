package negocio.asignatura;

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

}
