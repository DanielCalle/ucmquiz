package presentacion.command;

import negocio.FactoriaNegocio;
import negocio.usuario.Usuario;
import presentacion.Command;
import presentacion.Contexto;

public class CommandUserLogin implements Command {

	@Override
	public Contexto execute(Object data) {
		
		return FactoriaNegocio.getInstance().generateSAUsuario.readByName(((Usuario)data).getNombre());
	
	}

}
