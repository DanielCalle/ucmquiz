package negocio.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity

@NamedQueries({
	@NamedQuery(name = "negocio.usuario.Usuario.readAll", query = "select obj from Usuario obj where obj.activo = true"),
	@NamedQuery(name = "negocio.usuario.Usuario.findBynombre", query = "select obj from Usuario obj where obj.nombre = :nombre")})

@Table(name = "USUARIO")
public class Usuario {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Version
	private int version;
	
	private String email;

	private String nombre;
	
	private String password;
	
	private boolean activo;

	public Usuario() {}
	
	public Usuario(String email, String nombre, String password) {
		this.email = email;
		this.nombre = nombre;
		this.password = password;
		this.activo = true;
	}
	
	public Usuario(Integer id, String email, String nombre, String password) {
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.password = password;
		this.activo = true;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	

}
