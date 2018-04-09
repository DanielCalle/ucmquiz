package negocio.asignatura; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Entidad de negocio Asignatura.
 * Esta clase contiene una relacion 1 : N con la entidad Pregunta.
 */
@Entity
@NamedQueries({
	@NamedQuery(
		name = "negocio.asignatura.Asignatura.findBytitulo", 
		query = "select obj from Asignatura obj where obj.titulo = :titulo"
		),
	@NamedQuery(name = "negocio.asignatura.Asignatura.readAll", 
	query = "select obj from Asignatura obj where obj.activo = 1") })

public class Asignatura extends RecursiveTreeObject<Asignatura> {



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
	public StringProperty getTituloStringProperty() {
		return new SimpleStringProperty(titulo);
	}
	public StringProperty getActivoStringProperty() {
		if(activo)return new SimpleStringProperty("Activada");
		else return new SimpleStringProperty("Desactivada");
		
	}
	public StringProperty getIdStringProperty() {
		return new SimpleStringProperty(id.toString());
		
	}

}
