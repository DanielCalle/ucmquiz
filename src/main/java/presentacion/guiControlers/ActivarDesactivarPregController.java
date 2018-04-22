package presentacion.guiControlers;

import presentacion.GUI;

public abstract class ActivarDesactivarPregController implements GUI {
	
	public static ActivarDesactivarPregController instance;
	
	public static ActivarDesactivarPregController getInstance() {
		
		if (instance == null)
			instance = new ActivarDesactivarPregControllerImp ();
		
		return instance;
	}

}
