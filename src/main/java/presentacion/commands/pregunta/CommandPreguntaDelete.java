package presentacion.commands.pregunta;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandPreguntaDelete implements Command {

	@Override
	public Contexto execute(Object data) {
		int idPregunta = (int) data;
		Contexto aux = FactoriaNegocio.getInstance().generateSAPregunta().borrarPregunta(idPregunta);
		
		if(aux.getEvent() == Events.CRUD_DELETE_PREGUNTA_OK) aux.setEvent(Events.PREGUNTA_DELETE_PROFESOR_ADMIN_OK);
		else aux.setEvent(Events.PREGUNTA_DELETE_PROFESOR_ADMIN_KO);
		
		return aux;
	}

}
