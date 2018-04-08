package negocio.asignatura;

import presentacion.Contexto;

public interface SAAsignatura {
	
	public Contexto delete(int id);

	public Contexto activaAsignatura(int id);
	
	public Contexto desactivaAsignatura(int id);
	
	Contexto create(Asignatura asignatura);
	
}
