package presentacion.commands.pregunta;

import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandQuitarRespuestaOk implements Command {

	@Override
	public Contexto execute(Object data) {
		return new Contexto(Events.QUITAR_RESPUESTA_OK,data);
	}

}