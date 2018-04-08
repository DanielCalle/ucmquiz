package negocio;

import negocio.asignatura.SAAsignatura;
import negocio.pregunta.SAPregunta;

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instance;

	public synchronized static FactoriaNegocio getInstance() {
		if(instance == null) instance = new FactoriaNegocioImp();
		return instance;
	}
	
	public abstract SAPregunta generateSAPregunta();

	public abstract SAAsignatura generateSAAsignatura();

}