package negocio;

import negocio.asignatura.SAAsignaturaImp;
import negocio.asignatura.SAAsignatura;
import negocio.pregunta.SAPregunta;
import negocio.respuesta.SARespuesta;

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instance;

	public synchronized static FactoriaNegocio getInstance() {
		if(instance == null) instance = new FactoriaNegocioImp();
		return instance;
	}
	
	public abstract SAPregunta generateSAPregunta();

	public abstract SAAsignatura generateSAAsignatura();
	
	public abstract SARespuesta generateSARespuesta();

}