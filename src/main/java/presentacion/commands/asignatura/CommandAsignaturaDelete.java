package presentacion.commands.asignatura;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandAsignaturaDelete implements Command {

	@Override
	public Contexto execute(Object data) {
		Integer id = (Integer) data;
		return FactoriaNegocio.getInstance().generateSAAsignatura().delete(id);
	}
	
}
