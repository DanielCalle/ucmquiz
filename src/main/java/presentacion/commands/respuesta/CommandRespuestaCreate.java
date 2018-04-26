package presentacion.commands.respuesta;

import negocio.FactoriaNegocio;
import negocio.respuesta.Respuesta;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandRespuestaCreate implements Command {
	
	@Override
	public Contexto execute(Object data) {
		Respuesta respuesta = (Respuesta) data;
		return new Contexto(Events.CRUD_CREATE_RESPUESTA_OK, respuesta);
	}
	
}
