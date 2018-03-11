package presentacion.guiControlers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import negocio.pregunta.Pregunta;
import negocio.respuesta.Respuesta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.GUI;
import presentacion.controlador.Controlador;

public class AnswerQuestionControlerImp extends AnswerQuestionControler implements Initializable, GUI {
	
	@FXML
	private Label lblQuestion;
	
	@FXML
	private RadioButton rdbOption1;
	
	@FXML
	private RadioButton rdbOption2;
	
	@FXML
	private RadioButton rdbOption3;
	
	@FXML
	private Button btnReturnMainMenu;
	
	private Pregunta question;
	
	private List<Respuesta> answers;
	
	private Vector<Boolean> correctTuple;
	
	@FXML
	private void btnConfirmListener(ActionEvent e) {
		
		if(rdbOption1.isSelected() && correctTuple.get(0)) {
			
			JOptionPane.showMessageDialog(null, "!!! Respuesta correcta !!!");
			
		} else if(rdbOption2.isSelected() && correctTuple.get(1)) {
			
			JOptionPane.showMessageDialog(null, "!!! Respuesta correcta !!!");
			
		} else if(rdbOption3.isSelected() && correctTuple.get(2)) {
			
			JOptionPane.showMessageDialog(null, "!!! Respuesta correcta !!!");
			
		} else {
			
			JOptionPane.showMessageDialog(null, "!!! Respuesta incorrecta !!!");
			
		}
	
	}
	
	@FXML
	private void btnReturnMainMenuListener(ActionEvent e) {
		
		Stage stage = (Stage) btnReturnMainMenu.getScene().getWindow();
		
		stage.close();
		
		Controlador.getInstance().accion(new Contexto(Events.SHOW_MAIN_MENU, null));
		
	}

	@Override
	public void update(Contexto contexto) {
		
		switch(contexto.getEvent()) {
		
			case Events.LOAD_QUESTION_OK:
		
				this.question = (Pregunta) contexto.getDato();
				
				lblQuestion.setText(this.question.getTitulo());
						
			break;
			case Events.LOAD_ANSWER_OK:
				
				this.answers = (List<Respuesta>) contexto.getDato();
				
				rdbOption1.setText(this.answers.get(1).getTitulo());
				
				rdbOption2.setText(this.answers.get(0).getTitulo());
				
				rdbOption3.setText(this.answers.get(2).getTitulo());
			
				this.correctTuple = new Vector<>();
				
				correctTuple.add(this.answers.get(1).isCorrecto());
				
				correctTuple.add(this.answers.get(0).isCorrecto());
				
				correctTuple.add(this.answers.get(2).isCorrecto());
				
			break;
			default:
				
				JOptionPane.showMessageDialog(null, "Error en la carga de datos.");
			
		}
				
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Controlador.getInstance().accion(new Contexto(Events.LOAD_QUESTION, null));
		
		Controlador.getInstance().accion(new Contexto(Events.LOAD_ANSWER, null));
		
	}

}
