package presentacion.guiControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentacion.Contexto;

public class ResponderPreguntaElegirAsignaturaControllerImp extends ResponderPreguntaElegirAsignaturaController {
	
	@FXML
	private StackPane stackpane;
	
    @FXML
    private JFXButton btnSeleccionar;
    
    @FXML
    private JFXButton btnCancelar;
	
	@FXML
	private JFXComboBox<String> comboBoxAsignatura;
	
	@FXML
	void btnCancelarAction(ActionEvent event) {
		Stage stage = (Stage) stackpane.getScene().getWindow();

		stage.close();
	}
	
	@FXML
	void btnSeleccionarAction(ActionEvent event) {
		
	}
	
	@FXML
	void comboBoxAsignaturaAction(ActionEvent event) {
		
	}

	@Override
	public void update(Contexto contexto) {
		
	}

}
