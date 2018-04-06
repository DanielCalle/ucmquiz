package presentacion.guiControlers;

public abstract class CrearPreguntaController {
	
	public static CrearPreguntaController instance;
	
	public static CrearPreguntaController getInstance() {
		if(instance == null) instance = new CrearPreguntaControllerImp();
		return instance;
	}

}
