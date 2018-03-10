package presentacion.guiControlers;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import negocio.pregunta.Pregunta;
import negocio.usuario.Usuario;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.GUI;
import presentacion.controlador.Controlador;

public class CreateQuestionControlerImp extends CreateQuestionControler implements GUI {
	
	@FXML
	private TextField txtQuestion;
	
	@FXML
	private TextField txtCorrectAnswer;
	
	@FXML
	private TextField txtWrongAnswer1;
	
	@FXML
	private TextField txtWrongAnswer2;
	
	@FXML
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
	}
	
	@FXML
	private void btnCancel (ActionEvent e) {
		JOptionPane.showMessageDialog(null,"Cancelar");
	}

	@Override
	public void update(Contexto contexto) {
		// TODO Auto-generated method stub
		
	}

}
