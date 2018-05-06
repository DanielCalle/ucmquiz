package presentacion.guiControlers;

import presentacion.GUI;

public abstract class BorrarRespuestaAdminController implements GUI {
	
	public static BorrarRespuestaAdminController instance;
	
	public synchronized static BorrarRespuestaAdminController getInstance () {
		
		if (instance == null)
			instance = new BorrarRespuestaAdminControllerImp();
		
		return instance;
	}

}
