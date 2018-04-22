package presentacion.guiControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentacion.Contexto;

public class AñadirRespuestaControllerImp extends AñadirRespuestaController {

	@FXML
	private StackPane stackpane;

	@FXML
	private AnchorPane root;

	@FXML
	public TextArea textArea;

	@FXML
	private JFXButton btnCancelar;

	@FXML
	private JFXButton btnAñadir;
	
	@FXML
	private JFXCheckBox chkAñadir;

	@FXML
	void btnAñadir(ActionEvent event) {

		// Stage stage = (Stage) stackpane.getScene().getWindow();
		//
		// stage.close();
	}

	@FXML
	void chkAñadirListener(ActionEvent event) {
		
	}
	
	@FXML
	void btnAñadirListener(ActionEvent event) {

	}

	@Override
	public void update(Contexto contexto) {
		// TODO Auto-generated method stub

	}

}
