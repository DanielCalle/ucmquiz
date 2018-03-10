package presentacion.commands;

import negocio.FactoriaNegocio;
import negocio.respuesta.Respuesta;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandCreateAnswer implements Command {

	@Override
	public Contexto execute(Object data) {
		
		Contexto context = null;
		
		int idAnswer = FactoriaNegocio.getInstance().generateSAREspuesta().create(((Respuesta)data));
		
		if(idAnswer < 0)
			
			context = new Contexto(Events.CREATE_ANSWER_KO, null);
		
		else
			
			context = new Contexto(Events.CREATE_ANSWER_OK, idAnswer);
		
		return context;
		
	}

}
