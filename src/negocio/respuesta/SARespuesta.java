package negocio.respuesta;

import java.util.List;


public interface SARespuesta {

	public int create(Respuesta respuesta);
	
	public Respuesta read(int id);

	public List<Respuesta> readAll();

	public int update(Respuesta respuesta);

	public int delete(int id);
	
}
