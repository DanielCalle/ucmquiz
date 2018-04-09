package presentacion.commands.asignatura;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandListarAsignaturas implements Command{

	@Override
	public Contexto execute(Object data) {
		return FactoriaNegocio.getInstance().generateSAAsignatura().readAll();
	}

}
