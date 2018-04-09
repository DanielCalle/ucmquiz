package presentacion.guiControlers;

import presentacion.GUI;

public abstract class SeleccionUsuarioController implements GUI {


	public static SeleccionUsuarioController instance;
	
	public static SeleccionUsuarioController getInstance () {
		
		if (instance == null)
			
			instance = new SeleccionUsuarioControllerImp();
		
		return instance;
		
	}
	
}
