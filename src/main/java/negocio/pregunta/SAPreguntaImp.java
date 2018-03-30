
package negocio.pregunta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import negocio.EntityManagerUtil;

public class SAPreguntaImp implements SAPregunta {

	@Override
	public int create(Pregunta pregunta) {
			
		int id = -1;
			
		if (pregunta != null) {
				
			EntityManager entitymanager = EntityManagerUtil.getEntityManager();
				
				EntityTransaction entitytransaction = entitymanager.getTransaction();
				
				entitytransaction.begin();
				
				TypedQuery<Pregunta> query = entitymanager.createNamedQuery("negocio.pregunta.Pregunta.findBytexto", Pregunta.class);
				
				query.setParameter("texto", pregunta.getTexto());
				
				List<Pregunta> lista = query.getResultList();
				
				if (lista.isEmpty()) {
					
					entitymanager.persist(pregunta);
					
					entitytransaction.commit();
					
					id = pregunta.getId();
					
				} else {
					
					if (!lista.get(0).getActiva()) {
						
						Pregunta preguntaResult = lista.get(0);
						
						preguntaResult.setActiva(true);
						
						entitytransaction.commit();
						
						id = preguntaResult.getId();
					
					} else {
						
						entitytransaction.rollback();
		
					}

				}
				
				entitymanager.close();
			
			}
			
			return id;
		
	}

}
