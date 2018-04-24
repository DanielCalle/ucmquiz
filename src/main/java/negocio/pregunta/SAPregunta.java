package negocio.pregunta;

import negocio.respuesta.Respuesta;
import presentacion.Contexto;

public interface SAPregunta {

	public Contexto create(Pregunta pregunta);

	public Contexto borrarPregunta(int idPregunta);
	
		
}
