package presentacion.guiControlers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import negocio.asignatura.Asignatura;
import negocio.respuesta.Respuesta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class CrearPreguntaQuitarRespuestaControllerImp extends CrearPreguntaQuitarRespuestaController {
	
	@FXML
	private StackPane stackpane;
	
    @FXML
    private JFXButton btnQuitar;
    
    @FXML
    private JFXButton btncancelar;
	
	@FXML
	private JFXComboBox<String> comboBoxRespuestas;
	
	private List<Respuesta> list;
	private JFXDialogLayout content;
	private JFXDialog dialog;
	private JFXButton button;
	
	@FXML
	void btnAtrasAction(ActionEvent event) {
		Stage stage = (Stage) stackpane.getScene().getWindow();

		stage.close();
	}
	
	@FXML
	void btnQuitarAction(ActionEvent event) {
		if(comboBoxRespuestas.getSelectionModel().getSelectedIndex()!=-1) {
			list.remove(comboBoxRespuestas.getSelectionModel().getSelectedIndex());
			Contexto contexto = new Contexto(Events.QUITAR_RESPUESTA_OK, list);
	        Controlador.getInstance().accion(contexto);
	        comboBoxRespuestas.getSelectionModel().clearSelection();
	        content = new JFXDialogLayout();
	        content.setHeading(new Text("Respuesta quitada"));
	        content.setBody(new Text("La respuesta fue quitada correctamente de la pregunta"));
	        dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
	
	        button = new JFXButton("Ok");
	        button.setOnAction(new EventHandler < ActionEvent > () {
	
	            @Override
	            public void handle(ActionEvent arg0) {
	                dialog.close();
	
	            }
	
	        });
	        content.setActions(button);
	        dialog.show();
	        List < String > value = list.stream().map(a -> a.getTexto()).collect(Collectors.toList());
	        comboBoxRespuestas.setItems(FXCollections.observableArrayList(value));
		}
		else {
	        content = new JFXDialogLayout();
	        content.setHeading(new Text("Accion incorrecta"));
	        content.setBody(new Text("No se ha seleccionado respuesta"));
	        dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
	
	        button = new JFXButton("Ok");
	        button.setOnAction(new EventHandler < ActionEvent > () {
	
	            @Override
	            public void handle(ActionEvent arg0) {
	                dialog.close();
	
	            }
	
	        });
	        content.setActions(button);
	        dialog.show();
		}
	}
	
	public void comboBoxRespuestasAction(){}
	
	public void initialize(URL location, ResourceBundle resources) {
		/*list = (List<Respuesta>) resources.getObject("respuestas");

		List<String> value = list.stream().map(a -> a.getTexto()).collect(Collectors.toList());

		comboBoxRespuestas.setItems(FXCollections.observableArrayList(value));*/
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Contexto contexto) {
		switch (contexto.getEvent()) {
		case UPDATE_QUITAR_RESPUESTA:
			list = (List<Respuesta>) contexto.getDato();
	        List < String > value = list.stream().map(a -> a.getTexto()).collect(Collectors.toList());
	        
	        comboBoxRespuestas.setItems(FXCollections.observableArrayList(value));
	        
			break;
		default:
			break;
		}
	}

}