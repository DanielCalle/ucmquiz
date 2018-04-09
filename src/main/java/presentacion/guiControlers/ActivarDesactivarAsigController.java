package presentacion.guiControlers;

public abstract class ActivarDesactivarAsigController {
	
	public static ActivarDesactivarAsigController instance;
	
	public static ActivarDesactivarAsigController getInstance() {
		
		if (instance == null)
			instance = new ActivarDesactivarAsigControllerImp();
		
		return instance;
	}

}
