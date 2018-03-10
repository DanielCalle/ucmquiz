package negocio.respuesta;

import javax.persistence.*;

import negocio.pregunta.Pregunta;

@Entity
@NamedQueries({
	
	@NamedQuery(name = "negocio.respuesta.Respuesta.readAll", query = "select obj from Respuesta obj where obj.activo = 1"),
	
	})
public class Respuesta {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Version
	private int version;
	
	@ManyToOne
	private Pregunta pregunta;
	
	public Pregunta getPregunta() {
		return this.pregunta;
	}
	public void setPregunta(Pregunta p) {
		this.pregunta = p;
	}
	
	private boolean correcto;
	private String titulo;
	private boolean activo;
	public Respuesta() {};
	
	public Respuesta(boolean respuesta, String titulo) {
		this.setCorrecto(respuesta);
		this.setTitulo(titulo);
	}
	public Respuesta(boolean respuesta, String titulo, Pregunta p) {
		this.setCorrecto(respuesta);
		this.setTitulo(titulo);
		this.pregunta = p;
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
