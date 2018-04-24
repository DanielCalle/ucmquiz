package presentacion.guiControlers;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class MenuProfesorPreguntasControllerImp extends MenuProfesorPreguntasController {

	@FXML
	private JFXButton botonEliminarPregunta;

	@FXML
	private AnchorPane root;

	@FXML
	private JFXButton botonPreguntas;

	@FXML
	private JFXButton botonRespuestas;

	@FXML
	private JFXButton botonAsignaturas;

	@FXML
	private JFXButton botonCrearPregunta;

	@FXML
	private JFXButton botonActivarDesactivarPregunta;

	@FXML
	void botonActivarDesactivarPreguntaAction(ActionEvent event) {

		Stage stage = (Stage) root.getScene().getWindow();

		stage.close();

		Contexto contexto = new Contexto(Events.SHOW_PREGUNTA_ACTIVATE_DESACTIVATE, null);
		
		Controlador.getInstance().accion(contexto);
		
	}
	
	@FXML
	void botonAsignaturasAction(ActionEvent event) {
		Stage stage = (Stage) root.getScene().getWindow();

		stage.close();
		Contexto contexto = new Contexto(Events.SHOW_MENU_PROFESORES_ASIGNATURAS, null);
		Controlador.getInstance().accion(contexto);
	}

	@FXML
	void botonCrearPreguntaAction(ActionEvent event) {

		Contexto contexto = new Contexto(Events.SHOW_PREGUNTA_CREATE, null);
		Controlador.getInstance().accion(contexto);

	}

	@FXML
	void botonPreguntasAction(ActionEvent event) {

	}

	@FXML
	void botonRespuestasAction(ActionEvent event) {

	}

	@FXML
	void botonEliminarPreguntaAction(ActionEvent event) {
		Contexto contexto = new Contexto(Events.SHOW_PREGUNTA_DELETE, null);
		Controlador.getInstance().accion(contexto);
	}

	@Override
	public void update(Contexto contexto) {
		// TODO Auto-generated method stub

	}

}
