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

		Asignatura asig = new Asignatura();
		asig.setTitulo("hola");
		Contexto contexto = new Contexto(Events.COMMAND_CREATE_SUBJECT, asig);
		Controlador.getInstance().accion(contexto);
		asig.setId(1);
		Pregunta preg = new Pregunta();
		preg.setActiva(true);
		preg.setAsignatura(asig);
		preg.setTexto("aaa");
		List<Respuesta> resp = new ArrayList<Respuesta>();
		Respuesta r = new Respuesta();
		r.setActiva(true);
		r.setCorrecta(true);
		r.setTexto("hufishfiw");
		resp.add(r);
		r = new Respuesta();
		r.setActiva(true);
		r.setCorrecta(false);
		r.setTexto("false");
		resp.add(r);
		preg.setRespuestas(resp);
		Contexto contexto2 = new Contexto(Events.COMMAND_PREGUNTA_CREATE,preg);
		Controlador.getInstance().accion(contexto2);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}