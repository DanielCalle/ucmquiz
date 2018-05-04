package presentacion.commands.respuesta;

import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandRespuestaGetList implements Command{
	
	@Override
	public Contexto execute(Object data) {
		return new Contexto(Events.SHOW_RESPUESTA_DELETE, data);
	}
}
