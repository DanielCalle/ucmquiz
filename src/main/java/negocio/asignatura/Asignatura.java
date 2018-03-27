package negocio.asignatura;

import java.util.Collection;

import javax.persistence.*;

import negocio.pregunta.Pregunta;

@Entity
public class Asignatura {


	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Version
	private int version;
	
	public Asignatura() {
		
	}
	
	public Asignatura(Integer id, String titulo, boolean activo) {
		this.id = id;
		this.titulo = titulo;
		this.activo = activo;
	}
	
	public Asignatura(String titulo, boolean activo) {
		this.titulo = titulo;
		this.activo = activo;
	}
	
	@OneToMany(mappedBy="asignatura")
	private Collection<Pregunta> preguntas;

	private String titulo;

	private boolean activo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}