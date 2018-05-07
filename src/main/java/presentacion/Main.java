package presentacion;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import presentacion.controlador.Controlador;
import negocio.FactoriaNegocio;
import negocio.asignatura.Asignatura;
import negocio.pregunta.Pregunta;
import negocio.pregunta.SAPregunta;
import negocio.respuesta.Respuesta;
import presentacion.controlador.Controlador;

public class Main extends Application {
	


	@Override
	public void start(Stage stage) throws IOException {
		
		Asignatura asignatura = new Asignatura("ABD", true);
		FactoriaNegocio.getInstance().generateSAAsignatura().create(asignatura);
		List<Respuesta> respuestas = new ArrayList<Respuesta>();
		Respuesta respuesta = new Respuesta("ABD1T", true, true);
		respuestas.add(respuesta);
		respuesta = new Respuesta("ABD1F", true, false);
		respuestas.add(respuesta);
		
		Pregunta pregunta = new Pregunta("ABD1", true);
		pregunta.setAsignatura(asignatura);
		pregunta.setRespuestas(respuestas);
		FactoriaNegocio.getInstance().generateSAPregunta().create(pregunta);
		
		respuestas = new ArrayList<Respuesta>();
		respuesta = new Respuesta("ABD2T", true, true);
		respuestas.add(respuesta);
		respuesta = new Respuesta("ABD2F", true, false);
		respuestas.add(respuesta);
		
		pregunta = new Pregunta("ABD2", true);
		pregunta.setAsignatura(asignatura);
		pregunta.setRespuestas(respuestas);
		FactoriaNegocio.getInstance().generateSAPregunta().create(pregunta);
		
		asignatura = new Asignatura("GPS", true);
		FactoriaNegocio.getInstance().generateSAAsignatura().create(asignatura);
		
		respuestas = new ArrayList<Respuesta>();
		respuesta = new Respuesta("GPS1T", true, true);
		respuestas.add(respuesta);
		respuesta = new Respuesta("GPS1F", true, false);
		respuestas.add(respuesta);
		
		pregunta = new Pregunta("GPS1", true);
		pregunta.setAsignatura(asignatura);
		pregunta.setRespuestas(respuestas);
		FactoriaNegocio.getInstance().generateSAPregunta().create(pregunta);
		
		Contexto contexto = new Contexto(Events.SHOW_SELECCION_USUARIO, null);
		Controlador.getInstance().accion(contexto);
		/*Contexto contexto2 = new Contexto(Events.SHOW_ESCOGER_PREGUNTA_ASIGNATURA, null);
		Controlador.getInstance().accion(contexto2);
		Contexto contexto = new Contexto(Events.COMMAND_ASIGNATURA_READ, 1);
		Controlador.getInstance().accion(contexto);*/
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}