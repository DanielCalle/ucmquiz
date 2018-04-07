package negocio;

import negocio.asignatura.SAAsignaturaImp;

public class FactoriaNegocioImp extends FactoriaNegocio {

	@Override
	public SAAsignaturaImp createSAAsignatura() {
		// TODO Auto-generated method stub
		return new SAAsignaturaImp();
	}
	

	
}