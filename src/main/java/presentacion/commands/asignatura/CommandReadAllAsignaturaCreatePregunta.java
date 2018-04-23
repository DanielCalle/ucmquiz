package presentacion.commands.asignatura;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandReadAllAsignaturaCreatePregunta implements Command{
	@Override
	public Contexto execute(Object data) {
		Contexto contexto = FactoriaNegocio.getInstance().generateSAAsignatura().readAll();
		if (contexto.getDato() == null)
			contexto.setEvent(Events.ASIGNATURA_READ_ALL_PREGUNTA_CREATE_KO);
		else contexto.setEvent(Events.ASIGNATURA_READ_ALL_PREGUNTA_CREATE_OK);
		return contexto;
	}

}
