package presentacion.guiControlers;

import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import negocio.respuesta.Respuesta;

public class BorrarRespuestaAdminControllerImp extends BorrarRespuestaAdminController{
	
	@FXML
    private StackPane root;
	
	@FXML
	private JFXButton btncancelar;

	@FXML
	private JFXButton btnborrar;
	
	@FXML
	private JFXTreeTableView<Respuesta> tablaRespuestas;

	@FXML
	private JFXComboBox<?> comboboxPregunta;

	@FXML
	void btnBorrar(ActionEvent event) {
		if (comboboxPregunta.getSelectionModel().getSelectedItem() != null) {
			if (tablaRespuestas.getSelectionModel().getSelectedItem() != null) {
				TreeItem<Respuesta> resp = tablaRespuestas.getSelectionModel().getSelectedItem();
				Respuesta r = resp.getValue();
				Contexto contexto = new Contexto(Events.COMMAND_RM_RESPUESTA, r.getTexto());
				Controlador.getInstance().accion(contexto);
			}
			else {
				JFXDialogLayout content = new JFXDialogLayout();
				content.setHeading(new Text("Accion incorrecta"));
				content.setBody(new Text("Debes seleccionar una respuesta"));
				JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

				JFXButton button = new JFXButton("Ok");
				button.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						dialog.close();

					}

				});
				content.setActions(button);
				dialog.show();
			}
		}
		else {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Accion incorrecta"));
			content.setBody(new Text("Debes seleccionar una pregunta"));
			JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

			JFXButton button = new JFXButton("Ok");
			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					dialog.close();

				}

			});
			content.setActions(button);
			dialog.show();
		}
	}
	@FXML
	void btnCancelar(ActionEvent event) {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	@Override
	public void update(Contexto contexto) {
		switch (contexto.getEvent()) {
		//Si se ha podido borrar muestro un mensaje con acierto
		case CRUD_DELETE_RESPUESTA_OK:

			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Respuesta borrada"));
			content.setBody(new Text(contexto.getEvent().getMessage()));
			JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

			JFXButton button = new JFXButton("Ok");
			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					dialog.close();

				}

			});
			content.setActions(button);
			dialog.show();

			break;
		//Si no se ha podido borrar muestro un mensaje de error con el con la info
		case CRUD_DELETE_ASIGNATURA_KO:
			
			content = new JFXDialogLayout();
			content.setHeading(new Text("Error al eliminar la respuesta"));
			content.setBody(new Text(contexto.getEvent().getMessage()));
			dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

			button = new JFXButton("Ok");
			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					dialog.close();

				}

			});
			content.setActions(button);
			dialog.show();

			break;

		default:
			
			content = new JFXDialogLayout();
			content.setHeading(new Text("Error"));
			dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

			button = new JFXButton("Ok");
			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					dialog.close();

				}

			});
			content.setActions(button);
			dialog.show();

		}
		
	}


}
