package negocio;

import negocio.pregunta.SAPregunta;
import negocio.pregunta.SAPreguntaImp;

public class FactoriaNegocioImp extends FactoriaNegocio {

	@Override
	public SAPregunta generateSAPregunta() {
		
		return new SAPreguntaImp();
	
	}
	

	
}