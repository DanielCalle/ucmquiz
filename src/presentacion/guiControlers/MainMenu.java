package presentacion.guiControlers;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenu {
	
	@FXML
	private void btnCreateQuestion (ActionEvent e) {
		JOptionPane.showMessageDialog(null,"Tiene que lanzar el cuestionario");
	}
	
	@FXML 
	private void btnAnswerQuestion (ActionEvent e) {
		JOptionPane.showMessageDialog(null,"Tiene que lanzar la pregunta");
	}
	

}
