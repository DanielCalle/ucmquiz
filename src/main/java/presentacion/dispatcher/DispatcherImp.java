
package presentacion.dispatcher;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
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
			
			XPath xpath = XPathFactory.newInstance().newXPath();
            
			InputSource inputSource = new InputSource("Dispatcher.xml");
            
			String regularExpression = "//*[@id='"+ contexto.getEvent().name() +"'][1]";
            
			Node element = (Node) xpath.evaluate(regularExpression,inputSource,XPathConstants.NODE);
			
			String filePath = element.getChildNodes().item(3).getTextContent();
			
			String windowTitle = element.getChildNodes().item(1).getTextContent();
			
			FXMLLoader loader = new FXMLLoader();
			
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
		
		GUI gui = (GUI) contexto.getDato();
		
		gui.update(contexto);
		
	}
	
}