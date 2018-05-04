package presentacion.guiControlers;

import presentacion.Contexto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import negocio.pregunta.Pregunta;
import negocio.respuesta.Respuesta;

public class ResponderPreguntaControllerImp extends ResponderPreguntaController implements Initializable {

	// Elementos FXML
	@FXML
	private StackPane root;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private JFXButton btnGoBack;

	@FXML
	private Label LabelPregunta;

	@FXML
	private JFXButton btnConfirmar;

	@FXML
	private JFXTreeTableView<Respuesta> treeView;

	@FXML
	void btnConfirmarListener(ActionEvent event) {

	}

	@FXML
	void btnGoBackListener(ActionEvent event) {

	}

	@FXML
	void treeViewSort(ActionEvent event) {

	}
	// Fin elementos FXML

	@Override
	public void update(Contexto contexto) {
		switch (contexto.getEvent()) {
		case CRUD_CREATE_RESPUESTA_OK:
	      	
	      	//respuestas.add((Respuesta) contexto.getDato());
	      	
			Pregunta pregunta = (Pregunta) contexto.getDato();
			List<Respuesta> respuestas = pregunta.getRespuestas();
			
	      	ObservableList<Respuesta> users = FXCollections.observableArrayList();
			
				for (Respuesta a: respuestas) {
					users.add(a);
				}
				final TreeItem<Respuesta> root = new RecursiveTreeItem<Respuesta>(users, RecursiveTreeObject::getChildren);
				treeView.setRoot(root);
				LabelPregunta.setText(pregunta.getTexto());
	      	break;
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JFXTreeTableColumn<Respuesta, String> pregunta = new JFXTreeTableColumn<>("Respuesta");
		pregunta.setPrefWidth(150);
		pregunta.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<Respuesta, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Respuesta, String> param) {
						return param.getValue().getValue().getTituloStringProperty();
					}
				});
		ObservableList<Respuesta> users = FXCollections.observableArrayList();
		treeView.getColumns().setAll(pregunta);
		treeView.setShowRoot(false);
		treeView.getSelectionModel().getSelectedItem();

	}

}
