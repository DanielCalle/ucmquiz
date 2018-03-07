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
import postgresqlTest.Student;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       
    	Parent root = FXMLLoader.load(getClass().getResource("/presentacion/GUI/login.fxml"));
    	
    	stage.setTitle("Login");
    	
    	stage.setResizable(false);
    	
    	stage.setScene(new Scene(root));
    	
    	stage.show();
    	/*
    	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ucmquizPostgres");
    	Usuario usuario = new Usuario("zhong.9745@gmail.com", "Zihao", "Hong");
    	//Usuario usuario = new Usuario();
		EntityManager entityManager = emfactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			usuario.setNombre("Zihao");
			usuario = entityManager.merge(usuario);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.print("hola");
		}
		*/
    	/*SAUsuario sa = new SAUsuarioImp();
    	Usuario usuario = new Usuario("zhong.9745@gmail.com", "Hong", "Zihao");
    	sa.create(usuario);*/
    }
	
	public static void main(String[] args) {
	       launch(args);
	}

};