package presentacion.commands.asignatura;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandAsignaturaRead implements Command {

	@Override
	public Contexto execute(Object data) {
		int idAsignatura = (int) data;
		return FactoriaNegocio.getInstance().generateSAAsignatura().read(idAsignatura);
	}

}
