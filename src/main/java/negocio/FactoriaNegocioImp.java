package negocio;

import negocio.asignatura.SAAsignatura;
import negocio.asignatura.SAAsignaturaImp;

public class FactoriaNegocioImp extends FactoriaNegocio {

	@Override
	public SAAsignatura createSAAsignatura() {
		return new SAAsignaturaImp();
	}
	
	
	
}