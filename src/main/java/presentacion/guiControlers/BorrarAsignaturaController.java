package presentacion.guiControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import negocio.FactoriaNegocio;
import negocio.asignatura.SAAsignatura;

public class BorrarAsignaturaController {

    @FXML
    private JFXButton btncancelar;

    @FXML
    private JFXTextField textfieldAsignatura;

    @FXML
    private JFXButton btnborrar;

    @FXML
    void btnBorrar(ActionEvent event) {
    	int id = Integer.parseInt(textfieldAsignatura.getText());
    	SAAsignatura sa = FactoriaNegocio.getInstance().createSAAsignatura();
    	sa.delete(id);
    }

    @FXML
    void btnCancelar(ActionEvent event) {

    }

    @FXML
    void textFieldAsignatura(ActionEvent event) {

    }

}
