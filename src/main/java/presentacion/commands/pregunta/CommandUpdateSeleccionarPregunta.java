package presentacion.commands.pregunta;

import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandUpdateSeleccionarPregunta implements Command{

	@Override
	public Contexto execute(Object data) {
		// TODO Auto-generated method stub
		return new Contexto(Events.UPDATE_SELECCIONAR_PREGUNTA,data);
	}

}
