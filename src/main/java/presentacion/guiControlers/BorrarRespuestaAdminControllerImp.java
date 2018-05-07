package presentacion.guiControlers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import negocio.asignatura.Asignatura;
import negocio.pregunta.Pregunta;
import negocio.respuesta.Respuesta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class BorrarRespuestaAdminControllerImp extends BorrarRespuestaAdminController implements Initializable{
	
	@FXML
    private StackPane root;
	
	@FXML
	private JFXButton btncancelar;

	@FXML
	private JFXButton btnborrar;
	
	@FXML
	private JFXTreeTableView<Respuesta> tablaRespuestas;

	@FXML
	private JFXComboBox<String> comboboxPregunta;
	
	private List<Respuesta> listaRespuestas;
	private List<Pregunta> listaPreguntas;

	@FXML
	void btnBorrar(ActionEvent event) {
		if (comboboxPregunta.getSelectionModel().getSelectedItem() != null) {
				Contexto contexto = new Contexto(Events.CRUD_READ_PREGUNTA_OK, listaPreguntas.get(comboboxPregunta.getSelectionModel().getSelectedIndex()));
				this.update(contexto);
			if (tablaRespuestas.getSelectionModel().getSelectedItem() != null) {
				TreeItem<Respuesta> resp = tablaRespuestas.getSelectionModel().getSelectedItem();
				Respuesta r = resp.getValue();
				Contexto contexto3 = new Contexto(Events.COMMAND_RESPUESTA_DELETE_ADMIN, r.getTexto());
				Controlador.getInstance().accion(contexto3);
			}
			else {
				JFXDialogLayout content = new JFXDialogLayout();
				content.setHeading(new Text("Accion incorrecta"));
				content.setBody(new Text("Debes seleccionar una respuesta"));
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
		}else {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Accion incorrecta"));
			content.setBody(new Text("Debes seleccionar una pregunta"));
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
	@FXML
	void btnCancelar(ActionEvent event) {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	@Override
	public void update(Contexto contexto) {
		switch (contexto.getEvent()) {
		//Si se ha podido borrar muestro un mensaje con acierto
		case RESPUESTA_DELETE_ADMIN_OK:

			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Respuesta borrada"));
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
		//Si no se ha podido borrar muestro un mensaje de error con el con la info
		case RESPUESTA_DELETE_ADMIN_KO:
			
			content = new JFXDialogLayout();
			content.setHeading(new Text("Error al eliminar la respuesta"));
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
		case CRUD_READ_ALL_RESPUESTA_OK:

			this.listaRespuestas = (List<Respuesta>) contexto.getDato();
			ObservableList<Respuesta> users = FXCollections.observableArrayList();

			for (Respuesta r : listaRespuestas) {
				users.add(r);
			}
			final TreeItem<Respuesta> raiz = new RecursiveTreeItem<Respuesta>(users, RecursiveTreeObject::getChildren);
			tablaRespuestas.setRoot(raiz);

			System.out.println("OK");
			break;

		case CRUD_READ_ALL_RESPUESTA_KO:
			JFXDialogLayout contenido = new JFXDialogLayout();
			contenido.setHeading(new Text("Error al leer las respuestas"));
			contenido.setBody(new Text(contexto.getEvent().getMessage()));
			JFXDialog dialogo = new JFXDialog(root, contenido, JFXDialog.DialogTransition.CENTER);

			JFXButton button1 = new JFXButton("Ok");
			button1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					dialogo.close();

				}

			});
			contenido.setActions(button1);
			dialogo.show();
			break;
			
		case CRUD_READ_PREGUNTA_OK:
			// Recibe la asignatura lo pone como titulo y llama al read all
			Pregunta preguntaActual = (Pregunta) contexto.getDato();
			List<Respuesta> respuestas = preguntaActual.getRespuestas();
			ObservableList<Respuesta> respuestasList = FXCollections.observableArrayList();
			for (Respuesta r : respuestas) {
				respuestasList.add(r);
			}
			final TreeItem<Respuesta> raiz2 = new RecursiveTreeItem<Respuesta>(respuestasList, RecursiveTreeObject::getChildren);
			tablaRespuestas.setRoot(raiz2);     
			break;
				      	
		case CRUD_READ_ALL_PREGUNTA_KO:
			JFXDialogLayout contenido2 = new JFXDialogLayout();
			contenido2.setHeading(new Text("Error al leer las respuestas"));
			contenido2.setBody(new Text(contexto.getEvent().getMessage()));
			JFXDialog dialogo2 = new JFXDialog(root, contenido2, JFXDialog.DialogTransition.CENTER);
			JFXButton button2 = new JFXButton("Ok");
			button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
				public void handle(ActionEvent arg0) {
					dialogo2.close();
				}
			});
			contenido2.setActions(button2);
			dialogo2.show();
			break;

		default:
			
			content = new JFXDialogLayout();
			content.setHeading(new Text("Error"));
			dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

			button1 = new JFXButton("Ok");
			button1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					dialog.close();

				}

			});
			content.setActions(button1);
			dialog.show();

		}
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Contexto contexto = new Contexto(Events.COMMAND_PREGUNTA_READ_ALL, null);
		Controlador.getInstance().accion(contexto);

		List<String> value = listaPreguntas.stream().map(a -> a.getTexto()).collect(Collectors.toList());
		comboboxPregunta.setItems(FXCollections.observableArrayList(value));
		
	}

}
