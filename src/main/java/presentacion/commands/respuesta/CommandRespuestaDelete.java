package presentacion.commands.respuesta;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandRespuestaDelete implements Command{

	@Override
	public Contexto execute(Object data) {
		String respuesta = (String) data;
		return FactoriaNegocio.getInstance().generateSARespuesta().borrarRespuesta(respuesta);
	}

}
