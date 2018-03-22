
package presentacion.controlador;

import presentacion.Contexto;

public abstract class Controlador {

	private static Controlador instance;

	public static Controlador getInstance() {
		if(instance == null)
			instance = new ControladorImp();
		return instance;
	}

	public abstract void accion(Contexto contexto);
}