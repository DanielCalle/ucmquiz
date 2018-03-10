package negocio.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import negocio.EntityManagerUtil;
import negocio.pregunta.Pregunta;

public class SAUsuarioImp implements SAUsuario {

	@Override
	public int create(Usuario usuario) {
		int id = 1;
		if (usuario != null) {
			EntityManager entitymanager = EntityManagerUtil.getEntityManager();
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
		}
		return id;
	}

	@Override
	public Usuario read(int id) {
		Usuario usuario = null;
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();
		usuario = entitymanager.find(Usuario.class, id);
		if (usuario == null)
			entitytransaction.rollback();
		else
			entitytransaction.commit();
		entitymanager.close();
		return usuario;
	}

	@Override
	public List<Usuario> readAll() {
		List<Usuario> lista = null;
		
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();

		TypedQuery<Usuario> query = entitymanager.createNamedQuery("negocio.usuario.Usuario.readAll", Usuario.class);
		lista = query.getResultList();
		entitytransaction.commit();

		entitymanager.close();

		return lista;
	}

	@Override
	public int update(Usuario usuario) {
		int id = -1;
		
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
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
		entitymanager.close();
		return id;
	}

	@Override
	public int delete(int id) {
		int id_res = -1;
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
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
		return id_res;
	}

	@Override
	public Usuario readByName(String name) {
		Usuario usuario = null;
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
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
		return usuario;
	}

}
