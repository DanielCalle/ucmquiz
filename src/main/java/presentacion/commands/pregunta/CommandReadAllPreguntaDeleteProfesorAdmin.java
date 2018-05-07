package presentacion.commands.pregunta;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandReadAllPreguntaDeleteProfesorAdmin implements Command{
	@Override
	public Contexto execute(Object data) {
		// TODO Auto-generated method stub
		Contexto aux = FactoriaNegocio.getInstance().generateSAPregunta().readAll();
		
		if(aux.getEvent() == Events.CRUD_READ_ALL_PREGUNTA_OK) aux.setEvent(Events.CRUD_READALL_DELETE_PREGUNTA_PROFESOR_ADMIN_OK);
		else  aux.setEvent(Events.CRUD_READALL_DELETE_PREGUNTA_PROFESOR_ADMIN_KO);
		return aux;
	}
}
