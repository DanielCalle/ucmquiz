package presentacion.guiControlers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;

public class SeleccionarPreguntaAsignaturaControllerImp extends SeleccionarPreguntaAsignaturaController implements Initializable {

    @FXML
    private StackPane stackpane;

    @FXML
    private AnchorPane root;

    @FXML
    public TextArea textArea;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnContestar;

    //private List<Pregunta> list;

    @FXML
    private JFXComboBox < String > cobobox;

    @FXML
    void btnCancelar(ActionEvent event) {

        Stage stage = (Stage) stackpane.getScene().getWindow();

        stage.close();
    }

    @FXML
    void btnContestarListener(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    }

	@Override
    public void update(Contexto contexto) {
  
    }
    @FXML
    void coboboxAction(ActionEvent event) {

    }

}