
package presentacion.command;

import java.lang.reflect.Constructor;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import presentacion.Command;

public class FactoriaCommandImp extends FactoriaCommand {

	public Command generateCommand(int event) {
		
		Command command = null;
		
		try {
			
			XPathFactory xPathFactory = XPathFactory.newInstance();
			
			XPath xpath = xPathFactory.newXPath();
            
			InputSource inputSource = new InputSource("Commands.xml");
            
            String regularExpression = "//*[@id='"+ event +"'][1]";
            
            Node element = (Node) xpath.evaluate(regularExpression,inputSource,XPathConstants.NODE);
			
			if(element != null) {
				
				String className = element.getTextContent().trim();
				
				Class<?> commandClass = Class.forName(className);
				
				Constructor<?> constructor = commandClass.getConstructor();
				
				command = (Command) constructor.newInstance();
			}
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		return command;
	
	}
	
}