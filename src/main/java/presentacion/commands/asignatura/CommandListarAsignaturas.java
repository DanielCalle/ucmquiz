package presentacion.commands.asignatura;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandListarAsignaturas implements Command{

	@Override
	public Contexto execute(Object data) {
		Contexto c = FactoriaNegocio.getInstance().generateSAAsignatura().readAll();
		return c;
	}

}
