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
		/*
		Parent root = FXMLLoader.load(getClass().getResource("/presentacion/SeleccionUsuario.fxml"));
		stage.setTitle("UCM QUIZ");
		
		//stage.setResizable(false);
		stage.setScene(new Scene(root));
		stage.show();
		stage.setScene(new Scene(root2));
		stage.show();
		*/
		Contexto contexto = new Contexto(Events.SHOW_SELECCION_USUARIO,null);
		Controlador.getInstance().accion(contexto);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}