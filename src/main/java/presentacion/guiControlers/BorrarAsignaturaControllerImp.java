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
import presentacion.Contexto;
import presentacion.Events;
import presentacion.Filter;
import presentacion.controlador.Controlador;

public class BorrarAsignaturaControllerImp extends BorrarAsignaturaController {
	@FXML
	private StackPane root;
	
    @FXML
    private JFXButton btncancelar;

    @FXML
    private JFXTextField textfieldAsignatura;

    @FXML
    private JFXButton btnborrar;

    @FXML
    void btnBorrar(ActionEvent event) {
    	Integer id = Integer.parseInt(textfieldAsignatura.getText());
    	Contexto contexto = new Contexto(Events.COMMAND_ASIGNATURA_DELETE, id);
    	Controlador.getInstance().accion(contexto);
    }

    @FXML
    void btnCancelar(ActionEvent event) {
    	Stage stage = (Stage) root.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void textFieldAsignatura(ActionEvent event) {

    }

	@Override
	public void update(Contexto contexto) {
    	JFXDialogLayout content = new JFXDialogLayout();
    	Events e = contexto.getEvent();
    	Filter filter = new Filter();
		filter.addFilter("entity","asignatura");
		e.setFilter(filter);
    	String message = e.getMessage();
    	content.setHeading(new Text("Information"));
    	content.setBody(new Text(message));
    	JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
    	dialog.show();
		
	}
}
