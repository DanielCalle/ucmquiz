package presentacion.guiControlers;

import presentacion.GUI;

public abstract class ResponderPreguntaController implements GUI {
	
	public static ResponderPreguntaController instance;
	
	public static ResponderPreguntaController getInstance() {
		if(instance == null) instance = new ResponderPreguntaControllerImp();
		return instance;
	}

}
