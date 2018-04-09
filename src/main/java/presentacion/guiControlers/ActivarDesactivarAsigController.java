package presentacion.guiControlers;


import presentacion.GUI;

public abstract class ActivarDesactivarAsigController implements GUI {

	
	public static ActivarDesactivarAsigController instance;
	
	public static ActivarDesactivarAsigController getInstance() {
		
		if (instance == null)
			instance = new ActivarDesactivarAsigControllerImp();
		
		return instance;
	}

}
