package negocio.asignatura;

import presentacion.Contexto;

public interface SAAsignatura {
	
	public Contexto delete(int id);

	Contexto create(Asignatura asignatura);
	
}
