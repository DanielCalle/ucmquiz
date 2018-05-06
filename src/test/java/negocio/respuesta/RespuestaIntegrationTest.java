package negocio.respuesta;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import negocio.FactoriaNegocio;
import presentacion.Contexto;
import presentacion.Events;

public class RespuestaIntegrationTest {

	@Test
	public void integrationTest() {

		Integer id = null;
		
		Events events = null;
		
		Contexto contexto = null;

		Respuesta respuesta = null;
		
		FactoriaNegocio factoriaNegocio = FactoriaNegocio.getInstance();
		
		SARespuesta saRespuesta = factoriaNegocio.generateSARespuesta();

		contexto = saRespuesta.create(respuesta);
		
		events = contexto.getEvent();
		
		id = (Integer) contexto.getDato();
		
		assertThat(
			"No se puede crear una respuesta nula.", 
			(id == null) && (events == Events.CRUD_CREATE_RESPUESTA_KO) , is(equalTo(true))
		);
		
		respuesta = new Respuesta("El conjunto de iteraciones recibe el nombre de fase.", true, false);
		
		contexto = saRespuesta.create(respuesta);
		
		events = contexto.getEvent();
		
		id = (Integer) contexto.getDato();
		
		assertThat( 
			"Es posible meter una respuesta no activa. " +
			"Si se mete una segunda vez, esta es reactivada automaticamente " +
			"si su estado de activo en la BBDD es false, en caso contrario devuelve null."
			, (id == null || id >= 0) && (events == Events.CRUD_CREATE_RESPUESTA_OK) , is(equalTo(true))
		);
		
		contexto = saRespuesta.borrarRespuesta(id);
		
		events = contexto.getEvent();
		
		id = (Integer) contexto.getDato();
		
		assertThat(
			"La operacion debe devolver el identificador de la respuesta" +
			" y el estado de la operacion en OK."
			, (id >= 0) && (events == Events.CRUD_DELETE_RESPUESTA_OK) , is(equalTo(true)) 
		);
		
		contexto = saRespuesta.borrarRespuesta(id);
		
		events = contexto.getEvent();
		
		id = (Integer) contexto.getDato();
		
		assertThat(
			"No se puede eliminar una respuesta que no existe."
			, (id == null) && (events == Events.CRUD_DELETE_RESPUESTA_KO) , is(equalTo(true)) 
		);
		
		contexto = saRespuesta.borrarRespuesta("El conjunto de iteraciones recibe el nombre de fase.");
		
		events = contexto.getEvent();
		
		id = (Integer) contexto.getDato();
		
		assertThat(
			"No se puede eliminar una respuesta que no existe."
			, (id == null) && (events == Events.CRUD_DELETE_RESPUESTA_KO) , is(equalTo(true)) 
		);
		
		respuesta = new Respuesta("El conjunto de iteraciones recibe el nombre de fase.", true, true);
		
		contexto = saRespuesta.create(respuesta);
		
		events = contexto.getEvent();
		
		id = (Integer) contexto.getDato();
		
		assertThat( 
			"Creamos una pregunta que es correcta y su estado es activo."
			, (id >= 0) && (events == Events.CRUD_CREATE_RESPUESTA_OK) , is(equalTo(true))
		);
		
		contexto = saRespuesta.borrarRespuesta("El conjunto de iteraciones recibe el nombre de fase.");
		
		events = contexto.getEvent();
		
		id = (Integer) contexto.getDato();
		
		assertThat( 
			"Borramos una pregunta existente."
			, (id >= 0) && (events == Events.CRUD_DELETE_RESPUESTA_OK) , is(equalTo(true))
		);
		
	}

}
