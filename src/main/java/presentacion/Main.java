package presentacion;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import presentacion.controlador.Controlador;

public class Main extends Application {
	


	@Override
	public void start(Stage stage) throws IOException {
		
		Contexto contexto2 = new Contexto(Events.SHOW_SELECCION_USUARIO, null);
		Controlador.getInstance().accion(contexto2);

		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}