package presentacion;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import negocio.asignatura.Asignatura;
import negocio.pregunta.Pregunta;
import presentacion.controlador.Controlador;
import negocio.respuesta.Respuesta;


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