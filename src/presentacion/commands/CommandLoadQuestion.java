package presentacion.commands;

import java.util.List;

import javax.swing.JOptionPane;

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
		
		if(questionList != null && questionList.size() > 0)

			context = new Contexto(Events.LOAD_QUESTION_OK, questionList.get(0));
		
		else {

			JOptionPane.showMessageDialog(null, "No hay preguntas.");
			context = new Contexto(Events.LOAD_QUESTION_KO, null);
			
		}
		
		return context;
	
	}

}
