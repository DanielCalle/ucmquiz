package negocio.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public class SAUsuarioImp implements SAUsuario {

	@Override
	public int create(Usuario usuario) {
		int id = 1;
		if(usuario != null) {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("");
			try {
				EntityManager entitymanager = emfactory.createEntityManager();
				EntityTransaction entitytransaction = entitymanager.getTransaction();
				entitytransaction.begin();
				TypedQuery<Usuario> query = entitymanager.createNamedQuery("negocio.usuario.Usuario.findBynombre", Usuario.class).setParameter("nombre", usuario.getNombre());
				List<Usuario> lista = query.getResultList();
				if(lista.isEmpty()) {
					entitymanager.persist(usuario);
					entitytransaction.commit();
					id = usuario.getId();
				}
				else {
					entitytransaction.rollback();
					id = -1;
					
				}
				entitymanager.close();
				emfactory.close();
			} catch(PersistenceException ex) {
				id = -1;
			}
		}
		return id;
	}

	@Override
	public Usuario read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Usuario> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
