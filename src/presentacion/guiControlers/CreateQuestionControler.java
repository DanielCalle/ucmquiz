package presentacion.guiControlers;

public abstract class CreateQuestionControler {

	private static CreateQuestionControler instance;
	
	public static CreateQuestionControler getInstance() {
		
		if(instance == null)
			
			instance = new CreateQuestionControlerImp();
		
		return instance;
		
	}
	
}
