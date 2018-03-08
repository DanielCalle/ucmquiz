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
import negocio.pregunta.Pregunta;
import negocio.pregunta.SAPregunta;
import negocio.pregunta.SAPreguntaImp;
import negocio.respuesta.Respuesta;
import negocio.respuesta.SARespuesta;
import negocio.respuesta.SARespuestaImp;
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
    	SAPregunta saPregunta = new SAPreguntaImp();
    	SARespuesta saRespuesta = new SARespuestaImp();
    	SAUsuario saUsuario = new SAUsuarioImp();
    	
    	Pregunta pregunta = new Pregunta("uno mas uno", true);
    	saPregunta.create(pregunta);
    	System.out.println("After create");
    	for (Pregunta u : saPregunta.readAll()) {
    		System.out.println(u.getId() + " " + u.getTitulo() + " " + u.getActivo());
    	}
    	pregunta = saPregunta.read(1);
    	pregunta.setTitulo("uno por uno");
    	saPregunta.update(pregunta);
    	System.out.println("After update");
    	for (Pregunta u : saPregunta.readAll()) {
    		System.out.println(u.getId() + " " + u.getTitulo() + " " + u.getActivo());
    	}
    	saPregunta.delete(1);
    	System.out.println("After delete");
    	for (Pregunta u : saPregunta.readAll()) {
    		System.out.println(u.getId() + " " + u.getTitulo() + " " + u.getActivo());
    	}
    	/*
    	Respuesta respuesta = new Respuesta(true, "dos");
    	saRespuesta.create(respuesta);
    	System.out.println("After create");
    	for (Respuesta u : saRespuesta.readAll()) {
    		System.out.println(u.getId() + " " + u.getTitulo() + " " + u.getActivo() + " " + u.getPregunta());
    	}
    	respuesta = saRespuesta.read(1);
    	respuesta.setTitulo("uno");
    	saRespuesta.update(respuesta);
    	System.out.println("After update");
    	for (Respuesta u : saRespuesta.readAll()) {
    		System.out.println(u.getId() + " " + u.getTitulo() + " " + u.getActivo() + " " + u.getPregunta());
    	}
    	saRespuesta.delete(1);
    	System.out.println("After delete");
    	for (Respuesta u : saRespuesta.readAll()) {
    		System.out.println(u.getId() + " " + u.getTitulo() + " " + u.getActivo() + " " + u.getPregunta());
    	}
    	*/
    	Usuario usuario = new Usuario("zhong.9745@gmail.com", "Zihao", "Zihao");
    	saUsuario.create(usuario);
    	System.out.println("After create");
    	for (Usuario u : saUsuario.readAll()) {
    		System.out.println(u.getId() + " " + u.getEmail() + " " + u.getNombre() + " " + u.getPassword() + " " + u.isActivo());
    	}
    	usuario = saUsuario.read(1);
    	usuario.setPassword("hong");
    	saUsuario.update(usuario);
    	System.out.println("After update");
    	for (Usuario u : saUsuario.readAll()) {
    		System.out.println(u.getId() + " " + u.getEmail() + " " + u.getNombre() + " " + u.getPassword() + " " + u.isActivo());
    	}
    	saUsuario.delete(1);
    	System.out.println("After delete");
    	for (Usuario u : saUsuario.readAll()) {
    		System.out.println(u.getId() + " " + u.getEmail() + " " + u.getNombre() + " " + u.getPassword() + " " + u.isActivo());
    	}
    }
	
	public static void main(String[] args) {
	       launch(args);
	}

};