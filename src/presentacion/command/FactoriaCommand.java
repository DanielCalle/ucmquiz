
package presentacion.command;

import presentacion.Command;

public abstract class FactoriaCommand {

	private static FactoriaCommand instance;

	public static FactoriaCommand getInstance() {
		if(instance == null)
			instance = new FactoriaCommandImp();
		return instance;
	}

	public abstract Command generateCommand(int event);
}