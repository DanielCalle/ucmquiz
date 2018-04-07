package presentacion;

public class Contexto {
	
	private Events evento;
	private Object dato;

	public Contexto(Events evento, Object dato) {
		this.dato = dato;
		this.evento = evento;
	}

	public Events getEvent() {
		return evento;
	}

	public Object getDato() {
		return dato;
	}

	public void setEvent(Events event) {
		this.evento = event;
	}

	public void setDato(Object dato) {
		this.dato = dato;
	}
	
}