package presentacion.guiControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import negocio.pregunta.Pregunta;
import negocio.respuesta.Respuesta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class CrearPreguntaAgregarRespuestaControllerImp extends CrearPreguntaAgregarRespuestaController {

	@FXML
	private StackPane stackpane;

	@FXML
	private AnchorPane root;

	@FXML
	public TextArea textArea;

	@FXML
	private JFXButton btnCancelar;

	@FXML
	private JFXButton btnAgregar;
	
	@FXML
	private CheckBox chkCorrecta;
	
	private boolean check = false;
	
	@FXML
	void btnCancelarListener(ActionEvent event) {
		Stage stage = (Stage) stackpane.getScene().getWindow();
        stage.close();
	}

	@FXML
	void chkAgregarListener(ActionEvent event) {
		if (check) check = false;
		else check = true;
	}
	
	@FXML
	void btnAgregarListener(ActionEvent event) {
		
		// Coge dato de la respuesta
		if (textArea.getLength() == 0) {
			// Si no hay texto saca un mensaje de error
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Accion incorrecta"));
            content.setBody(new Text("No se pueden crear una respuesta en blanco"));
            JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

            JFXButton button = new JFXButton("Ok");
            button.setOnAction(new EventHandler < ActionEvent > () {

                @Override
                public void handle(ActionEvent arg0) {
                    dialog.close();
                }

            });
            content.setActions(button);
            dialog.show();
        } 
		else {
			// Si hay se crea la respuesta
            Respuesta respuesta = new Respuesta(textArea.getText(), chkCorrecta.isSelected(), true);
            Contexto contexto = new Contexto(Events.COMMAND_RESPUESTA_CREATE, respuesta);
            Controlador.getInstance().accion(contexto);
            textArea.clear();
            JFXDialogLayout content = new JFXDialogLayout();
            content = new JFXDialogLayout();
            content.setHeading(new Text("Respuesta agregada"));
            content.setBody(new Text("La respuesta fue agregada correctamente de la pregunta"));
            JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

            JFXButton button = new JFXButton("Ok");
            button.setOnAction(new EventHandler < ActionEvent > () {

                @Override
                public void handle(ActionEvent arg0) {
                    dialog.close();

                }

            });
            content.setActions(button);
            dialog.show();
        }
	}

	@Override
	public void update(Contexto contexto) {
		// TODO Auto-generated method stub

	}

}
