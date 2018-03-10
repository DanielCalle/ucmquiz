package presentacion.guiControlers;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
<<<<<<< HEAD
import javafx.stage.Stage;
import negocio.pregunta.Pregunta;
=======
import negocio.pregunta.Pregunta;
import negocio.usuario.Usuario;
>>>>>>> branch 'master' of https://GCCodeWarrior@github.com/DanielCalle/ucmquiz.git
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
<<<<<<< HEAD
	private void btnAcceptListener(ActionEvent e) {
		
		Pregunta pregunta = new Pregunta(txtQuestion.getText(), true);
		
		Controlador.getInstance().accion(new Contexto(Events.CREATE_QUESTION, pregunta));
		
=======
	private void btnAccept (ActionEvent e) {
		try {
			
		
			ArrayList<String> datos = new ArrayList<String>();
			
			datos.add(txtQuestion.getText());
			datos.add(txtCorrectAnswer.getText());
			datos.add(txtWrongAnswer1.getText());
			datos.add(txtWrongAnswer2.getText());
			
			Controlador.getInstance().accion(new Contexto(Events.CREATE_QUESTION,datos));
			
			// Controlador.getInstance().accion(new Contexto(Events.SHOW_MAIN_MENU,null));
			
		} catch (Exception exception) {
			
			JOptionPane.showMessageDialog(null,"Error: " + exception.getMessage());
			
		}
		JOptionPane.showMessageDialog(null,"Aceptado");
>>>>>>> branch 'master' of https://GCCodeWarrior@github.com/DanielCalle/ucmquiz.git
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
