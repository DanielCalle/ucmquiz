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
	
	// AnserQuestion events [60 - 79]
	
	public static final int SHOW_ANSWER_QUESTION = 60;
	
}