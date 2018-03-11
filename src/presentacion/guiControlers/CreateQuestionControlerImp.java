package presentacion.guiControlers;


import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.pregunta.Pregunta;
import negocio.respuesta.Respuesta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.GUI;
import presentacion.controlador.Controlador;

public class CreateQuestionControlerImp extends CreateQuestionControler implements GUI {
	
	@FXML
	private TextField txtQuestion;
	
	@FXML
	private Button btnReturnMainMenu;
	
	@FXML
	private TextField txtCorrectAnswer;
	
	@FXML
	private TextField txtWrongAnswer1;
	
	@FXML
	private TextField txtWrongAnswer2;
	
	@FXML
	private void btnAcceptListener(ActionEvent e) {
		
		Pregunta pregunta = new Pregunta(txtQuestion.getText(), true);
		
		Respuesta respuestaCorrecta = new Respuesta(true, txtCorrectAnswer.getText(), pregunta);
		
		Respuesta respuestaIncorrecta1 = new Respuesta(false, txtWrongAnswer1.getText(), pregunta);
		
		Respuesta respuestaIncorrecta2 = new Respuesta(false, txtWrongAnswer2.getText(), pregunta);
		
		respuestaCorrecta.setActivo(true);
		
		respuestaIncorrecta1.setActivo(true);
		
		respuestaIncorrecta2.setActivo(true);
		
		Controlador.getInstance().accion(new Contexto(Events.CREATE_QUESTION, pregunta));
		
		Controlador.getInstance().accion(new Contexto(Events.CREATE_ANSWER, respuestaCorrecta));
		
		Controlador.getInstance().accion(new Contexto(Events.CREATE_ANSWER, respuestaIncorrecta1));
		
		Controlador.getInstance().accion(new Contexto(Events.CREATE_ANSWER, respuestaIncorrecta2));
		
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
		
			case Events.CREATE_QUESTION_OK:
			
				JOptionPane.showMessageDialog(null,"La pregunta se creo correctamente.");
				
			break;
			case Events.CREATE_ANSWER_OK:
				
				JOptionPane.showMessageDialog(null,"La respuesta se creo correctamente.");
				
			break;
			default:
				
				JOptionPane.showMessageDialog(null,"Error.");
			
		}
		
	}

}
