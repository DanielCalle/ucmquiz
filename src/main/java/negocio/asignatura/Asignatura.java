package negocio.asignatura; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

/**
 * Entidad de negocio Asignatura.
 * Esta clase contiene una relacion 1 : N con la entidad Pregunta.
 */
@Entity
@NamedQueries({
	@NamedQuery(
		name = "negocio.asignatura.Asignatura.findBytitulo", 
		query = "select obj from Asignatura obj where obj.titulo = :titulo"
	)
})
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
