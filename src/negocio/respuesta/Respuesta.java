package negocio.respuesta;

import javax.persistence.*;

import negocio.pregunta.Pregunta;

@Entity
public class Respuesta {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Version
	private int version;
	
	@ManyToOne
	private Pregunta pregunta;
	
	private boolean correcto;
	private String titulo;
	
	public Respuesta() {};
	
	public Respuesta(boolean respuesta, String titulo) {
		this.setCorrecto(respuesta);
		this.setTitulo(titulo);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}
}
