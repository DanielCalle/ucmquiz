package presentacion.guiControlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.GUI;
import presentacion.controlador.Controlador;

public class MainMenuControlerImp extends MainMenuControler implements GUI {
	
	@FXML
	private Button btnAnswerQuestion;
	
	@FXML
	private Button btnCreateQuestion;
	
	@FXML
	private void btnCreateQuestionListener(ActionEvent e) {
	
		Stage stage = (Stage) btnCreateQuestion.getScene().getWindow();
		
		stage.close();
		
		Controlador.getInstance().accion(new Contexto(Events.SHOW_CREATE_QUESTION, null));
		
	}
	
	@FXML 
	private void btnAnswerQuestionListener(ActionEvent e) {
		
		Stage stage = (Stage) btnAnswerQuestion.getScene().getWindow();
		
		stage.close();
		
		Controlador.getInstance().accion(new Contexto(Events.SHOW_ANSWER_QUESTION, null));
		
	}

	@Override
	public void update(Contexto contexto) {
		
		
		
	}

}
