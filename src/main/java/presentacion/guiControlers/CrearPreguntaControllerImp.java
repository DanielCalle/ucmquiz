package presentacion.guiControlers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import negocio.FactoriaNegocio;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class CrearPreguntaControllerImp extends CrearPreguntaController implements Initializable{

    @FXML
    private StackPane stackpane;
	
	@FXML
	private AnchorPane root;

	@FXML
	public TextArea txtText;
	
	@FXML
    private JFXButton btnCancelar;
	
	@FXML
	public Button btnConfirm;
	
	@FXML
    void btnCancelListener(ActionEvent event) {

    }
	
	@FXML
	public void btnConfirmListener(ActionEvent event) {
		
		if(txtText.getLength() == 0) {
			
			JFXDialogLayout content = new JFXDialogLayout();
	    	content.setHeading(new Text("Accion incorrecta"));
	    	content.setBody(new Text("No se pueden crear una pregunta en blanco"));
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
			
		} else {
			
			Pregunta pregunta = new Pregunta(txtText.getText(), true); 
			
			Contexto contexto = new Contexto(Events.COMMAND_PREGUNTA_CREATE,pregunta);
			
			Controlador.getInstance().accion(contexto);
			
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		txtText.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		
				if(txtText.getLength() > 0) 
					
					txtText.setStyle("-fx-control-inner-background: lightgreen");
				
				else
					
					txtText.setStyle("-fx-control-inner-background: white");
								
			}
		
		});
		
	}
}
