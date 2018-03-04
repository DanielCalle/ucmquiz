package main;

import java.awt.Dimension;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("/presentacion/GUI/hello.fxml"));
    
        Scene scene = new Scene(root, 800, 575);
    
        stage.setTitle("Hello World!");
        stage.setScene(scene);
        stage.show();
    }
	
	public static void main(String[] args) {
	       launch(args);
	}

};