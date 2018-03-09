package presentacion.command;

import negocio.FactoriaNegocio;
import negocio.usuario.Usuario;
import presentacion.Command;
import presentacion.Contexto;
import presentacion.Events;

public class CommandUserLogin implements Command {

	@Override
	public Contexto execute(Object data) {
		
		Contexto context = null;
		
		Usuario user = FactoriaNegocio.getInstance().generateSAUsuario().readByName(((Usuario)data).getNombre());
		
		if(user != null)
			
			context = new Contexto(Events.USER_LOGIN_OK, user);
		
		else
			
			context = new Contexto(Events.USER_LOGIN_KO, user);
		
		return context;
	
	}

}
