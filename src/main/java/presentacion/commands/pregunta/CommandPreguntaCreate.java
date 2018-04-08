package presentacion.commands.pregunta;

import negocio.FactoriaNegocio;
import negocio.pregunta.Pregunta;
import presentacion.Command;
import presentacion.Contexto;

public class CommandPreguntaCreate implements Command{

	@Override
	public Contexto execute(Object data) {
		Pregunta pregunta = (Pregunta) data;
		return FactoriaNegocio.getInstance().generateSAPregunta().create(pregunta);
	}

}
