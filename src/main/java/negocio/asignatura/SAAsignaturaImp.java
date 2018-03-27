package negocio.asignatura;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import negocio.EntityManagerUtil;

public class SAAsignaturaImp implements SAAsignatura {

	@Override
	public int create(Asignatura asignatura) {
		
		int id = -1;
		
		if (asignatura != null) {
			
			EntityManager entitymanager = EntityManagerUtil.getEntityManager();
			
			EntityTransaction entitytransaction = entitymanager.getTransaction();
			
			entitytransaction.begin();
			
			TypedQuery<Asignatura> query = entitymanager.createNamedQuery("negocio.asignatura.Asignatura.findBytitulo", Asignatura.class);
			
			query.setParameter("titulo", asignatura.getTitulo());
			
			List<Asignatura> lista = query.getResultList();
			
			if (lista.isEmpty()) {
				
				entitymanager.persist(asignatura);
				
				entitytransaction.commit();
				
				id = asignatura.getId();
				
			} else {
				
				if (!lista.get(0).isActivo()) {
					
					Asignatura asignaturaResult = lista.get(0);
					
					asignaturaResult.setActivo(true);
					
					entitytransaction.commit();
					
					id = asignatura.getId();
				
				} else {
					
					entitytransaction.rollback();
	
				}

			}
			
			entitymanager.close();
		
		}
		
		return id;

	}

}
