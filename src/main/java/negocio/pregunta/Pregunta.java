package negocio.pregunta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import negocio.asignatura.Asignatura;

/**
 * Entidad de negocio Pregunta.
 * Esta clase contiene una realacion N : 1 con la entidad Asignatura.
 */
@Entity
public class Pregunta {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Version
	private int version;
	
	@ManyToOne
	private Asignatura asignatura;
	
	private String texto;
	
	private boolean activa;
	
	public Pregunta() {};
	
	/**
	 * @param texto Texto de la pregunta.
	 * @param activa Estado de la pregunta.
	 */
	public Pregunta(String texto, boolean activa) {		
		this.texto = texto;
		this.activa = activa;
	}

	/**
	 * @param id Identificador de la entidad.
	 * @param texto Texto de la pregunta.
	 * @param activa Estado de la pregunta.
	 */
	public Pregunta(int id, String texto, boolean activa) {
		this.id = id;		
		this.texto = texto;
		this.activa = activa;
	}

	/**
	 * @param id Identificador de la entidad.
	 */
	public void setId(Integer id) {
		this.id = id;
	}	

	/**
	 * @return Devuelve el identificador de la entidad pregunta.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return Devuelve el texto de la pregunta.
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto Texto de la pregunta.
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return Devuelve el estado de la pregunta.
	 */
	public boolean getActiva() {
		return activa;
	}

	/**
	 * @param activa Estado de la pregunta.
	 */
	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	/**
	 * @return Devuelve la asignatura con la que la pregunta tiene una relacion.
	 */
	public Asignatura getAsignatura() {
		return asignatura;
	}

	/**
	 * @param asignatura Asignatura con la que esta relacionada la pregunta.
	 */
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

}