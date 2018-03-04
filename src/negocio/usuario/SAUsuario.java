package negocio.usuario;

import java.util.ArrayList;

public interface SAUsuario {

	public int create(Usuario usuario);
	
	public Usuario read(int id);

	public ArrayList<Usuario> readAll();

	public int update(Usuario usuario);

	public int delete(int id);
	
}
