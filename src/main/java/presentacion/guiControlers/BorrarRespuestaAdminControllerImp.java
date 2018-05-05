package presentacion.guiControlers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
	private JFXComboBox<?> comboboxPregunta;

	@FXML
	void btnBorrar(ActionEvent event) {
		if (comboboxPregunta.getSelectionModel().getSelectedItem() != null) {
			if (tablaRespuestas.getSelectionModel().getSelectedItem() != null) {
				TreeItem<Respuesta> resp = tablaRespuestas.getSelectionModel().getSelectedItem();
				Respuesta r = resp.getValue();
				Contexto contexto = new Contexto(Events.COMMAND_RM_RESPUESTA, r.getTexto());
				Controlador.getInstance().accion(contexto);
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
		}
		else {
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
		case CRUD_DELETE_RESPUESTA_OK:

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
		case CRUD_DELETE_ASIGNATURA_KO:
			
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

			List<Respuesta> lista = (List<Respuesta>) contexto.getDato();
			ObservableList<Respuesta> users = FXCollections.observableArrayList();

			for (Respuesta r : lista) {
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
		JFXTreeTableColumn<Respuesta, String> respuestas = new JFXTreeTableColumn<>("Pregunta");
		pregunta.setPrefWidth(630);
		pregunta.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<Pregunta, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Pregunta, String> param) {
						return param.getValue().getValue().getTituloStringProperty();
					}
				});
		ObservableList<Pregunta> users = FXCollections.observableArrayList();
		treeView.getColumns().setAll(pregunta);
		treeView.setShowRoot(false);
		treeView.getSelectionModel().getSelectedItem();

		Contexto contexto = new Contexto(Events.COMMAND_PREGUNTA_READ_ALL, null);
		Controlador.getInstance().accion(contexto);

		
	}


}
