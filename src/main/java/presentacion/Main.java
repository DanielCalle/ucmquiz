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
		
		/*
		Parent root = FXMLLoader.load(getClass().getResource("/presentacion/CrearAsignatura.fxml"));
		stage.setTitle("UCM QUIZ");
		stage.setScene(new Scene(root));
		stage.show();
		*/
		
		//stage.setResizable(false);
		
		/*stage.setScene(new Scene(root2));
		stage.show();*/
		
		   
		try {
			
			FXMLLoader loader = new FXMLLoader();
		
			loader.setController(Class.forName("presentacion.guiControlers.CrearAsignaturaController").getMethod("getInstance").invoke(null));
		
			loader.setLocation(getClass().getResource("/presentacion/CrearAsignatura.fxml"));
			   
			Parent root = loader.load();
			   
			Stage stage1 = new Stage();
			   
			stage1.setTitle("Crear Asignatura");
			      
			stage1.setResizable(false);
			      
			stage1.setScene(new Scene(root));
			      
			stage1.show();

			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
