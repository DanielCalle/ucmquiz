package presentacion.guiControlers;

import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControler {

	@FXML
	private TextField txtUsuario;
	
	@FXML
	private PasswordField txtPassword;
	
	@FXML
	private void btnGoListener(ActionEvent e) {
		
		if(txtUsuario.getText().compareTo("admin") == 0 && txtPassword.getText().compareTo("admin") == 0)
		
			JOptionPane.showMessageDialog(null,"Bien venido usuario administrador.");
			
		else
			
			JOptionPane.showMessageDialog(null,"No existe el usuario.");
		
	}	
	
}
