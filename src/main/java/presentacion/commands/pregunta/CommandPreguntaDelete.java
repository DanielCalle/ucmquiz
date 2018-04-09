package presentacion.commands.pregunta;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandPreguntaDelete implements Command {

	@Override
	public Contexto execute(Object data) {
		int idPregunta = (int) data;
		return FactoriaNegocio.getInstance().generateSAPregunta().borrarPregunta(idPregunta);
	}

}
