package presentacion.guiControlers;

import presentacion.GUI;

public abstract class CrearPreguntaQuitarRespuestaController implements GUI {
	
	public static CrearPreguntaQuitarRespuestaController instance;
	
	public static CrearPreguntaQuitarRespuestaController getInstance() {
		if(instance == null) instance = new CrearPreguntaQuitarRespuestaControllerImp();
		return instance;
	}

}
