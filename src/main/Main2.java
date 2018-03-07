package main;

import negocio.FactoriaNegocio;
import negocio.usuario.Usuario;

public class Main2 {

	public static void main(String[] args) {
		// Usuario(String email, String nombre, String password)
		Usuario u1 = new Usuario("pepe11@ucm.es", "pepe", "pepe");
		 int id = FactoriaNegocio.getInstance().generateSAUsuario().create(u1);
		 System.out.println(FactoriaNegocio.getInstance().generateSAUsuario().read(id).getEmail());
		 System.out.println("email del readByName " + FactoriaNegocio.getInstance().generateSAUsuario().readByName("pepe").getEmail());
		 FactoriaNegocio.getInstance().generateSAUsuario().delete(id);
		 
	}
	

}
