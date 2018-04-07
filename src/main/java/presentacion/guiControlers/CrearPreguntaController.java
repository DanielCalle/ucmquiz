package presentacion.guiControlers;

import presentacion.GUI;

public abstract class CrearPreguntaController implements GUI {
	
	public static CrearPreguntaController instance;
	
	public static CrearPreguntaController getInstance() {
		if(instance == null) instance = new CrearPreguntaControllerImp();
		return instance;
	}

}
