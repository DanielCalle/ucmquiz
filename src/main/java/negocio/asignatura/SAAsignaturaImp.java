package negocio.asignatura;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import negocio.EntityManagerUtil;

public class SAAsignaturaImp implements SAAsignatura {

	@Override
	public List<Asignatura> readAll() {
		List<Asignatura> lista = null;
		try {
			EntityManager entitymanager = EntityManagerUtil.getEntityManager();
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			entitytransaction.begin();
				
			TypedQuery<Asignatura> query = entitymanager.createNamedQuery("negocio.asignatura.Asignatura.readAll", Asignatura.class);
			lista = query.getResultList();
			entitytransaction.commit();
				
			entitymanager.close();
		} catch(PersistenceException ex) {}
		
		return lista;
	}

}
