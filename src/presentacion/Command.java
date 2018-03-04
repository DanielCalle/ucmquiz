package presentacion;

public interface Command {
	
	public Contexto execute(Object data);
}