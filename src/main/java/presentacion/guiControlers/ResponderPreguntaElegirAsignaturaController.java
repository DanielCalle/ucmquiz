package presentacion.guiControlers;

import presentacion.GUI;

public abstract class ResponderPreguntaElegirAsignaturaController implements GUI {
	
	public static ResponderPreguntaElegirAsignaturaController instance;
	
	public static ResponderPreguntaElegirAsignaturaController getInstance() {
		if(instance == null) instance = new ResponderPreguntaElegirAsignaturaControllerImp();
		return instance;
	}

}