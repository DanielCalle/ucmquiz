package presentacion;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public enum Events {
	
	CRUD_CREATE_OK,
	CRUD_CREATE_KO,
	CRUD_READ_OK,
	CRUD_READ_KO,
	CRUD_READ_ALL_OK,
	CRUD_READ_ALL_KO,
	CRUD_UPDATE_OK,
	CRUD_UPDATE_KO,
	CRUD_DELETE_OK,
	CRUD_DELETE_KO,
	
	SHOW_LOGIN,
	SHOW_ROL_MENU,
	
	COMMAND_USER_LOGIN,
	
	;

	private Filter filter;
	private InputSource inputSource;
	
	private Events() {
		this.filter = new Filter();
		this.inputSource = new InputSource("Events.xml");
	}
	
	public Events setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}
	
	public String getMessage() {

		String message = null;
		
		try {
		
			XPathFactory xPathFactory = XPathFactory.newInstance();
			
			XPath xpath = xPathFactory.newXPath();
	        
			Node element = (Node) xpath.evaluate("//*[@id='"+ this.name() +"'][1]",inputSource,XPathConstants.NODE);
			
			message = element.getTextContent().trim();
			
			message = filter.filter(message); 
			
		} catch (XPathExpressionException e) {
			
			e.printStackTrace();
		
		}
		
		return message;
		
	}
	
}
