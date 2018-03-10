package presentacion.guiControlers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.GUI;
import presentacion.controlador.Controlador;

public class AnswerQuestionControlerImp extends AnswerQuestionControler implements Initializable, GUI {
	
	@FXML
	private Button btnExit;
	
	@FXML
	private Label lblQuestion;
	
	@FXML
	private CheckBox txtOption1;
	
	@FXML
	private CheckBox txtOption2;
	
	@FXML
	private CheckBox txtOption3;
	
	@FXML
	private void btnConfirm (ActionEvent e) {
		
		JOptionPane.showMessageDialog(null,"Evento de confirmar");
	
	}
	
	@FXML
	private void btnExit (ActionEvent e) {
		
		Stage stage = (Stage) btnExit.getScene().getWindow();
		
		stage.close();
		
	}

	@Override
	public void update(Contexto contexto) {
		
		if(contexto.getEvent() == Events.LOAD_QUESTION_OK) {
		
			Pregunta pregunta = (Pregunta) contexto.getDato();
			
			lblQuestion.setText(pregunta.getTitulo());
			
		} else {
			
		}
				
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Controlador.getInstance().accion(new Contexto(Events.LOAD_QUESTION, null));
		
	}

}
