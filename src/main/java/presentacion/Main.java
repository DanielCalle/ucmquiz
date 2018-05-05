package presentacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import negocio.FactoriaNegocio;
import negocio.asignatura.Asignatura;
import negocio.pregunta.Pregunta;
import negocio.pregunta.SAPregunta;
import negocio.respuesta.Respuesta;
import presentacion.controlador.Controlador;


public class Main extends Application {
	


	@Override
	public void start(Stage stage) throws IOException {
		
		Contexto contexto2 = new Contexto(Events.SHOW_ESCOGER_PREGUNTA_ASIGNATURA, null);
//		Contexto contexto = new Contexto(Events.SHOW_SELECCION_USUARIO, null);
//		Controlador.getInstance().accion(contexto);
//		Contexto contexto2 = new Contexto(Events.SHOW_PREGUNTA_RESPONDER, null);
		Controlador.getInstance().accion(contexto2);
		Asignatura a = new Asignatura("ABD", true);
		a.setTitulo("ABD");
//		
		contexto2 = new Contexto(Events.COMMAND_UPDATE_SELECCIONAR_PREGUNTA, a);
		Controlador.getInstance().accion(contexto2);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}