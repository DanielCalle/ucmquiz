package presentacion.guiControlers;

import presentacion.GUI;

public abstract class MenuProfesorPreguntasController implements GUI {

public static MenuProfesorPreguntasController instance;
	
	public static MenuProfesorPreguntasController getInstance() {
		if(instance == null) instance = new MenuProfesorPreguntasControllerImp();
		return instance;
	}
	
}
