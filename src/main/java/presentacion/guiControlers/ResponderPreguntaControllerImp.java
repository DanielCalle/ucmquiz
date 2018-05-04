package presentacion.guiControlers;

import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
		JFXDialogLayout content = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("Ok");
        button.setOnAction(new EventHandler < ActionEvent > () {

            @Override
            public void handle(ActionEvent arg0) {
                dialog.close();
                
            }

        });
        content.setActions(button);
        dialog.show();
		TreeItem<Respuesta> respuesta = treeView.getSelectionModel().getSelectedItem();
		if (respuesta != null) {
	        content.setHeading(new Text("Validacion"));
	        content.setBody(new Text(respuesta.getValue().getCorrectaStringProperty().get()));
		}
		else {
	        content.setHeading(new Text("Error"));
	        content.setBody(new Text("No respuesta seleccionada"));
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
		case CRUD_READ_PREGUNTA_OK:
	      	
			Pregunta pregunta = (Pregunta) contexto.getDato();
	      	ObservableList<Respuesta> respuestas = FXCollections.observableArrayList();
			
			for (Respuesta respuesta: pregunta.getRespuestas()) {
				respuestas.add(respuesta);
			}
			
			final TreeItem<Respuesta> root = new RecursiveTreeItem<Respuesta>(respuestas, RecursiveTreeObject::getChildren);
			treeView.setRoot(root);
			LabelPregunta.setText(pregunta.getTexto());
	      	break;
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JFXTreeTableColumn<Respuesta, String> respuestaColumn = new JFXTreeTableColumn<>("Respuesta");
		respuestaColumn.setPrefWidth(150);
		respuestaColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Respuesta, String>, ObservableValue<String>>() {
					
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Respuesta, String> param) {
				return param.getValue().getValue().getTituloStringProperty();
			}
			
		});
		
		treeView.getColumns().setAll(respuestaColumn);
		treeView.setShowRoot(false);
		treeView.getSelectionModel().getSelectedItem();

	}

}
