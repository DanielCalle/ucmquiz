package negocio;

import negocio.asignatura.SAAsignatura;

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instance;

	public synchronized static FactoriaNegocio getInstance() {
		if(instance == null) instance = new FactoriaNegocioImp();
		return instance;
	}
	
	public abstract SAAsignatura generateSAAsignatura();

}