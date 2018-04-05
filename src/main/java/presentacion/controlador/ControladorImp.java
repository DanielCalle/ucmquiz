
package presentacion.controlador;

import presentacion.Command;
import presentacion.Contexto;
import presentacion.commands.FactoriaCommand;
import presentacion.dispatcher.Dispatcher;

public class ControladorImp extends Controlador {

	public void accion(Contexto contexto) {
		
		Dispatcher dispatcher = Dispatcher.getInstance();
		
		Command command = FactoriaCommand.getInstance().generateCommand(contexto.getEvent());

		if(command != null)

			dispatcher.updateView(command.execute(contexto));
		
		else
			
			dispatcher.generateView(contexto);
		
	}
	
}