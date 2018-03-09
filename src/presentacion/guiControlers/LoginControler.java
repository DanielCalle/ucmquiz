package presentacion.guiControlers;

public abstract class LoginControler {

	private static LoginControler instance;
	
	public static LoginControler getInstance() {
		
		if(instance == null)
			
			instance = new LoginControlerImp();
		
		return instance;
		
	}

}
