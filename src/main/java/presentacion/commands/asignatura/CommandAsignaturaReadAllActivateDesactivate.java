package presentacion.commands.asignatura;

import java.util.List;

import negocio.FactoriaNegocio;
import negocio.asignatura.Asignatura;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandAsignaturaReadAllActivateDesactivate implements Command {

	@Override
	public Contexto execute(Object data) {
		Contexto contexto = FactoriaNegocio.getInstance().generateSAAsignatura().readAll();
		if (contexto.getDato() == null)
			contexto.setEvent(Events.ASIGNATURA_READ_ALL_ACTIVATE_DESACTIVATE_KO);
		else contexto.setEvent(Events.ASIGNATURA_READ_ALL_ACTIVATE_DESACTIVATE_OK);
		return contexto;
	}

}
