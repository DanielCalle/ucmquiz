package negocio;

import negocio.asignatura.SAAsignatura;
import negocio.asignatura.SAAsignaturaImp;
import negocio.pregunta.SAPregunta;
import negocio.pregunta.SAPreguntaImp;

public class FactoriaNegocioImp extends FactoriaNegocio {


	@Override
	public SAAsignatura generateSAAsignatura() {
		return new SAAsignaturaImp();
	}

	@Override

	public SAPregunta generateSAPregunta() {
		
		return new SAPreguntaImp();
	}
	
}