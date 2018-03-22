
package presentacion.dispatcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentacion.Contexto;
import presentacion.GUI;

public class DispatcherImp extends Dispatcher {

	@Override
	public void generateView(Contexto contexto) {
		
		try {
			
			XPathFactory xPathFactory = XPathFactory.newInstance();
			
			XPath xpath = xPathFactory.newXPath();
            
			InputSource inputSource = new InputSource("Dispatcher.xml");
            
			String regularExpression = "//*[@id='"+ contexto.getEvent() +"'][1]";
            
			Node element = (Node) xpath.evaluate(regularExpression,inputSource,XPathConstants.NODE);
			
			String filePath = element.getChildNodes().item(3).getTextContent();
			
			String windowTitle = element.getChildNodes().item(1).getTextContent();
			
			String controlerClass = element.getChildNodes().item(5).getTextContent();
			
			FXMLLoader loader = new FXMLLoader();
			
			loader.setController(Class.forName(controlerClass).getMethod("getInstance").invoke(null));
			
			loader.setLocation(getClass().getResource(filePath));
			
			Parent root = loader.load();
			
			Stage stage = new Stage();
			
			stage.setTitle(windowTitle);
	    	
	    	stage.setResizable(false);
	    	
	    	stage.setScene(new Scene(root));
	    	
	    	stage.show();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
	}
	
	@Override
	public void updateView(Contexto contexto) {
		
		try {
			
			XPathFactory xPathFactory = XPathFactory.newInstance();
			
			XPath xpath = xPathFactory.newXPath();
	        
			InputSource inputSource = new InputSource("Dispatcher.xml");
	        
			String regularExpression = "//*[@id='"+ contexto.getEvent() +"'][1]";
	        
			Node element = (Node) xpath.evaluate(regularExpression,inputSource,XPathConstants.NODE);
			
			String controlerClassPath = element.getChildNodes().item(5).getTextContent();
			
			Class<?> controlerClass = Class.forName(controlerClassPath);
			
			Method method = controlerClass.getMethod("getInstance");
			
			GUI view = (GUI) method.invoke(null);
			
			view.update(contexto);
			
		} catch (XPathExpressionException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
			
			e.printStackTrace();
		
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		
		}
		
	}
	
}