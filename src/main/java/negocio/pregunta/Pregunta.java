package negocio.pregunta; 

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import negocio.asignatura.Asignatura;
import negocio.respuesta.Respuesta;

/**
 * Entidad de negocio Pregunta.
 * Esta clase contiene una realacion N : 1 con la entidad Asignatura.
 */
@Entity
@NamedQueries({
	@NamedQuery(
		name = "negocio.pregunta.Pregunta.findBytexto", 
		query = "select obj from Pregunta obj where obj.texto = :texto"
	),
	@NamedQuery(
			name = "negocio.pregunta.Pregunta.findByAsignatura", 
			query = "select obj from Pregunta obj where obj.asignatura = :asignatura"
	),
	@NamedQuery(name = "negocio.pregunta.Pregunta.readAll", 
	query = "select obj from Pregunta obj")
})
public class Pregunta extends RecursiveTreeObject<Pregunta> {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Version
	private int version;
	
	@ManyToOne
	private Asignatura asignatura;
	
	private String texto;
	
	private boolean activa;

	@OneToMany(mappedBy="Pregunta", cascade = { CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
	private List<Respuesta> respuestas;
	
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

	public List<Respuesta> getRespuestas() {
		// TODO Auto-generated method stub
		return this.respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

}