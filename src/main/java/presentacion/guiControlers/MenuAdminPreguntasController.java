package presentacion.guiControlers;

public class MenuAdminPreguntasController {
	public static MenuAdminPreguntasController instance;

	public static MenuAdminPreguntasController getInstance() {
		if (instance == null)
			instance = new MenuAdminPreguntasControllerImp();
		return instance;
	}
}
