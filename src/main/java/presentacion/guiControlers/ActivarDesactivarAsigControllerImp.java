package presentacion.guiControlers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import negocio.EntityManagerUtil;
import negocio.asignatura.Asignatura;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class ActivarDesactivarAsigControllerImp extends ActivarDesactivarAsigController implements Initializable {

    @FXML
    private JFXButton btncancelar;

    @FXML
    private JFXButton btncrear;

    @FXML
    private JFXToggleButton btnonoff;

    @FXML
    private JFXComboBox<String> teacherSubject;
    
    private boolean state;
	private List<Asignatura> list;

    @FXML
    void btnCancelar(ActionEvent event) {
    	System.out.println("a");
    }

    @FXML
    void btnCrear(ActionEvent event) {
    	int index = teacherSubject.getSelectionModel().getSelectedIndex();
    	if (index >= 0) {
	    	Asignatura asignatura = list.get(index);
	    	if (state != asignatura.isActivo()) { 
	    		asignatura.setActivo(state);
	    		list.set(index, asignatura);
		    	Contexto contexto = new Contexto(
		    			((state) ? Events.COMMAND_ASIGNATURA_ACTIVATE : Events.COMMAND_ASIGNATURA_DESACTIVATE), 
		    			asignatura.getId());
	    		state = !state;
				btnonoff.setSelected(state);
				Controlador.getInstance().accion(contexto);
	    	}
    	}
	}

    @FXML
    void btnOnOFF(ActionEvent event) {
    	if (state) state = false;
    	else state = true;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		state = false;

		Asignatura asignatura = null;
		asignatura = new Asignatura();
		asignatura.setTitulo("MMI");
		asignatura.setId(1);
		asignatura.setActivo(true);
		
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();
		entitymanager.persist(asignatura);
		entitytransaction.commit();
		
		Contexto contexto = new Contexto(Events.COMMAND_ASIGNATURA_READ_ALL_ACTIVATE_DESACTIVATE, null);
		Controlador.getInstance().accion(contexto);
		
		List<String> value = list.stream().map(a -> a.getTitulo()).collect(Collectors.toList());
		
		teacherSubject.setItems(FXCollections.observableArrayList(value));
		teacherSubject.valueProperty().addListener(new ChangeListener<String>() {


			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Asignatura asig = list.stream().filter(a -> a.getTitulo() == newValue).findFirst().get();
				state = !asig.isActivo();
				btnonoff.setSelected(state);
			}
			
		});
	}

	@Override
	public void update(Contexto contexto) {
		switch (contexto.getEvent()) {
		case ASIGNATURA_READ_ALL_ACTIVATE_DESACTIVATE_OK: list = (List<Asignatura>) contexto.getDato(); break;
		case ASIGNATURA_READ_ALL_ACTIVATE_DESACTIVATE_KO: break;
		case ASIGNATURA_ACTIVATE_OK: System.out.println("si"); break;
		case ASIGNATURA_ACTIVATE_KO: System.out.println("no"); break;
		case ASIGNATURA_DESACTIVATE_OK: System.out.println("si2"); break;
		case ASIGNATURA_DESACTIVATE_KO: System.out.println("no2"); break;
		default:
			break;
		}
	}

}
