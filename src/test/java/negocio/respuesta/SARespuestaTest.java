package negocio.respuesta;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import presentacion.Contexto;
import presentacion.Events;

public class SARespuestaTest {
	
	//Descomentar cuando haya un createRespuesta
//	@Test
//	public void borrarRespuestaTest() {
//		Respuesta r = new Respuesta("Hector", true, true);
//		
//		SARespuesta sar = new SARespuestaImp();
//		
//		Contexto c = sar.create(r);
//		
//		Integer id = (Integer) c.getDato();
//		
//		c = sar.borrarRespuesta(id);
//		
//		assertEquals("El evento de la operacion Delete en Respuesta tiene que estar OK"
//				,c.getEvent(),
//				Events.CRUD_DELETE_RESPUESTA_OK);
//	}
}
