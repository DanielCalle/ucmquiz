package negocio;

import negocio.asignatura.SAAsignatura;
import negocio.asignatura.SAAsignaturaImp;
import negocio.pregunta.SAPregunta;
import negocio.pregunta.SAPreguntaImp;

public class FactoriaNegocioImp extends FactoriaNegocio {

	public SAPregunta generateSAPregunta() {
		
		return new SAPreguntaImp();
	}
	
	@Override
	public SAAsignatura generateSAAsignatura() {
		
		return new SAAsignaturaImp();
		
	}

}