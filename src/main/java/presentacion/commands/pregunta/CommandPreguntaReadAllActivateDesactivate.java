package presentacion.commands.pregunta;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandPreguntaReadAllActivateDesactivate implements Command{

	@Override
	public Contexto execute(Object data) {
		Contexto contexto = FactoriaNegocio.getInstance().generateSAPregunta().readAll();
		if (contexto.getDato() == null)
			contexto.setEvent(Events.PREGUNTA_READ_ALL_ACTIVATE_DESACTIVATE_KO);
		else contexto.setEvent(Events.PREGUNTA_READ_ALL_ACTIVATE_DESACTIVATE_OK);
		return contexto;
	}
	

}
