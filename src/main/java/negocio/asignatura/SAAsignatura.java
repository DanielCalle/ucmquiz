package negocio.asignatura;

import java.util.List;

import presentacion.Contexto;

public interface SAAsignatura {
	
	public Contexto readAll();

	
	public Contexto delete(int id);

	public Contexto create(Asignatura asignatura);

	public Contexto activeAsignatura(int id);
	
	public Contexto desactiveAsignatura(int id);
	
	public Contexto read(int id);

	
}
