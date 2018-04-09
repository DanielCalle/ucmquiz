
package negocio.pregunta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import negocio.EntityManagerUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

public class SAPreguntaImp implements SAPregunta {

	public Contexto borrarPregunta(int idPregunta) {
		Events event = null;

		Contexto contexto = null;

		Filter filter = new Filter();

		Pregunta p = null;
		EntityManager entitymanager = EntityManagerUtil.getEntityManager();
		EntityTransaction entitytransaction = entitymanager.getTransaction();
		entitytransaction.begin();

		try {
			p = entitymanager.find(Pregunta.class, idPregunta); // se busca el objeto por la clave primaria (el id)

			//p.setAsignatura(null); // se desvincula la pregunta con la asignatura (haciendo la dependencia null)
			//entitymanager.refresh(p); // se actualiza la entidad en bbdd
			p.setActiva(false);
			entitytransaction.commit();
			event = Events.CRUD_DELETE_PREGUNTA_OK;
			
			filter.addFilter("info", "");

			event.setFilter(filter);
			contexto = new Contexto(event, null);

		} catch (IllegalArgumentException iae) { // si el id pasado no existe el entitymanager soltará error
			iae.printStackTrace();
			entitytransaction.rollback();
			event = Events.CRUD_DELETE_PREGUNTA_KO;
			filter.addFilter("reason", "que el id introducido no exista");
			
			filter.addFilter("info", "");

			event.setFilter(filter);
			contexto = new Contexto(event, null);
		} finally {
			entitymanager.close();
		}
		return contexto;
	}

		/**
	 * @param pregunta
	 *            Objeto que contiene los datos de una pregunta que se quiere crear.
	 * @return Objeto que contiene un evento que indica el resultado de las
	 *         operaciones y un dato.
	 */
	@Override
	public Contexto create(Pregunta pregunta) {

		Events event = null;

		Contexto contexto = null;

		Filter filter = new Filter();

		if (pregunta != null) {

			EntityManager entitymanager = EntityManagerUtil.getEntityManager();

			EntityTransaction entitytransaction = entitymanager.getTransaction();

			entitytransaction.begin();

			TypedQuery<Pregunta> query = entitymanager.createNamedQuery("negocio.pregunta.Pregunta.findBytexto",
					Pregunta.class);

			query.setParameter("texto", pregunta.getTexto());

			List<Pregunta> lista = query.getResultList();

			if (lista.isEmpty()) {

				entitymanager.persist(pregunta);

				entitytransaction.commit();

				event = Events.CRUD_CREATE_PREGUNTA_OK;

				filter.addFilter("info", "");

				event.setFilter(filter);

				contexto = new Contexto(event, pregunta.getId());

			} else {

				if (!lista.get(0).getActiva()) {

					Pregunta preguntaResult = lista.get(0);

					preguntaResult.setActiva(true);

					entitytransaction.commit();

					event = Events.CRUD_CREATE_PREGUNTA_OK;

					filter.addFilter("info", "");

					event.setFilter(filter);

					contexto = new Contexto(event, preguntaResult.getId());

				} else {

					entitytransaction.rollback();

					event = Events.CRUD_CREATE_PREGUNTA_KO;

					filter.addFilter("reason", "que ya existia");

					filter.addFilter("info", "");

					event.setFilter(filter);

					contexto = new Contexto(event, null);

				}

			}

			entitymanager.close();

		}

		return contexto;

	}

}
