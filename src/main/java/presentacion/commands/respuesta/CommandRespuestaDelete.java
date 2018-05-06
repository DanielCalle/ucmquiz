package presentacion.commands.respuesta;

import negocio.FactoriaNegocio;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandRespuestaDelete implements Command{

	@Override
	public Contexto execute(Object data) {
		String respuesta = (String) data;
		Contexto aux =  FactoriaNegocio.getInstance().generateSARespuesta().borrarRespuesta(respuesta);
		if(aux.getEvent() == Events.CRUD_DELETE_RESPUESTA_OK) aux.setEvent(Events.RESPUESTA_DELETE_ADMIN_OK);
		else aux.setEvent(Events.RESPUESTA_DELETE_ADMIN_KO);
		
		return aux;
	}
	

}
