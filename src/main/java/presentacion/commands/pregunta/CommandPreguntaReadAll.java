package presentacion.commands.pregunta;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandPreguntaReadAll implements Command{

	@Override
	public Contexto execute(Object data) {
		// TODO Auto-generated method stub
		return FactoriaNegocio.getInstance().generateSAPregunta().readAll();
	}
}
