package presentacion.guiControlers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentacion.Contexto;

public class CrearAsignaturaControllerImp extends CrearAsignaturaController implements Initializable {
   
    @FXML
    private StackPane root;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXButton btnGoBack;

    @FXML
    private JFXTextField textfieldAsignatura;

    @FXML
    private JFXButton btnCreate;

    @FXML
    void btnCreateListener(ActionEvent event) {

    }

    @FXML
    void btnGoBackListener(ActionEvent event) {

    	Stage stage = (Stage) root.getScene().getWindow();
    	
    	stage.close();
    	
    }

	@Override
	public void update(Contexto contexto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		textfieldAsignatura.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (textfieldAsignatura.getLength() > 0)

					textfieldAsignatura.setStyle("-fx-control-inner-background: lightgreen");

				else

					textfieldAsignatura.setStyle("-fx-control-inner-background: white");

			}

		});

		
	}
	
}

