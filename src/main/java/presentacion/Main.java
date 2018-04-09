package presentacion;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	


	@Override
	public void start(Stage stage) throws IOException {
	
  
		try {
			
			FXMLLoader loader = new FXMLLoader();
		
			loader.setController(Class.forName("presentacion.guiControlers.CrearAsignaturaController").getMethod("getInstance").invoke(null));
			loader.setLocation(getClass().getResource("/presentacion/CrearAsignatura.fxml"));
			   
			Parent root = loader.load();
			
			stage.setTitle("Crear Asignatura");
			      
			stage.setResizable(false);
			      
			stage.setScene(new Scene(root));
			      
			stage.show();

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}		

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
