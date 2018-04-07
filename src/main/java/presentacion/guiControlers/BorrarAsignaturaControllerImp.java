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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;
import presentacion.controlador.Controlador;

public class BorrarAsignaturaControllerImp extends BorrarAsignaturaController {
	@FXML
	private StackPane root;
	
    @FXML
    private JFXButton btncancelar;

    @FXML
    private JFXTextField textfieldAsignatura;

    @FXML
    private JFXButton btnborrar;

    @FXML
    void btnBorrar(ActionEvent event) {
		if (textfieldAsignatura.getLength() == 0) {

			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Accion incorrecta"));
			content.setBody(new Text("No se pueden borrar una asignatura sin ID"));
			JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

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
			try {
				Contexto contexto = new Contexto(Events.COMMAND_ASIGNATURA_DELETE, Integer.parseInt(textfieldAsignatura.getText()));
				Controlador.getInstance().accion(contexto);
			} catch (NumberFormatException e) {
				JFXDialogLayout content = new JFXDialogLayout();
				content.setHeading(new Text("Accion incorrecta"));
				content.setBody(new Text("El ID tiene que ser num�rico"));
				JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

				JFXButton button = new JFXButton("Ok");
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

    @FXML
    void btnCancelar(ActionEvent event) {
    }

    
	public void initialize(URL location, ResourceBundle resources) {

		textfieldAsignatura.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (textfieldAsignatura.getLength() > 0)

					textfieldAsignatura.setStyle("-fx-control-inner-background: lightgreen");

				else

					textfieldAsignatura.setStyle("-fx-control-inner-background: white");

			}

		});

	}

	@Override
	public void update(Contexto contexto) {
		switch (contexto.getEvent()) {

		case CRUD_DELETE_ASIGNATURA_OK:

			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Pregunta Creada"));
			content.setBody(new Text(contexto.getEvent().getMessage()));
			JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

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
			
		case CRUD_DELETE_ASIGNATURA_KO:
			
			content = new JFXDialogLayout();
			content.setHeading(new Text("Error"));
			content.setBody(new Text(contexto.getEvent().getMessage()));
			dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

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
			dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

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
