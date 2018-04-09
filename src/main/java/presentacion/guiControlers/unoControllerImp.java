package presentacion.guiControlers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import negocio.FactoriaNegocio;
import negocio.asignatura.Asignatura;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class unoControllerImp extends unoController implements Initializable{

	@FXML
	private StackPane stackpane;
	

    @FXML
    private JFXButton Atras;

	@FXML
	private JFXTreeTableView<Asignatura> treeView;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//Asignatura asignatura = new Asignatura("EDA", true);

		//Contexto c = new Contexto(Events.COMMAND_CREATE_SUBJECT, asignatura);
	//	Controlador.getInstance().accion(c);
		JFXTreeTableColumn<Asignatura, String> id = new JFXTreeTableColumn<>("Id");
		id.setPrefWidth(150);
		id.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<Asignatura, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Asignatura, String> param) {
						return param.getValue().getValue().getIdStringProperty();
					}
				});
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
		Contexto contexto = new Contexto(Events.COMMAND_ASIGNATURA_LISTAR, null);
		Controlador.getInstance().accion(contexto);

		//final TreeItem<Asignatura> root = new RecursiveTreeItem<Asignatura>(users, RecursiveTreeObject::getChildren);
		treeView.getColumns().setAll(id,deptName, deptEstado);
		// treeView.getColumns().setAll(deptEstado);
	//	treeView.setRoot(root);
		treeView.setShowRoot(false);
		treeView.getSelectionModel().getSelectedItem();

	}

	  @FXML
	    void AtrasAction(ActionEvent event) {
			Stage stage = (Stage) stackpane.getScene().getWindow();
	    	
	    	stage.close();
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

	@Override
	public void update(Contexto contexto) {
		if (contexto.getEvent() == Events.CRUD_READ_ASIGNATURA_KO) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Lista de asignaturas vacia"));
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

		}

		else if (contexto.getEvent() != Events.CRUD_READ_ALL_ASIGNATURA_OK) {
			
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Error"));
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
		else {
			ObservableList<Asignatura> users = FXCollections.observableArrayList();
			List<Asignatura> lista = (List<Asignatura>)contexto.getDato();
			for (Asignatura a: lista) {
				users.add(a);
			}
			final TreeItem<Asignatura> root = new RecursiveTreeItem<Asignatura>(users, RecursiveTreeObject::getChildren);
			treeView.setRoot(root);
		}
	}

}