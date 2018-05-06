package presentacion.commands.asignatura;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;

public class CommandAsignaturaRead implements Command {

	/**
	 * Comando que ejecuta el read de una asignatura
	 * @param data Id de la asignatura
	 * @return Contexto con la asignatura correspondiente si se encontro y null si no
	 * @author Zihao
	 */
	@Override
	public Contexto execute(Object data) {
		int idAsignatura = (int) data;
		return FactoriaNegocio.getInstance().generateSAAsignatura().read(idAsignatura);
	}

}
