package presentacion.guiControlers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class BorrarPreguntaControllerImp extends BorrarPreguntaController implements Initializable {

	@FXML
	private StackPane stackpane;

	@FXML
	private JFXButton btncancelar;

	@FXML
	private AnchorPane root;

	@FXML
	private JFXTextField textfieldPregunta;

	@FXML
	private JFXButton btnborrar;

	@FXML
	void btnCancelar(ActionEvent event) {
		Stage stage = (Stage) root.getScene().getWindow();

		stage.close();
	}

	@FXML
	void btnBorrar(ActionEvent event) {
		if (textfieldPregunta.getLength() == 0) {

			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Accion incorrecta"));
			content.setBody(new Text("No puede haber ids en blanco"));
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

			int idPregunta = Integer.parseInt(textfieldPregunta.getText());

			Contexto contexto = new Contexto(Events.COMMAND_PREGUNTA_DELETE, idPregunta);

			Controlador.getInstance().accion(contexto);
		}
	}

	@FXML
	void textFieldPregunta(ActionEvent event) {

	}

	@Override
	public void update(Contexto contexto) {
		switch (contexto.getEvent()) {

		case CRUD_DELETE_PREGUNTA_OK:

			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Pregunta borrada"));
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

		case CRUD_DELETE_PREGUNTA_KO:

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textfieldPregunta.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (textfieldPregunta.getLength() > 0)

					textfieldPregunta.setStyle("-fx-control-inner-background: lightgreen");

				else

					textfieldPregunta.setStyle("-fx-control-inner-background: white");

			}

		});

	}

}
