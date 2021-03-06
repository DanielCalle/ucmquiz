package presentacion.guiControlers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class CrearPreguntaControllerImp extends CrearPreguntaController implements Initializable {

	@FXML
	private StackPane stackpane;

	@FXML
	private AnchorPane root;

	@FXML
	public TextArea textArea;

	@FXML
	private JFXButton btnCancelar;

    @FXML
    private JFXButton btnCrear;

	@FXML
	void btnBorrar(ActionEvent event){
		
		Stage stage = (Stage) stackpane.getScene().getWindow();
    	
    	stage.close();
	}

	@FXML
	 void btnCrearListener(ActionEvent event) {

		if (textArea.getLength() == 0) {

			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Accion incorrecta"));
			content.setBody(new Text("No se pueden crear una pregunta en blanco"));
			JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

			JFXButton button = new JFXButton("Ok");
			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					dialog.close();

				}

			});
			content.setActions(button);
			dialog.show();

		} else {

			Pregunta pregunta = new Pregunta(textArea.getText(), true);

			Contexto contexto = new Contexto(Events.COMMAND_PREGUNTA_CREATE, pregunta);

			Controlador.getInstance().accion(contexto);

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		textArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (textArea.getLength() > 0)

					textArea.setStyle("-fx-control-inner-background: lightgreen");

				else

					textArea.setStyle("-fx-control-inner-background: white");

			}

		});

	}

	@Override
	public void update(Contexto contexto) {
		switch (contexto.getEvent()) {

		case CRUD_CREATE_PREGUNTA_OK:

			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Pregunta Creada"));
			content.setBody(new Text(contexto.getEvent().getMessage()));
			JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

			JFXButton button = new JFXButton("Ok");
			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					dialog.close();

				}

			});
			content.setActions(button);
			dialog.show();

			break;
			
		case CRUD_CREATE_PREGUNTA_KO:
			
			content = new JFXDialogLayout();
			content.setHeading(new Text("Error"));
			content.setBody(new Text(contexto.getEvent().getMessage()));
			dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

			button = new JFXButton("Ok");
			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					dialog.close();

				}

			});
			content.setActions(button);
			dialog.show();

			break;

		default:

			content = new JFXDialogLayout();
			content.setHeading(new Text("Error"));
			dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

			button = new JFXButton("Ok");
			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					dialog.close();

				}

			});
			content.setActions(button);
			dialog.show();

		}
	}
}