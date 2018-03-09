package main;

import javafx.application.Application;
import javafx.stage.Stage;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       
    	Controlador.getInstance().accion(new Contexto(Events.SHOW_LOGIN,null));
    	
    	/*
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
    	*/
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
    	/*
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
    	*/
    }
	
	public static void main(String[] args) {
	       launch(args);
	}

};