package presentacion.commands.asignatura;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandAsignaturaDesactivate implements Command {

	@Override
	public Contexto execute(Object data) {
		int id = (int) data;
		return FactoriaNegocio.getInstance().generateSAAsignatura().desactiveAsignatura(id);
	}

}
