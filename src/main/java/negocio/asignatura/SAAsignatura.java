package negocio.asignatura;

import presentacion.Contexto;

public interface SAAsignatura {
	
	public Contexto delete(int id);

	public Contexto create(Asignatura asignatura);

	public Contexto activeAsignatura(int id);
	
	public Contexto desactiveAsignatura(int id);

	public Contexto readAll();
	
}
