package presentacion.guiControlers;

import presentacion.GUI;

public abstract class CrearPreguntaAgregarRespuestaController implements GUI{


	
	public static CrearPreguntaAgregarRespuestaController instance;
	
	public static CrearPreguntaAgregarRespuestaController getInstance() {
		
		if (instance == null)
			instance = new CrearPreguntaAgregarRespuestaControllerImp();
		
		return instance;
	}

}
