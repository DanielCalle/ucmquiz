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

/**
 * Clase que maneja los eventos de la interfaz de crear pregunta.
 */
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

    /**
     * Metodo que llama al menu anterior
     * @param event Evento que se genera al pulsarse el boton.
     */
    @FXML
    void btnGoBackListener(ActionEvent event) {

    	Stage stage = (Stage) root.getScene().getWindow();
    	
    	stage.close();
    	
    }

    /**
     * Metodo que crea una pregunta.
     * @param event Evento que se genera al pulsarse el boton
     */
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

	/**
	 * Metodo que contiene el codigo que debe inicializarse al cargar la vista.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/**
		 * Codigo que cambia el color del campo de texto.
		 */
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
