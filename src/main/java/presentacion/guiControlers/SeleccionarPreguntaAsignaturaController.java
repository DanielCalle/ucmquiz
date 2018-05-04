package presentacion.guiControlers;

import presentacion.GUI;

public abstract class SeleccionarPreguntaAsignaturaController implements GUI {
	
	public static SeleccionarPreguntaAsignaturaController instance;
	
	public static SeleccionarPreguntaAsignaturaController getInstance() {
		if(instance == null) instance = new SeleccionarPreguntaAsignaturaControllerImp();
		return instance;
	}

}
