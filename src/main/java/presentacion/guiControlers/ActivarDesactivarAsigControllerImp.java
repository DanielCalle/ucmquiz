package presentacion.guiControlers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ActivarDesactivarAsigControllerImp extends ActivarDesactivarAsigController implements Initializable {

    @FXML
    private JFXButton btncancelar;

    @FXML
    private JFXButton btncrear;

    @FXML
    private JFXToggleButton btnonoff;

    @FXML
    private JFXComboBox<?> teacherSubject;

    @FXML
    void btnCancelar(ActionEvent event) {
    	System.out.println("a");
    }

    @FXML
    void btnCrear(ActionEvent event) {
    	/*if(txtText.getLength() == 0) {
			
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
			
		}*/
		
	}

    @FXML
    void btnOnOFF(ActionEvent event) {
    	System.out.println("c");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/*txtText.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		
				if(txtText.getLength() > 0) 
					
					txtText.setStyle("-fx-control-inner-background: lightgreen");
				
				else
					
					txtText.setStyle("-fx-control-inner-background: white");
								
			}
		
		});*/
		
	}

}
