package presentacion.guiControlers;

import presentacion.GUI;

public abstract class BorrarPreguntaController implements GUI {

	public static BorrarPreguntaController instance;
	
	public static BorrarPreguntaController getInstance() {
		if(instance == null) instance = new BorrarPreguntaControllerImp();
		return instance;
	}

}
