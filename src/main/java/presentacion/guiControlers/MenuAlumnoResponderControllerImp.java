package presentacion.guiControlers;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class MenuAlumnoResponderControllerImp extends MenuAlumnoResponderController {
	@FXML
    private AnchorPane root;
	
	@FXML
    private JFXButton botonResponderPregunta;
	
	@FXML
	void botonResponderPreguntaAction(ActionEvent event) {
		Stage stage = (Stage) root.getScene().getWindow();

		stage.close();
		Contexto contexto = new Contexto(Events.SHOW_RESPONDER_ELEGIR_ASIGNATURA, null);
		Controlador.getInstance().accion(contexto);
	}

}
