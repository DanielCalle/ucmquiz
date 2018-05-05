package presentacion.guiControlers;

import presentacion.GUI;

public abstract class BorrarPreguntaProfesorAdminController implements GUI {

	public static BorrarPreguntaProfesorAdminController instance;
	
	public static BorrarPreguntaProfesorAdminController getInstance() {
		if(instance == null) instance = new BorrarPreguntaProfesorAdminControllerImp();
		return instance;
	}
}