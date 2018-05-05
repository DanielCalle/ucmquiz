package presentacion.commands.pregunta;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandPreguntaRead implements Command {

	@Override
	public Contexto execute(Object data) {
		int idPregunta = (int) data;
		return FactoriaNegocio.getInstance().generateSAPregunta().read(idPregunta);
	}

}
