package negocio.pregunta;

import static org.hamcrest.Matchers.equalTo;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

public class SAPreguntaTest {

	@Test
	public void testCrearPregunta() {
		
		Pregunta p = new Pregunta("¿ Cual es la definicion sencilla del teorema de la integral de Riemann ?", true);
		
		SAPreguntaImp sa = new SAPreguntaImp();
		
		int id = sa.create(p);
		
		assertTrue(
			"Si la pregunta ya esta en la BBDD se ha de retornar " +
			"-1, si no el identificador ha de ser un numero positivo."
			, 
			id == -1 || id > 0
		);
		
	}
	
	@Test
	public void testCrearPreguntaNula() {
		
		Pregunta p = null;
				
		SAPreguntaImp sa = new SAPreguntaImp();
		
		assertThat("No se puede crear una pregunta nula.", sa.create(p), equalTo(-1));
		
	}
	
	@Test
	public void testCrearPreguntaInactiva() {
		 
		Pregunta p = new Pregunta("¿ Cual es la definicion de Kernel para un sistema operativo ?", false);
		
		SAPreguntaImp sa = new SAPreguntaImp();
		
		int id = sa.create(p);
		
		assertTrue(
			"Podemos insertar una pregunta desactivada, su identificador " +
			"es positivo. Si se vuelve a reinsertar y su estado de activacion " + 
			"en la BBDD es false, esta es automaticamente reactivada. Si ya existia " +
			"y su estado de activacion era true, entonces se ha de retornar -1."
			, 
			id == -1 || id > 0
		);
	}
	
}
