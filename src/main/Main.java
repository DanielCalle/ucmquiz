package main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import negocio.usuario.SAUsuario;
import negocio.usuario.SAUsuarioImp;
import negocio.usuario.Usuario;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       /*
    	Parent root = FXMLLoader.load(getClass().getResource("/presentacion/GUI/login.fxml"));
    	
    	stage.setTitle("Login");
    	
    	stage.setResizable(false);
    	
    	stage.setScene(new Scene(root));
    	
    	stage.show();*/
    	SAUsuario sa = new SAUsuarioImp();
    	Usuario usuario = new Usuario("zhong.9745@gmail.com", "Hongdd", "Zihao");
    	//sa.create(usuario);
    	sa.delete(3);
    }
	
	public static void main(String[] args) {
	       launch(args);
	}

};