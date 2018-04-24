package negocio.respuesta;

import presentacion.Contexto;

public interface SARespuesta {

	public Contexto borrarRespuesta(String respuesta);
	
	public Contexto borrarRespuesta(int idRespuesta);
}
