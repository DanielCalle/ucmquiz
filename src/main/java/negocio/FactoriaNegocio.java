package negocio;

import negocio.asignatura.SAAsignatura;
<<<<<<< HEAD
=======
import negocio.pregunta.SAPregunta;
>>>>>>> refs/remotes/origin/develop

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instance;

	public synchronized static FactoriaNegocio getInstance() {
		if(instance == null) instance = new FactoriaNegocioImp();
		return instance;
	}
	
	public abstract SAAsignatura createSAAsignatura();

	public abstract SAPregunta generateSAPregunta();

	public abstract SAAsignatura generateSAAsignatura();

}