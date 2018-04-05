
package presentacion.commands;

import presentacion.Command;
import presentacion.Events;

public abstract class FactoriaCommand {

	private static FactoriaCommand instance;

	public static FactoriaCommand getInstance() {
		if(instance == null)
			instance = new FactoriaCommandImp();
		return instance;
	}

	public abstract Command generateCommand(Events event);
	
}