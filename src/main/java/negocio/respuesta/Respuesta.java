package negocio.respuesta; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import negocio.pregunta.Pregunta;

/**
 * Entidad de negocio respuesta.
 * Esta relacion mantiene una relacion N : 1 con la entidad pregunta.
 */
@Entity
public class Respuesta extends RecursiveTreeObject<Respuesta>{

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Version
	private int version;
	
	@ManyToOne
	private Pregunta pregunta;
	
	private String texto;
	
	private boolean activa;
	
	private boolean correcta;
	
	public Respuesta() {};
	
	/**
	 * @param texto Texto de la respuesta. 
	 * @param correcta Indica si la respuesta es o no correcta.
	 * @param activa Indica el estado de la respuesta.
	 */
	public Respuesta(String texto, boolean correcta, boolean activa) {
		this.texto = texto;
		this.activa = activa;
		this.correcta = correcta;
	}
	
	/**
	 * @param id Identificador de la respuesta.
	 * @param texto Texto de la respuesta. 
	 * @param correcta Indica si la respuesta es o no correcta.
	 * @param activa Indica el estado de la respuesta.
	 */
	public Respuesta(int id, String texto, boolean correcta, boolean activa) {
		this.id = id;
		this.texto = texto;
		this.activa = activa;
		this.correcta = correcta;
	}
	
	/**
	 * @return Devuelve el identificador de la entidad respuesta.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @param id Identificador de la respuesta. 
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return Devuelve el texto de la respuesta.
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto Texto de la respuesta.
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return Devuelve si es o no correcta la respuesta.
	 */
	public boolean isCorrecta() {
		return correcta;
	}
	
	/**
	 * @param correcta Estado que indica si la respuesta es o no correcta.
	 */
	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}
	
	/**
	 * @return Devuelve el estado de la respuesta.
	 */
	public boolean getActiva() {
		return activa;
	}

	/**
	 * @param activa Estado de la respuesta.
	 */
	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	/**
	 * @return Devuelve la preunta a la que esta asociada esta respuesta.
	 */
	public Pregunta getPregunta() {
		return this.pregunta;
	}
	
	/**
	 * @param p Pregunta a la que se asocia esta respuesta.
	 */
	public void setPregunta(Pregunta p) {
		this.pregunta = p;
	}
	public StringProperty getTituloStringProperty() {
		return new SimpleStringProperty(texto);
		
	}
	public StringProperty getCorrectaStringProperty() {
		if(correcta) return new SimpleStringProperty("CORRECTA");
		else return new SimpleStringProperty("INCORRECTA");
		
	}
	
}
