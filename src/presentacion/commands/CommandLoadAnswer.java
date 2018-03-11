package presentacion.commands;

import java.util.List;

import negocio.FactoriaNegocio;
import negocio.respuesta.Respuesta;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandLoadAnswer implements Command {

	@Override
	public Contexto execute(Object data) {
		
		Contexto context  = null;
		
		List<Respuesta> respuestas = FactoriaNegocio.getInstance().generateSAREspuesta().readAll();
		
		if(respuestas != null && respuestas.size() > 0)
			
			context = new Contexto(Events.LOAD_ANSWER_OK, respuestas);
		
		else
		
			context = new Contexto(Events.LOAD_ANSWER_KO, null);
		
		return context;
		
	}

}
