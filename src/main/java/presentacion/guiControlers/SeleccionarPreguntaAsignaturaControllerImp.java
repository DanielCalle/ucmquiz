package presentacion.guiControlers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import negocio.asignatura.Asignatura;
import negocio.pregunta.Pregunta;
import negocio.respuesta.Respuesta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

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
    private List<Pregunta> preguntas;
    private Asignatura asignatura;

    @FXML
    void btnCancelar(ActionEvent event) {

        Stage stage = (Stage) stackpane.getScene().getWindow();

        stage.close();
    }

    @FXML
    void btnContestarListener(ActionEvent event) {
    	if(cobobox.getSelectionModel().getSelectedIndex()!=-1) {
    		Pregunta pregunta = preguntas.get(cobobox.getSelectionModel().getSelectedIndex());
    		Contexto contexto = new Contexto(Events.SHOW_PREGUNTA_RESPONDER, null);
    		Controlador.getInstance().accion(contexto);
    		
    		contexto = new Contexto(Events.COMMAND_PREGUNTA_READ, pregunta.getId());
    		Controlador.getInstance().accion(contexto);
    		
    	}else {
    		
    	}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    }

	@Override
    public void update(Contexto contexto) {
		switch(contexto.getEvent()) {
		case CRUD_READ_ALL_PREGUNTA_OK:
			List<Pregunta> list = (List<Pregunta>) contexto.getDato();
			preguntas = new ArrayList<Pregunta>();
	        
			for(Pregunta p : list) {
				if(p.getAsignatura().getId() == (asignatura.getId())) {
					preguntas.add(p);
				}
			}
			
			List < String > value = preguntas.stream().map(a -> a.getTexto()).collect(Collectors.toList());
	        cobobox.setItems(FXCollections.observableArrayList(value));
	        
			break;
		case CRUD_READ_ASIGNATURA_OK:
			
			asignatura = (Asignatura) contexto.getDato();
			textArea.setText(asignatura.getTitulo());
			Contexto context = new Contexto(Events.COMMAND_PREGUNTA_READ_ALL, null);
	        Controlador.getInstance().accion(context);
	        
	      	break;
		default: break;
		}
  
    }
    @FXML
    void coboboxAction(ActionEvent event) {}

}