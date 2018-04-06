package presentacion.guiControlers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CrearPreguntaController implements Initializable {
	
	@FXML
    private StackPane root;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXButton btnGoBack;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private JFXTextArea txtText;

    @FXML
    void btnGoBackListener(ActionEvent event) {

    	Stage stage = (Stage) root.getScene().getWindow();
    	
    	stage.close();
    	
    }

	@FXML
	public void btnConfirmListener(ActionEvent event) {
		
		if(txtText.getLength() > 0) {
			
			
			
		} else {
			
			JFXDialogLayout content = new JFXDialogLayout();
			
			content.setHeading(new Text("Advertencia"));
			
			content.setBody(new Text("No se puede crear una pregunta vacia."));
			
			JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
			
			JFXButton confirmButton = new JFXButton("OK");
			
			confirmButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					dialog.close();
			
				}
				
			});
			
			content.setActions(confirmButton);
			
			dialog.show();
			
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
