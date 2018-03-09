package presentacion.guiControlers;

public abstract class MainMenuControler {

	private static MainMenuControler instance;
	
	public static MainMenuControler getInstance() {
		
		if(instance == null)
			
			instance = new MainMenuControlerImp();
		
		return instance;
		
	}
	
}
