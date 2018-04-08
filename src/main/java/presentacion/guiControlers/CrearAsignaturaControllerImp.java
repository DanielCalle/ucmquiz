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
import negocio.asignatura.Asignatura;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class CrearAsignaturaControllerImp extends CrearAsignaturaController implements Initializable {
   
    @FXML
    private StackPane root;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXButton btnGoBack;

    @FXML
    private JFXTextField textfieldAsignatura;

    @FXML
    private JFXButton btnCreate;

    @FXML
    void btnCreateListener(ActionEvent event) {
    	
    	if (textfieldAsignatura.getLength() == 0) {

			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Accion incorrecta"));
			content.setBody(new Text("No se pueden crear una asignatura en blanco"));
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

			Asignatura asignatura = new Asignatura(textfieldAsignatura.getText(), true);

			Contexto contexto = new Contexto(Events.COMMAND_CREATE_SUBJECT, asignatura);

			Controlador.getInstance().accion(contexto);

		}

    }

    @FXML
    void btnGoBackListener(ActionEvent event) {

    	Stage stage = (Stage) root.getScene().getWindow();
    	
    	stage.close();
    	
    }

	@Override
	public void update(Contexto contexto) {
		switch (contexto.getEvent()) {

		case CRUD_CREATE_ASIGNATURA_OK:

			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Asignatura Creada"));
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
			
		case CRUD_CREATE_ASIGNATURA_KO:
			
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
		
	

	@Override
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
	
}

