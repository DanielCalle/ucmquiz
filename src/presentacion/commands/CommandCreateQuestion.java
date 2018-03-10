package presentacion.commands;

import negocio.FactoriaNegocio;
import negocio.pregunta.Pregunta;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandCreateQuestion implements Command {

	@Override
	public Contexto execute(Object data) {
		
		Contexto context = null;
		
		int questionId = FactoriaNegocio.getInstance().generateSAPregunta().create(((Pregunta)data));
		
		if(questionId < 0)
			
			context = new Contexto(Events.CREATE_QUESTION_KO, null);
			
		else
			
			context = new Contexto(Events.CREATE_QUESTION_OK, questionId);
		
		return context;
	
	}

}
