package presentacion.guiControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import negocio.FactoriaNegocio;
import negocio.FactoriaNegocioImp;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;
import presentacion.GUI;
import presentacion.controlador.Controlador;

public abstract class BorrarAsignaturaController implements GUI {
	
	private static BorrarAsignaturaController instance;

	public synchronized static BorrarAsignaturaController getInstance() {
		if(instance == null) instance = new BorrarAsignaturaControllerImp();
		return instance;
	}

}
