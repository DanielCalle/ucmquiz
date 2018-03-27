package negocio.respuesta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import negocio.pregunta.Pregunta;

@Entity
public class Respuesta {

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
	
	public Respuesta(String texto, boolean correcta, boolean activa) {
		this.texto = texto;
		this.activa = activa;
		this.correcta = correcta;
	}
	
	public Respuesta(int id, String texto, boolean correcta, boolean activa) {
		this.id = id;
		this.texto = texto;
		this.activa = activa;
		this.correcta = correcta;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isCorrecta() {
		return correcta;
	}
	
	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}
	public boolean getActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public Pregunta getPregunta() {
		return this.pregunta;
	}
	public void setPregunta(Pregunta p) {
		this.pregunta = p;
	}
	
}
