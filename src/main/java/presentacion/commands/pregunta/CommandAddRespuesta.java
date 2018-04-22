package presentacion.commands.pregunta;

import negocio.FactoriaNegocio;
import negocio.pregunta.Pregunta;
import presentacion.Command;
import presentacion.Contexto;

public class CommandAddRespuesta implements Command{

	@Override
	public Contexto execute(Object data) {
		
		return FactoriaNegocio.getInstance().generateSAPregunta().añadirRespuesta(idPregunta, respuesta);
	}

}
