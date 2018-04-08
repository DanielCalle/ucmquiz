package negocio;

import negocio.asignatura.SAAsignatura;
import negocio.asignatura.SAAsignaturaImp;
<<<<<<< HEAD
=======
import negocio.pregunta.SAPregunta;
import negocio.pregunta.SAPreguntaImp;
>>>>>>> refs/remotes/origin/develop

public class FactoriaNegocioImp extends FactoriaNegocio {

	@Override
	public SAPregunta generateSAPregunta() {
		
		return new SAPreguntaImp();
	
	}
	
	@Override
	public SAAsignatura generateSAAsignatura() {
		
		return new SAAsignaturaImp();
		
	}

}