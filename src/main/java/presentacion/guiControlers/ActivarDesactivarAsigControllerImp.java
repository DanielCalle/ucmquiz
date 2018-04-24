package presentacion.guiControlers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToggleButton;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import negocio.EntityManagerUtil;
import negocio.asignatura.Asignatura;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class ActivarDesactivarAsigControllerImp extends ActivarDesactivarAsigController implements Initializable {

	@FXML
	private StackPane root;
	@FXML
	private JFXButton btncancelar;

	@FXML
	private JFXButton btncrear;

	@FXML
	private JFXToggleButton btnonoff;

	@FXML
	private JFXComboBox<String> teacherSubject;

	private boolean state;
	private List<Asignatura> list;
	private JFXDialogLayout content;
	private JFXDialog dialog;
	private JFXButton button;

	@FXML
	void btnCancelar(ActionEvent event) {
		
		Stage stage = (Stage) root.getScene().getWindow();

		stage.close();
		
		Controlador.getInstance().accion(new Contexto(Events.SHOW_MENU_PROFESORES_PREGUNTAS, null));
		
	}

	@FXML
	void btnCrear(ActionEvent event) {
		int index = teacherSubject.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			Asignatura asignatura = list.get(index);
			if (state != asignatura.isActivo()) {
				asignatura.setActivo(state);
				list.set(index, asignatura);
				Contexto contexto = new Contexto(
						((state) ? Events.COMMAND_ASIGNATURA_ACTIVATE : Events.COMMAND_ASIGNATURA_DESACTIVATE),
						asignatura.getId());
				Controlador.getInstance().accion(contexto);
			}
			else {
				content = new JFXDialogLayout();
				content.setHeading(new Text("Error"));
				content.setBody(new Text("La asignatura ya esta " + (state ? "activada" : "desactivada")));
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

	@FXML
	void btnOnOFF(ActionEvent event) {
		if (state)
			state = false;
		else
			state = true;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		state = false;

		Contexto contexto = new Contexto(Events.COMMAND_ASIGNATURA_READ_ALL_ACTIVATE_DESACTIVATE, null);
		Controlador.getInstance().accion(contexto);

		List<String> value = list.stream().map(a -> a.getTitulo()).collect(Collectors.toList());

		teacherSubject.setItems(FXCollections.observableArrayList(value));
		teacherSubject.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Asignatura asig = list.stream().filter(a -> a.getTitulo() == newValue).findFirst().get();
				state = asig.isActivo();
				btnonoff.setSelected(state);
			}

		});
	}

	@Override
	public void update(Contexto contexto) {
		switch (contexto.getEvent()) {
		case ASIGNATURA_READ_ALL_ACTIVATE_DESACTIVATE_OK:
			list = (List<Asignatura>) contexto.getDato();
			break;
			
		case ASIGNATURA_READ_ALL_ACTIVATE_DESACTIVATE_KO:
			break;
		case ASIGNATURA_ACTIVATE_OK:
			content = new JFXDialogLayout();
			content.setHeading(new Text("Asignatura activada"));
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
		case ASIGNATURA_ACTIVATE_KO:
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
		case ASIGNATURA_DESACTIVATE_OK:
			content = new JFXDialogLayout();
			content.setHeading(new Text("Asignatura desactivada"));
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
		case ASIGNATURA_DESACTIVATE_KO:
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
			break;
		}
	}

}
