package presentacion.guiControlers;

import presentacion.GUI;

public abstract class A�adirRespuestaController implements GUI{


	
	public static A�adirRespuestaController instance;
	
	public static A�adirRespuestaController getInstance() {
		
		if (instance == null)
			instance = new A�adirRespuestaControllerImp();
		
		return instance;
	}

}
