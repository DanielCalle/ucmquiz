
package presentacion.dispatcher;

import java.lang.reflect.Method;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import presentacion.Contexto;
import presentacion.GUI;

public class DispatcherImp extends Dispatcher {

	@Override
	public void generateVista(Contexto contexto) {
		
		try {
			
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xpath = xPathFactory.newXPath();
            InputSource inputSource = new InputSource("Dispatcher.xml");
            String regularExpression = "//*[@id='"+ contexto.getEvent() +"'][1]";
            Node element = (Node) xpath.evaluate(regularExpression,inputSource,XPathConstants.NODE);
			
			String className = element.getTextContent().trim();
			Class<?> vistaClass = Class.forName(className);
			Method method = vistaClass.getMethod("getInstance");
			GUI vista = (GUI) method.invoke(null);
			
			vista.update(contexto);
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
	}
	
}