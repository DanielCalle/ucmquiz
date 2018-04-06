package presentacion.guiControlers;

public abstract class BorrarAsignaturaController {
	
	public static BorrarAsignaturaController instance;
	
	public static BorrarAsignaturaController getInstance () {
		
		if (instance == null)
			instance = new BorrarAsignaturaControllerImp();
		
		return instance;
	}

}
