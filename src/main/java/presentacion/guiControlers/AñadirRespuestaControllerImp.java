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

public class A�adirRespuestaControllerImp extends A�adirRespuestaController {

	@FXML
	private StackPane stackpane;

	@FXML
	private AnchorPane root;

	@FXML
	public TextArea textArea;

	@FXML
	private JFXButton btnCancelar;

	@FXML
	private JFXButton btnA�adir;
	
	@FXML
	private JFXCheckBox chkA�adir;

	@FXML
	void btnA�adir(ActionEvent event) {

		// Stage stage = (Stage) stackpane.getScene().getWindow();
		//
		// stage.close();
	}

	@FXML
	void chkA�adirListener(ActionEvent event) {
		
	}
	
	@FXML
	void btnA�adirListener(ActionEvent event) {

	}

	@Override
	public void update(Contexto contexto) {
		// TODO Auto-generated method stub

	}

}
