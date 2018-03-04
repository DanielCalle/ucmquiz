
package presentacion.dispatcher;

import presentacion.Contexto;

public abstract class Dispatcher {

	private static Dispatcher instance;

	public static Dispatcher getInstance() {
		if(instance == null)
			instance = new DispatcherImp();
		return instance;
	}

	public abstract void generateVista(Contexto contexto);
}