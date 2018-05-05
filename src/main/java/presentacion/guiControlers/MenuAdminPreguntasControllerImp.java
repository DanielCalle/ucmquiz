package presentacion.guiControlers;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class MenuAdminPreguntasControllerImp extends MenuAdminPreguntasController{
	@FXML
    private AnchorPane root;

    @FXML
    private JFXButton botonPreguntas;

    @FXML
    private JFXButton botonRespuestas;

    @FXML
    private JFXButton botonEliminarPregunta;

    @FXML
    void botonEliminarPreguntaAction(ActionEvent event) {
    	Contexto contexto2 = new Contexto(Events.SHOW_PREGUNTA_DELETE_PROFESOR_ADMIN, null);
		Controlador.getInstance().accion(contexto2);
    }

    @FXML
    void botonPreguntasAction(ActionEvent event) {

    }

    @FXML
    void botonRespuestasAction(ActionEvent event) {

    }

}
