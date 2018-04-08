package presentacion;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentacion.controlador.Controlador;


public class Main extends Application {
	


	@Override
	public void start(Stage stage) throws IOException {
		Contexto contexto = new Contexto(Events.SHOW_ASIGNATURA_DELETE,null);
		Controlador.getInstance().accion(contexto);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
