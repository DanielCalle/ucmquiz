package negocio.pregunta;

import java.util.List;

public interface SAPregunta {

	public int create(Pregunta pregunta);
	
	public Pregunta read(int id);

	public List<Pregunta> readAll();

	public int update(Pregunta pregunta);

	public int delete(int id);
	
}
