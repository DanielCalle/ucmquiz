package presentacion.guiControlers;

public class MenuAlumnoResponderController {
	public static MenuAlumnoResponderController instance;

	public static MenuAlumnoResponderController getInstance() {
		if (instance == null)
			instance = new MenuAlumnoResponderControllerImp();
		return instance;
	}
}
