package presentacion.command;

import java.util.ArrayList;

import negocio.FactoriaNegocio;
import negocio.pregunta.Pregunta;
import negocio.respuesta.Respuesta;
import negocio.usuario.Usuario;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandCreateQuestion implements Command{

	@Override
	public Contexto execute(Object data) {

		Contexto context = null;
		
		ArrayList<String> datos = (ArrayList<String>)data;
		
		Pregunta p = new Pregunta(datos.get(0),true);
		
		int id = FactoriaNegocio.getInstance().generateSAPregunta().create(p);
		
	
		Pregunta pregunta = new Pregunta();
		pregunta.setId(id);
		
		Respuesta respuestaCorrecta = new Respuesta(true,datos.get(1),pregunta);
		FactoriaNegocio.getInstance().generateSAREspuesta().create(respuestaCorrecta);
		Respuesta respuestaINCorrecta1 = new Respuesta(false,datos.get(2),pregunta);
		FactoriaNegocio.getInstance().generateSAREspuesta().create(respuestaINCorrecta1);
		Respuesta respuestaINCorrecta2 = new Respuesta(false,datos.get(3),pregunta);
		FactoriaNegocio.getInstance().generateSAREspuesta().create(respuestaINCorrecta2);
		
		
		if(id < 0)
			
			context = new Contexto(Events.CREATE_QUESTION_OK, id);
		
		else
			
			context = new Contexto(Events.CREATE_QUESTION_KO, id);
		
		return context;
	}



}
