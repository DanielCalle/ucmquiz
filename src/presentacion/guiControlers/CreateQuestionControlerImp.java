package presentacion.guiControlers;


import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.GUI;
import presentacion.controlador.Controlador;

public class CreateQuestionControlerImp extends CreateQuestionControler implements GUI {
	
	@FXML
	private Button btnReturnMainMenu;
	
	@FXML
	private TextField txtQuestion;
	
	@FXML
	private TextField txtCorrectAnswer;
	
	@FXML
	private TextField txtWrongAnswer1;
	
	@FXML
	private TextField txtWrongAnswer2;
	
	@FXML
	private void btnAcceptListener(ActionEvent e) {
		
		Pregunta pregunta = new Pregunta(txtQuestion.getText(), true);
		
		Controlador.getInstance().accion(new Contexto(Events.CREATE_QUESTION, pregunta));
		
	}
	
	@FXML
	private void btnReturnMainMenuListener(ActionEvent e) {
		
		Stage stage = (Stage) btnReturnMainMenu.getScene().getWindow();
		
		stage.close();
		
		Controlador.getInstance().accion(new Contexto(Events.SHOW_MAIN_MENU, null));
		
	}

	@Override
	public void update(Contexto contexto) {
		
		if(contexto.getEvent() == Events.CREATE_QUESTION_OK) {
			
			JOptionPane.showMessageDialog(null,"La pregunta se creo correctamente.");
			
		} else {
			
			JOptionPane.showMessageDialog(null,"La pregunta no se puedo crear.");
			
		}
		
	}

}
