package presentacion.guiControlers;

public abstract class AnswerQuestionControler {

	private static AnswerQuestionControler instance;
	
	public static AnswerQuestionControler getInstance() {
		
		if(instance == null)
			
			instance = new AnswerQuestionControlerImp();
		
		return instance;
		
	}
	
}
