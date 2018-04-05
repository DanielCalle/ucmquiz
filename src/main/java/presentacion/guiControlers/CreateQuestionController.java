package presentacion.guiControlers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import negocio.FactoriaNegocio;
import negocio.pregunta.Pregunta;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.AnchorPane;

public class CreateQuestionController implements Initializable {
	
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
		
		if(txtText.getLength() > 0) {
			
			Alert alert = new Alert(AlertType.WARNING);
			
			alert.setTitle("Advertencia");
			
			alert.setHeaderText("Accion incorrecta");
			
			alert.setContentText("No se pueden crear una pregunta en blanco");
			
			alert.showAndWait();
			
		} else {
			
			Pregunta pregunta = new Pregunta(txtText.getText(), true); 
			
			FactoriaNegocio.getInstance().generateSAPregunta().create(pregunta);
			
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
