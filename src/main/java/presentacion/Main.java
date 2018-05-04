package presentacion;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import presentacion.controlador.Controlador;


public class Main extends Application {
	


	@Override
	public void start(Stage stage) throws IOException {

		/*try {
			
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
		}	*/

		/*
		Parent root = FXMLLoader.load(getClass().getResource("/presentacion/SeleccionUsuario.fxml"));
		stage.setTitle("UCM QUIZ");
		
		//stage.setResizable(false);
		stage.setScene(new Scene(root));
		stage.show();
		stage.setScene(new Scene(root2));
		stage.show();
		*/
		Contexto contexto = new Contexto(Events.SHOW_PREGUNTA_RESPONDER,null);
		Controlador.getInstance().accion(contexto);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}