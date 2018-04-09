package presentacion.guiControlers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import negocio.asignatura.Asignatura;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class MenuProfesorasignaturasControllerImp extends MenuProfesorasignaturasController implements Initializable {

    @FXML
    private AnchorPane root;
	@FXML
	private JFXButton BotonPreguntas;

	@FXML
	private JFXButton BotonRespuestas;

	@FXML
	private JFXButton BotonAsignaturas;
	@FXML
	private JFXButton botonNuevo;

	@FXML
	private JFXButton botonEliminar;

	@FXML
	private JFXButton botonActivarDesactivar;


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

	@FXML
	void botonEliminarAction(ActionEvent event) {

		Contexto contexto = new Contexto(Events.SHOW_ASIGNATURA_DELETE, null);
		Controlador.getInstance().accion(contexto);

	}



	@Override
	public void update(Contexto contexto) {
		// TODO Auto-generated method stub

	}

	@FXML
	void botonActivarDesactivarAction(ActionEvent event) {
		Contexto contexto = new Contexto(Events.SHOW_ASIGNATURA_ACTIVATE_DESACTIVATE, null);
		Controlador.getInstance().accion(contexto);
	}
	@FXML
    void BotonAsignaturasAction(ActionEvent event) {

    }

    @FXML
    void BotonPreguntasAction(ActionEvent event) {
    	
    	Stage stage = (Stage) root.getScene().getWindow();
    	
    	stage.close();
    	Contexto contexto = new Contexto(Events.SHOW_MENU_PROFESORES_PREGUNTAS, null);
		Controlador.getInstance().accion(contexto);
    }

    @FXML
    void BotonRespuestasAction(ActionEvent event) {

    }



    @FXML
    private JFXButton botonCrearAsignatura;

    @FXML
    private JFXButton botonlistar;




    @FXML
    void botonCrearAsignaturaAction(ActionEvent event) {

		Contexto contexto = new Contexto(Events.SHOW_ASIGNATURA_CREATE, null);
		Controlador.getInstance().accion(contexto);
    }

    @FXML
    void botonlistarAction(ActionEvent event) {
    	Contexto contexto = new Contexto(Events.SHOW_ASIGNATURA_READ_ALL,null);
		Controlador.getInstance().accion(contexto);
    }
    
}