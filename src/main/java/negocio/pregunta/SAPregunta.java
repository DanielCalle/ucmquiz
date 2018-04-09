package negocio.pregunta;

import presentacion.Contexto;

public interface SAPregunta {

	public Contexto create(Pregunta pregunta);

	public Contexto borrarPregunta(int idPregunta);
	
}
