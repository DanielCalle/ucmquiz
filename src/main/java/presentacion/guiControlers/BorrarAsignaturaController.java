package presentacion.guiControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class BorrarAsignaturaController {
	
    @FXML
    private JFXButton btncancelar;

    @FXML
    private JFXTextField textfieldAsignatura;

    @FXML
    private JFXButton btnborrar;
    
	
	private static BorrarAsignaturaController instance;
	
	public BorrarAsignaturaController() {
		instance = this;
	}

	public static BorrarAsignaturaController getInstance() {
		return instance;
	}
	

    @FXML
    void btnBorrar(ActionEvent event) {
    	Integer id = Integer.parseInt(textfieldAsignatura.getText());
    	Contexto contexto = new Contexto(Events.COMMAND_ASIGNATURA_DELETE, id);
    	Controlador.getInstance().accion(contexto);
    }

    @FXML
    void btnCancelar(ActionEvent event) {
    }

    @FXML
    void textFieldAsignatura(ActionEvent event) {

    }

}
