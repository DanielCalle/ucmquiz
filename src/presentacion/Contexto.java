package presentacion;

public class Contexto {
	
	private int evento;
	private Object dato;

	public Contexto(int evento, Object dato) {
		this.dato = dato;
		this.evento = evento;
	}

	public int getEvent() {
		return evento;
	}

	public Object getDato() {
		return dato;
	}

	public void setEvent(int event) {
		this.evento = event;
	}

	public void setDato(Object dato) {
		this.dato = dato;
	}
	
}