package presentacion.guiControlers;

import presentacion.Contexto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class BorrarRespuestaAdminControllerImp extends BorrarRespuestaAdminController{
	
	@FXML
    private AnchorPane root;
	
	@FXML
	private JFXButton btncancelar;

	@FXML
	private JFXButton btnborrar;
	
	@FXML
	private JFXTreeTableView<?> tablaRespuestas;

	@FXML
	private JFXComboBox<?> comboboxPregunta;

	@FXML
	void btnBorrar(ActionEvent event) {

	}

	@FXML
	void btnCancelar(ActionEvent event) {

	}

	@Override
	public void update(Contexto contexto) {
		// TODO Auto-generated method stub
		
	}


}
