
package presentacion.controlador;

import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.command.FactoriaCommand;
import presentacion.dispatcher.Dispatcher;

public class ControladorImp extends Controlador {

	public void accion(Contexto contexto) {
		
		Command command = FactoriaCommand.getInstance().generateCommand(contexto.getEvent());
		
		if(command != null) {
		
			// Real
			
			// Contexto contextoResult = command.execute(contexto.getDato());
			
			// Dispatcher.getInstance().updateView(contextoResult);
			
			// Pruebas
			
			Dispatcher.getInstance().updateView(new Contexto(Events.USER_LOGIN_OK, contexto.getDato()));
		
		} else {
			
			Dispatcher.getInstance().generateView(contexto);
	
		}
			
	}
	
}