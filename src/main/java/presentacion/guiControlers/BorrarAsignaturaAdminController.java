package presentacion.guiControlers;

public abstract class BorrarAsignaturaAdminController {
	
	public static BorrarAsignaturaAdminController instance;
	
	public static BorrarAsignaturaAdminController getInstance () {
		
		if (instance == null)
			instance = new BorrarAsignaturaAdminControllerImp();
		
		return instance;
	}

}
