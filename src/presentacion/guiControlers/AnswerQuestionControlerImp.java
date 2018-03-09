package presentacion.guiControlers;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import presentacion.Contexto;
import presentacion.GUI;

public class AnswerQuestionControlerImp extends AnswerQuestionControler implements GUI {
	
	@FXML
	private Label txtQuestion;
	
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
		JOptionPane.showMessageDialog(null,"Evento de salir");
		
	}

	@Override
	public void update(Contexto contexto) {
		// TODO Auto-generated method stub
		
	}

}
