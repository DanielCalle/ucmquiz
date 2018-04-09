package presentacion.guiControlers;

import presentacion.GUI;

public abstract class BorrarAsignaturaController implements GUI {
	
	private static BorrarAsignaturaController instance;

	public synchronized static BorrarAsignaturaController getInstance() {
		if(instance == null) instance = new BorrarAsignaturaControllerImp();
		return instance;
	}

}
