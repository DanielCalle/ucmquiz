package negocio.usuario;

import java.util.List;

public interface SAUsuario {

	public int create(Usuario usuario);
	
	public Usuario read(int id);
	
	public Usuario readByName(String name);

	public List<Usuario> readAll();

	public int update(Usuario usuario);

	public int delete(int id);
	
}
