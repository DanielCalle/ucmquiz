package presentacion.guiControlers;

public abstract class CrearAsignaturaController {
	
	public static CrearAsignaturaController instance;
	
	public static CrearAsignaturaController getInstance () {
		
		if (instance == null)
			instance = new CrearAsignaturaControllerImp();
		
		return instance;
	}

}
