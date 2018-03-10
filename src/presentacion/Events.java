package presentacion;

public class Events {

	// Login events [0 - 19]
	
	public static final int SHOW_LOGIN = 0; // Mostrar la ventana del login.
	public static final int USER_LOGIN = 1; // Intentar logearse.
	public static final int USER_LOGIN_OK = 2; // Login correcto.
	public static final int USER_LOGIN_KO = 3; // Login incorrecto.
	
	// MainMenu events [20 - 39]
	
	public static final int SHOW_MAIN_MENU = 20;
	
	// CreateQuestion events [40 - 59]
	
	public static final int SHOW_CREATE_QUESTION = 40;
	public static final int CREATE_QUESTION = 41;
	public static final int CREATE_QUESTION_OK = 42;
	public static final int CREATE_QUESTION_KO = 43;
	public static final int CREATE_ANSWER = 44;
	public static final int CREATE_ANSWER_OK = 45;
	public static final int CREATE_ANSWER_KO = 46;
	
	// AnserQuestion events [60 - 79]
	
	public static final int SHOW_ANSWER_QUESTION = 60;
	public static final int LOAD_QUESTION = 61;
	public static final int LOAD_QUESTION_OK = 62;
	public static final int LOAD_QUESTION_KO = 63;
	public static final int ANSWER_QUESTION = 64;
	public static final int ANSWER_QUESTION_OK = 65;
	public static final int ANSWER_QUESTION_KO = 66;

}