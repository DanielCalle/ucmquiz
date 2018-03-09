
package presentacion.dispatcher;

import presentacion.Contexto;

public abstract class Dispatcher {

	private static Dispatcher instance;

	public static Dispatcher getInstance() {
		if(instance == null)
			instance = new DispatcherImp();
		return instance;
	}

	public abstract void updateView(Contexto contexto);
	
	public abstract void generateView(Contexto contexto);
	
}