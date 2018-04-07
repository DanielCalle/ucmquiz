package negocio;

import negocio.asignatura.SAAsignaturaImp;

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instance;

	public synchronized static FactoriaNegocio getInstance() {
		if(instance == null) instance = new FactoriaNegocioImp();
		return instance;
	}

	abstract public SAAsignaturaImp createSAAsignatura();

}