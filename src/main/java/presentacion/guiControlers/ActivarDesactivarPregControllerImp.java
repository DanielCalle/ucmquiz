package presentacion.guiControlers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class ActivarDesactivarPregControllerImp extends ActivarDesactivarPregController implements Initializable{
	
	@FXML
	private StackPane root;
	
	@FXML
	private JFXButton btncancelar;

	@FXML
	private JFXButton btncambiar;
	
	@FXML
	private JFXToggleButton btnonoff;

	@FXML
	private JFXComboBox<String> nameQuestion;

	private boolean state;
	private List<Pregunta> list;
	private JFXDialogLayout content;
	private JFXDialog dialog;
	private JFXButton button;
	
	@FXML
	void btnCambiar(ActionEvent event) {
		
		int index = nameQuestion.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			Pregunta pregunta = list.get(index);
			if (state != pregunta.getActiva()) {
				pregunta.setActiva(state);
				list.set(index, pregunta);
				Contexto contexto = new Contexto(
						((state) ? Events.COMMAND_PREGUNTA_ACTIVATE : Events.COMMAND_PREGUNTA_DESACTIVATE),
						pregunta.getId());
				Controlador.getInstance().accion(contexto);
			}
			else {
				content = new JFXDialogLayout();
				content.setHeading(new Text("Error"));
				content.setBody(new Text("La pregunta ya esta " + (state ? "activada" : "desactivada")));
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
	void btnCancelar(ActionEvent event) {
		
		Stage stage = (Stage) root.getScene().getWindow();

		stage.close();
		
		Contexto contexto = new Contexto(Events.SHOW_MENU_PROFESORES_PREGUNTAS, null);
		
		Controlador.getInstance().accion(contexto);

	}
	
	@Override
	public void update(Contexto contexto) {
		switch (contexto.getEvent()) {
		case PREGUNTA_READ_ALL_ACTIVATE_DESACTIVATE_OK:
			list = (List<Pregunta>) contexto.getDato();
			break;
		case PREGUNTA_READ_ALL_ACTIVATE_DESACTIVATE_KO:
			break;
		case PREGUNTA_ACTIVATE_OK:
			content = new JFXDialogLayout();
			content.setHeading(new Text("Pregunta activada"));
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
		case PREGUNTA_ACTIVATE_KO:
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
		case PREGUNTA_DESACTIVATE_OK:
			content = new JFXDialogLayout();
			content.setHeading(new Text("Pregunta desactivada"));
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
		case PREGUNTA_DESACTIVATE_KO:
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
		
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		state = false;

		Contexto contexto = new Contexto(Events.COMMAND_PREGUNTA_READ_ALL_ACTIVATE_DESACTIVATE, null);
		Controlador.getInstance().accion(contexto);

		List<String> value = list.stream().map(a -> a.getTexto()).collect(Collectors.toList());

		nameQuestion.setItems(FXCollections.observableArrayList(value));
		nameQuestion.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Pregunta preg = list.stream().filter(a -> a.getTexto() == newValue).findFirst().get();
				state = preg.getActiva();
				btnonoff.setSelected(state);
			}

		});
		
	}
	@FXML
	void btnOnOFF(ActionEvent event) {
		if (state)
			state = false;
		else
			state = true;
	}

}
