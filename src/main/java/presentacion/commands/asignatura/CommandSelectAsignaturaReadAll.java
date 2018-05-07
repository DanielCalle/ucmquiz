package presentacion.commands.asignatura;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandSelectAsignaturaReadAll implements Command {

	@Override
	public Contexto execute(Object data) {
		Contexto contexto = FactoriaNegocio.getInstance().generateSAAsignatura().readAll();
		if (contexto.getDato() == null)
			contexto.setEvent(Events.SELECT_ASIGNATURA_READ_ALL_KO);
		else contexto.setEvent(Events.SELECT_ASIGNATURA_READ_ALL_OK);
		return contexto;
	}

}
