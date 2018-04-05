package negocio.pregunta;

import presentacion.Contexto;

public interface SAPregunta {
	
	public Contexto activarPregunta(int id);
	
	public Contexto desactivarPregunta(int id);

}
