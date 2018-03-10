package negocio.pregunta;

import java.util.Collection;

import javax.persistence.*;

import negocio.respuesta.Respuesta;

@Entity

@NamedQueries({
	@NamedQuery(name = "negocio.pregunta.Pregunta.readAll", query = "select obj from Pregunta obj where obj.activo = true"),
	@NamedQuery(name = "negocio.pregunta.Pregunta.findBytitulo", query = "select obj from Pregunta obj where obj.titulo = :titulo")})

public class Pregunta {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Version
	private int version;
	
	@OneToMany(mappedBy="pregunta")
	private Collection<Respuesta> respuestas;
	
	private String titulo;
	private boolean activo;
	
	public Pregunta() {};
	
	public Pregunta(String titulo, boolean activo) {
		this.titulo = titulo;
		this.activo = activo;
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

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
