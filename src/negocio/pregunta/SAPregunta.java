package negocio.pregunta;

import java.util.ArrayList;

public interface SAPregunta {

	public int create(Pregunta pregunta);
	
	public Pregunta read(int id);

	public ArrayList<Pregunta> readAll();

	public int update(Pregunta pregunta);

	public int delete(int id);
	
}
