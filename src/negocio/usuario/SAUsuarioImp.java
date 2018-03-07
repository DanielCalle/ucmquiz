package negocio.usuario;

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
		if (usuario != null) {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ucmquizPostgres");
			try {
				EntityManager entitymanager = emfactory.createEntityManager();
				EntityTransaction entitytransaction = entitymanager.getTransaction();
				entitytransaction.begin();
				TypedQuery<Usuario> query = entitymanager
						.createNamedQuery("negocio.usuario.Usuario.findBynombre", Usuario.class)
						.setParameter("nombre", usuario.getNombre());
				List<Usuario> lista = query.getResultList();
				if (lista.isEmpty()) {
					entitymanager.persist(usuario);
					entitytransaction.commit();
					id = usuario.getId();
				} else {
					if (!lista.get(0).isActivo()) {
						Usuario usuarioResult = lista.get(0);
						usuarioResult.setActivo(true);
						entitytransaction.commit();
						id = 1;
					} else {
						entitytransaction.rollback();
						id = -1;
					}

				}
				entitymanager.close();
				emfactory.close();
			} catch (PersistenceException ex) {
				id = -1;
			}
		}
		return id;
	}

	@Override
	public Usuario read(int id) {
		Usuario usuario = null;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ucmquizPostgres");
		try {
			EntityManager entitymanager = emfactory.createEntityManager();
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			entitytransaction.begin();
			usuario = entitymanager.find(Usuario.class, id);
			if (usuario == null)
				entitytransaction.rollback();
			else
				entitytransaction.commit();
			entitymanager.close();
			emfactory.close();
		} catch (PersistenceException ex) {
		}
		return usuario;
	}

	@Override
	public List<Usuario> readAll() {
		List<Usuario> lista = null;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ucmquizPostgres");

		EntityManager entitymanager = emfactory.createEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();

		TypedQuery<Usuario> query = entitymanager.createNamedQuery("negocio.usuario.Usuario.readAll", Usuario.class);
		lista = query.getResultList();
		entitytransaction.commit();

		entitymanager.close();
		emfactory.close();

		return lista;
	}

	@Override
	public int update(Usuario usuario) {
		int id = -1;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ucmquiz");

		EntityManager entitymanager = emfactory.createEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();
		
		Usuario usuarioResult = entitymanager.find(Usuario.class, usuario.getId());
		if(usuario.isActivo()) {
			usuarioResult.setEmail(usuario.getEmail());
			usuarioResult.setNombre(usuario.getNombre());
			usuarioResult.setPassword(usuario.getPassword());
			entitytransaction.commit();
			id = 1;
		}
		else {
			entitytransaction.rollback();
		}
		return id;
	}

	@Override
	public int delete(int id) {
		int id_res = -1;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ucmquizPostgres");
		try {
			EntityManager entitymanager = emfactory.createEntityManager();
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			entitytransaction.begin();
			Usuario usuario = entitymanager.find(Usuario.class, id);
			if (usuario != null) {
				if (usuario.isActivo()) {

					usuario.setActivo(false);

					entitytransaction.commit();
					id_res = 1;
				} else {
					entitytransaction.rollback();
				}

			} else {
				entitytransaction.rollback();
			}
			entitymanager.close();
			emfactory.close();
		} catch (PersistenceException ex) {
			id = -100;
		}
		return id_res;
	}

	@Override
	public Usuario readByName(String name) {
		Usuario usuario = null;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ucmquizPostgres");
			EntityManager entitymanager = emfactory.createEntityManager();
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			entitytransaction.begin();
			TypedQuery<Usuario> query = entitymanager
					.createNamedQuery("negocio.usuario.Usuario.findBynombre", Usuario.class)
					.setParameter("nombre", name);
			List<Usuario> lista = query.getResultList();
			if(!lista.isEmpty()) {
				usuario = lista.get(0);
			}
			entitytransaction.commit();
			entitymanager.close();
			emfactory.close();
			return usuario;
	}

}
