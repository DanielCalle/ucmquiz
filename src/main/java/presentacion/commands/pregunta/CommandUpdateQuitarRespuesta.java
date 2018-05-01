package presentacion.commands.pregunta;

import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandUpdateQuitarRespuesta implements Command {

	@Override
	public Contexto execute(Object data) {
		return new Contexto(Events.UPDATE_QUITAR_RESPUESTA,data);
	}

}
