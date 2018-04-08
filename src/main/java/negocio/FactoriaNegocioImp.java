package negocio;

import negocio.asignatura.SAAsignaturaImp;
import negocio.pregunta.SAPregunta;
import negocio.pregunta.SAPreguntaImp;

public class FactoriaNegocioImp extends FactoriaNegocio {

	@Override
	public SAAsignaturaImp createSAAsignatura() {
		// TODO Auto-generated method stub
		return new SAAsignaturaImp();
	}
	public SAPregunta generateSAPregunta() {
		
		return new SAPreguntaImp();
	}
	

	
}