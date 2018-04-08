package presentacion.commands;

import negocio.FactoriaNegocio;
import negocio.asignatura.Asignatura;
import presentacion.Command;
import presentacion.Contexto;

public class CommandCrearAsignatura implements Command {

	@Override
	public Contexto execute(Object data) {
		
		Asignatura asignatura = (Asignatura) data;
		return FactoriaNegocio.getInstance().generateSAAsignatura().create(asignatura);
	}


}
