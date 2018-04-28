package presentacion.guiControlers;

import presentacion.GUI;

public abstract class AñadirRespuestaController implements GUI{


	
	public static AñadirRespuestaController instance;
	
	public static AñadirRespuestaController getInstance() {
		
		if (instance == null)
			instance = new AñadirRespuestaControllerImp();
		
		return instance;
	}

}
