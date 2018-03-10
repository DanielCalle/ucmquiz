package negocio;

import negocio.pregunta.SAPregunta;
import negocio.respuesta.SARespuesta;
import negocio.usuario.SAUsuario;

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instance;

	public synchronized static FactoriaNegocio getInstance() {
		if(instance == null) instance = new FactoriaNegocioImp();
		return instance;
	}
	
	public abstract SAUsuario generateSAUsuario();
	
	public abstract SAPregunta generateSAPregunta();
	
	public abstract SARespuesta generateSAREspuesta();

}