package presentacion.guiControlers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToggleButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import negocio.asignatura.Asignatura;
import presentacion.Contexto;

public class ActivarDesactivarPregControllerImp extends ActivarDesactivarPregController implements Initializable{
	
	@FXML
	private StackPane root;
	
	@FXML
	private JFXToggleButton btnonoff;

	@FXML
	private JFXComboBox<?> nameQuestion;

	private boolean state;
	private List<Asignatura> list;
	private JFXDialogLayout content;
	private JFXDialog dialog;
	private JFXButton button;
	
	@FXML
	void btnCambiar(ActionEvent event) {

	}

	@FXML
	void btnCancelar(ActionEvent event) {

	}
	
	@Override
	public void update(Contexto contexto) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
