package presentacion.guiControlers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import negocio.asignatura.Asignatura;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class unocontroller implements Initializable {

	@FXML
	private StackPane stackpane;

	@FXML
	private JFXButton botonNuevo;

	@FXML
	private JFXButton botonEliminar;

	@FXML
	private JFXTreeTableView<Asignatura> treeView;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		JFXTreeTableColumn<Asignatura, String> deptName = new JFXTreeTableColumn<>("Nombre");
		deptName.setPrefWidth(150);
		deptName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<Asignatura, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Asignatura, String> param) {
						return param.getValue().getValue().getTituloStringProperty();
					}
				});
		JFXTreeTableColumn<Asignatura, String> deptEstado = new JFXTreeTableColumn<>("Estado");
		deptEstado.setPrefWidth(150);
		deptEstado.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<Asignatura, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Asignatura, String> param) {
						return param.getValue().getValue().getActivoStringProperty();
					}
				});

		ObservableList<Asignatura> users = FXCollections.observableArrayList();
		Contexto contexto = new Contexto(Events.CRUD_READ_ALL_ASIGNATURA_OK, null);
		Controlador.getInstance().accion(contexto);

		final TreeItem<Asignatura> root = new RecursiveTreeItem<Asignatura>(users, RecursiveTreeObject::getChildren);
		treeView.getColumns().setAll(deptName, deptEstado);
		// treeView.getColumns().setAll(deptEstado);
		treeView.setRoot(root);
		treeView.setShowRoot(false);
		treeView.getSelectionModel().getSelectedItem();
		botonEliminar.setDisable(true);
		treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			e.consume();
			botonEliminar.setDisable(false);
		});
	}

	@FXML
	void botonEliminarAction(ActionEvent event) {

	}

	@FXML
	void botonNuevoAction(ActionEvent event) {
		JFXDialogLayout content = new JFXDialogLayout();
    	content.setHeading(new Text("Correcto"));
    	content.setBody(new Text("Se ha creado correctamente"));
    	JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

    	JFXButton button = new JFXButton("Done");
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
