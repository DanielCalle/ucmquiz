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
	private boolean activo;
	public Respuesta() {};
	
	public Respuesta(boolean respuesta, String titulo) {
		this.setCorrecto(respuesta);
		this.setTitulo(titulo);
	}
	public int getId() {
		return this.id;
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
	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
