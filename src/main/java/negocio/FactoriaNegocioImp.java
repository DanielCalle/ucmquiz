package negocio;

import negocio.asignatura.SAAsignatura;
import negocio.asignatura.SAAsignaturaImp;
import negocio.pregunta.SAPregunta;
import negocio.pregunta.SAPreguntaImp;
import negocio.respuesta.SARespuesta;
import negocio.respuesta.SARespuestaImp;

public class FactoriaNegocioImp extends FactoriaNegocio {


	@Override
	public SAAsignatura generateSAAsignatura() {
		return new SAAsignaturaImp();
	}

	@Override

	public SAPregunta generateSAPregunta() {
		
		return new SAPreguntaImp();
	}
	
	public SARespuesta generateSARespuesta() {
		return new SARespuestaImp();
	}
	
}