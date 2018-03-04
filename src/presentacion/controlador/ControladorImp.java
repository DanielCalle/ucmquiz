
package presentacion.controlador;

import presentacion.Command;
import presentacion.Contexto;
import presentacion.command.FactoriaCommand;
import presentacion.dispatcher.Dispatcher;

public class ControladorImp extends Controlador {

	public void accion(Contexto contexto) {
		
		Command command = FactoriaCommand.getInstance().generateCommand(contexto.getEvent());
		
		Contexto contextoResult = null;
		
		if(command != null) {
			
			contextoResult = command.execute(contexto.getDato());
		
			Dispatcher.getInstance().generateVista(contextoResult);
		
		} else {
			
			Dispatcher.getInstance().generateVista(contexto);
	
		}
			
	}
	
}