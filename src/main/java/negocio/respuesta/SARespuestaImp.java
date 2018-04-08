package negocio.respuesta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceException;

import negocio.ComprobadorSintactico;
import negocio.EntityManagerUtil;
import negocio.pregunta.Pregunta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;

public class SARespuestaImp implements SARespuesta {

	/**
	 * Realiza la operacion de crear una Respuesta
	 * @param respuesta La respuesta que se quiere crear.
	 * @return Devuelve un contexto del id de la Respuesta creada y del estado de la operacion.
	 * @author Daniel Calle
	 */
	@Override
	public Contexto create(Respuesta respuesta) {
		Events e;
		Filter filter = new Filter();
		int id = -1;
		//Se comprueba que no hay referencias null
		if(respuesta != null && respuesta.getPregunta() != null) {
			String texto = respuesta.getTexto();
			int idPregunta = respuesta.getPregunta().getId();
			//Se comprueba que el texto no es vacio y el id de pregunta no sea negativo
			if(!texto.isEmpty() && ComprobadorSintactico.isPositive(idPregunta)) {
				try {
					EntityManager entitymanager = EntityManagerUtil.getEntityManager();
					EntityTransaction entitytransaction = entitymanager.getTransaction();
					entitytransaction.begin();
					Pregunta preguntaResult = entitymanager.find(Pregunta.class, idPregunta, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
					//Se comprueba que existe la pregunta a la que asociar la respuesta
					if(preguntaResult != null) {
						respuesta.setActiva(true);
						respuesta.setPregunta(preguntaResult);
						//Se crea la respuesta
						entitymanager.persist(respuesta);
						entitytransaction.commit();
						e = Events.CRUD_CREATE_RESPUESTA_OK;
						filter.addFilter("info", respuesta.getTexto());
					} else {
						e = Events.CRUD_CREATE_RESPUESTA_KO;
						filter.addFilter("reason","ya existe");
						filter.addFilter("info", respuesta.getTexto());
					}
					entitymanager.close();
				} catch(PersistenceException ex) {
					e = Events.CRUD_CREATE_RESPUESTA_KO;
					filter.addFilter("reason","problemas técnicos");
					filter.addFilter("info", respuesta.getTexto());
				}
			} else
				e = Events.CRUD_CREATE_RESPUESTA_KO;
				filter.addFilter("reason","la sintaxis no es correcta");
				filter.addFilter("info", respuesta.getTexto());
			
		} else {
			e = Events.CRUD_CREATE_RESPUESTA_KO;
			filter.addFilter("reason","los datos se han formado mal");
			filter.addFilter("info", respuesta.getTexto());
		}
		
		e.setFilter(filter);
		
		return new Contexto(e,id);
	}
}
