package presentacion.guiControlers;

import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.usuario.Usuario;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.GUI;
import presentacion.controlador.Controlador;

public class LoginControlerImp extends LoginControler implements GUI {

	@FXML
	private TextField txtUsuario;
	
	@FXML
	private PasswordField txtPassword;

	@FXML
	private javafx.scene.control.Button btnGo;
	
	@FXML
	private void btnGoListener(ActionEvent event) {
		
		try {
		
			Usuario user = new Usuario(null, txtUsuario.getText(), txtPassword.getText());
			
			Controlador.getInstance().accion(new Contexto(Events.USER_LOGIN,user));
			
			// Controlador.getInstance().accion(new Contexto(Events.SHOW_MAIN_MENU,null));
			
		} catch (Exception exception) {
			
			JOptionPane.showMessageDialog(null,"Error: " + exception.getMessage());
			
		}
		
	}	
	
	public void update(Contexto context)  {
		
		Usuario user = (Usuario) context.getDato();
		
		if(context.getEvent() == Events.USER_LOGIN_OK) {
		
			JOptionPane.showMessageDialog(null,"Bienvenido " + user.getNombre() + ".");
			
			Stage stage = (Stage) btnGo.getScene().getWindow();
			
			stage.close();
			
			Controlador.getInstance().accion(new Contexto(Events.SHOW_MAIN_MENU,null));
			
		} else {
			
			JOptionPane.showMessageDialog(null,"No se reconoce el usuario " + user.getNombre() + ".");
			
		}
		
	}
	
}
