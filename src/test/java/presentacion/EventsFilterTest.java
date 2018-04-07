package presentacion;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class EventsFilterTest {
	
	@Mock
	private Filter filter;

	@Before
	public void preparations() {
		filter = Mockito.mock(Filter.class);
	}
	
	@Test // Para ver si cada mensaje tiene su mensaje 
	public void eventsWithTheirMessageInXML() {
		// Es correcto si no salta excepcion
		for (Events e : Events.values()) {
			try {
				e.setFilter(filter).getMessage();
			}
			catch (NullPointerException ex){
				System.out.println(e.name());
				throw ex;
			}
		}
	}

}
