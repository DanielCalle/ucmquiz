package presentacion.guiControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentacion.Contexto;

public class CrearPreguntaQuitarRespuestaControllerImp extends CrearPreguntaQuitarRespuestaController {
	
	@FXML
	private StackPane stackpane;
	
    @FXML
    private JFXButton btnQuitar;
    
    @FXML
    private JFXButton btncancelar;
	
	@FXML
	private JFXComboBox<String> comboBoxRespuestas;
	
	@FXML
	void btnAtrasAction(ActionEvent event) {
		Stage stage = (Stage) stackpane.getScene().getWindow();

		stage.close();
	}
	
	@FXML
	void btnQuitarAction(ActionEvent event) {
		
	}

	@Override
	public void update(Contexto contexto) {
		
	}

}
