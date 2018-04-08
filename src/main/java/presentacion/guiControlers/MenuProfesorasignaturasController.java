package presentacion.guiControlers;

import presentacion.GUI;

public abstract class MenuProfesorasignaturasController implements GUI {
	
	public static MenuProfesorasignaturasController instance;
	
	public static MenuProfesorasignaturasController getInstance() {
		if(instance == null) instance = new MenuProfesorasignaturasControllerImp();
		return instance;
	}
}
