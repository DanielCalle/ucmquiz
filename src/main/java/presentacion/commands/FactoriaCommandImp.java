
package presentacion.commands;

import java.lang.reflect.Constructor;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import presentacion.Command;
import presentacion.Events;

public class FactoriaCommandImp extends FactoriaCommand {

	public Command generateCommand(Events event) {
		
		Command command = null;
		
		try {
			
			InputSource inputSource = new InputSource("Commands.xml");
			
			XPath xpath = XPathFactory.newInstance().newXPath();
            
            Node element = (Node) xpath.evaluate("//*[@id='"+ event.name() +"'][1]", inputSource, XPathConstants.NODE);
			
			if(element != null) {
				
				String className = element.getTextContent();
				
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