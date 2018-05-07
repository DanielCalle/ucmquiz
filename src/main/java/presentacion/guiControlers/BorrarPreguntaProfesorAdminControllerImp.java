package presentacion.guiControlers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import negocio.pregunta.Pregunta;
import javafx.fxml.Initializable;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class BorrarPreguntaProfesorAdminControllerImp extends BorrarPreguntaProfesorAdminController
		implements Initializable {
	
	private Pregunta preguntaABorrar;

	// ELEMENTOS FXML

	@FXML
	private StackPane stackpane;

	@FXML
	private JFXButton btncancelar;

	@FXML
	private JFXButton btnborrar;

	@FXML
	private JFXTreeTableView<Pregunta> treeView;

	/**
	 * 
	 * @param event
	 * Detecta cuando se hace click en la tabla y guarda la pregunta
	 * sobre la que se hace click
	 */
	@FXML
	void click(MouseEvent event) {
		TreeItem<Pregunta> TreeItempregunta = treeView.getSelectionModel().getSelectedItem();
		try {
			Pregunta pregunta = TreeItempregunta.getValue();
			if(pregunta != null) {
				preguntaABorrar = pregunta;
			}
		}
		catch(Exception e) {
			
		}
		

	}
	/**
	 * Borra la pregunta
	 * @param event
	 */

	@FXML
	void btnBorrar(ActionEvent event) {
		
		if(preguntaABorrar== null) {
			mostrarDialog("ERROR", "No se ha seleccionado ninguna pregunta");
		}
		else {
			int idPregunta = preguntaABorrar.getId();

			Contexto contexto = new Contexto(Events.COMMAND_PREGUNTA_DELETE, idPregunta);

			Controlador.getInstance().accion(contexto);
			

		}
	}

	/**
	 * Lleva hacia atras
	 * @param event
	 */
	@FXML
	void btnCancelar(ActionEvent event) {
		Stage stage = (Stage) stackpane.getScene().getWindow();

		stage.close();
	}

	@FXML
	void treeViewSort(ActionEvent event) {
	}

	// FIN ELEMENTOS FXML

	/**
	 * LLeva a cabo los updates:
	 * Carga todas las preguntas, da los mensajes de confirmacion
	 */
	@Override
	public void update(Contexto contexto) {

		switch (contexto.getEvent()) {

		case CRUD_READALL_DELETE_PREGUNTA_PROFESOR_ADMIN_OK:

			List<Pregunta> lista = (List<Pregunta>) contexto.getDato();
			ObservableList<Pregunta> users = FXCollections.observableArrayList();

			for (Pregunta a : lista) {
				users.add(a);
			}
			final TreeItem<Pregunta> root = new RecursiveTreeItem<Pregunta>(users, RecursiveTreeObject::getChildren);
			treeView.setRoot(root);

			System.out.println("OK");
			break;

		case CRUD_READALL_DELETE_PREGUNTA_PROFESOR_ADMIN_KO:
			mostrarDialog("ERROR","Error al leer la lista");
			break;
		case PREGUNTA_DELETE_PROFESOR_ADMIN_OK:
			mostrarDialog("","Se elimino la pregunta del sistema");
			contexto = new Contexto(Events.COMMAND_PREGUNTA_READ_ALL, null);
			Controlador.getInstance().accion(contexto);
			break;
		case PREGUNTA_DELETE_PROFESOR_ADMIN_KO:
			mostrarDialog("ERROR","No se pudo eliminar la pregunta del sistema");
			break;
		default:
			break;

		}

	}

	/**
	 * Inicializa la tabla
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JFXTreeTableColumn<Pregunta, String> pregunta = new JFXTreeTableColumn<>("Pregunta");
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

		Contexto contexto = new Contexto(Events.COMMAND_READ_ALL_PREGUNTA_DELETE_PROFESOR_ADMIN, null);
		Controlador.getInstance().accion(contexto);

	}
	
	private void mostrarDialog(String Heading, String Body) {
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text(Heading));
		content.setBody(new Text(Body));
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


	}

}
