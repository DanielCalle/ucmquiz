package negocio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class EntityManagerUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	
	public synchronized static EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
			try {
				entityManagerFactory = Persistence.createEntityManagerFactory("ucmquizPostgres");
			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
		return entityManagerFactory.createEntityManager();
	}

	
}