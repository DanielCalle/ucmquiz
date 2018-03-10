package presentacion.guiControlers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.GUI;
import presentacion.controlador.Controlador;

public class AnswerQuestionControlerImp extends AnswerQuestionControler implements Initializable, GUI {
	
	@FXML
	private Label lblQuestion;
	
	@FXML
	private RadioButton txtOption1;
	
	@FXML
	private RadioButton txtOption2;
	
	@FXML
	private RadioButton txtOption3;
	
	@FXML
	private Button btnReturnMainMenu;
	
	@FXML
	private void btnConfirmListener(ActionEvent e) {
		
		JOptionPane.showMessageDialog(null,"Evento de confirmar");
	
	}
	
	@FXML
	private void btnReturnMainMenuListener(ActionEvent e) {
		
		Stage stage = (Stage) btnReturnMainMenu.getScene().getWindow();
		
		stage.close();
		
		Controlador.getInstance().accion(new Contexto(Events.SHOW_MAIN_MENU, null));
		
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
		
		Controlador.getInstance().accion(new Contexto(Events.LOAD_QUESTION, null));
		
	}

}
