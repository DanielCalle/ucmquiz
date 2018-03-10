package presentacion.commands;

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
		
		
		if(user != null && ((Usuario)data).getPassword().equalsIgnoreCase(user.getPassword()))
			
			context = new Contexto(Events.USER_LOGIN_OK, user);
		
		else
			
			context = new Contexto(Events.USER_LOGIN_KO, null);
		
		return context;
	
	}

}
