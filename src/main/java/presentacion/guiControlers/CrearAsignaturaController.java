package presentacion.guiControlers;

import presentacion.GUI;

public abstract class CrearAsignaturaController implements GUI {
	
	public static CrearAsignaturaController instance;
	
	public static CrearAsignaturaController getInstance () {
		
		if (instance == null)
			instance = new CrearAsignaturaControllerImp();
		
		return instance;
	}

}
