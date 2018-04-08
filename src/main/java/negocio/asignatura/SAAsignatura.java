package negocio.asignatura;

import presentacion.Contexto;

public interface SAAsignatura {

	public Contexto activaAsignatura(int id);
	
	public Contexto desactivaAsignatura(int id);

	public Contexto readAll();
	
}
