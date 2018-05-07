package presentacion.guiControlers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import negocio.asignatura.Asignatura;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class ResponderPreguntaElegirAsignaturaControllerImp extends ResponderPreguntaElegirAsignaturaController implements Initializable  {
	
	@FXML
	private StackPane stackpane;
	
    @FXML
    private JFXButton btnSeleccionar;
    
    @FXML
    private JFXButton btnCancelar;
	
	@FXML
	private JFXComboBox<String> comboBoxAsignatura;
	private List<Asignatura> asignaturas;
	
	@FXML
	void btnCancelarAction(ActionEvent event) {
		
		Stage stage = (Stage) stackpane.getScene().getWindow();

		stage.close();
	}
	
	@FXML
	void btnSeleccionarAction(ActionEvent event) {
		if(comboBoxAsignatura.getSelectionModel().getSelectedIndex()!=-1) {
		Asignatura asignatura = asignaturas.get(comboBoxAsignatura.getSelectionModel().getSelectedIndex());
		
		Contexto contexto = new Contexto(Events.SHOW_ESCOGER_PREGUNTA_ASIGNATURA, null);
		Controlador.getInstance().accion(contexto);
		
		contexto = new Contexto(Events.COMMAND_ASIGNATURA_READ, asignatura.getId());
		Controlador.getInstance().accion(contexto);
		
	}
	}
	
	@FXML
	void comboBoxAsignaturaAction(ActionEvent event) {}

	@Override
	public void update(Contexto contexto) {
		
		switch(contexto.getEvent()) {
		case SELECT_ASIGNATURA_READ_ALL_OK:
			asignaturas = (List<Asignatura>) contexto.getDato();
			List < String > value = asignaturas.stream().map(a -> a.getTitulo()).collect(Collectors.toList());
			comboBoxAsignatura.setItems(FXCollections.observableArrayList(value));
		break;
		default: break;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

        asignaturas = new ArrayList<Asignatura>();
		Contexto context = new Contexto(Events.COMMAND_SELECT_ASIGNATURA_READ_ALL, null);
        Controlador.getInstance().accion(context);
		
	}

}
