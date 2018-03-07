package presentacion.guiControlers;


import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateQuestion {
	
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
		JOptionPane.showMessageDialog(null,"Aceptado");
	}
	
	@FXML
	private void btnCancel (ActionEvent e) {
		JOptionPane.showMessageDialog(null,"Cancelar");
	}

}
