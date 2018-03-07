package presentacion.guiControlers;

import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import negocio.usuario.Usuario;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class LoginControler {

	@FXML
	private TextField txtUsuario;
	
	@FXML
	private PasswordField txtPassword;
	
	@FXML
	private void btnGoListener(ActionEvent event) {
		
		try {
		
			Usuario user = new Usuario(null, txtUsuario.getText(), txtPassword.getText());
			
			Controlador.getInstance().accion(new Contexto(Events.USER_LOGIN,user));
			
		} catch (Exception exception) {
			
			JOptionPane.showMessageDialog(null,"Error: " + exception.getMessage());
			
		}
		
	}	
	
}
