package presentacion.commands.pregunta;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandPreguntaDesactivate implements Command {

	@Override
	public Contexto execute(Object data) {
		int id = (int) data;
		return FactoriaNegocio.getInstance().generateSAPregunta().deactivatePregunta(id);
	}

}
