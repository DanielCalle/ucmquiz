package presentacion.commands;

import java.util.List;

import negocio.FactoriaNegocio;
import negocio.pregunta.Pregunta;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandLoadQuestion implements Command {

	@Override
	public Contexto execute(Object data) {
		
		Contexto context = null;
		
		List<Pregunta> questionList = FactoriaNegocio.getInstance().generateSAPregunta().readAll();
		
		if(questionList == null)
		
			context = new Contexto(Events.LOAD_QUESTION_KO, null);
		
		else
			
			context = new Contexto(Events.LOAD_QUESTION_OK, questionList.get(0));
		
		return context;
	
	}

}
