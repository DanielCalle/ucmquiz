package negocio;

import negocio.asignatura.SAAsignaturaImp;
import negocio.pregunta.SAPregunta;

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instance;

	public synchronized static FactoriaNegocio getInstance() {
		if(instance == null) instance = new FactoriaNegocioImp();
		return instance;
	}
	
	public abstract SAPregunta generateSAPregunta();

	abstract public SAAsignaturaImp createSAAsignatura();

}