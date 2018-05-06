package presentacion.commands.pregunta;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandPreguntaRead implements Command {

	/**
	 * Comando que ejecuta el read de una pregunta
	 * @param data Id de la pregunta
	 * @return Contexto con la pregunta correspondiente si se encontro y null si no
	 * @author Zihao
	 */
	@Override
	public Contexto execute(Object data) {
		int idPregunta = (int) data;
		return FactoriaNegocio.getInstance().generateSAPregunta().read(idPregunta);
	}

}
