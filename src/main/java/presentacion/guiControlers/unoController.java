package presentacion.guiControlers;

import presentacion.GUI;

public abstract class unoController implements GUI {
	
	public static unoController instance;
	
	public static unoController getInstance() {
		if(instance == null) instance = new unoControllerImp();
		return instance;
	}

}

