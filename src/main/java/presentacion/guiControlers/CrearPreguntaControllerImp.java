package presentacion.guiControlers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import negocio.asignatura.Asignatura;
import negocio.pregunta.Pregunta;
import negocio.respuesta.Respuesta;
import presentacion.Contexto;
import presentacion.Events;
import presentacion.controlador.Controlador;

public class CrearPreguntaControllerImp extends CrearPreguntaController implements Initializable {

    @FXML
    private StackPane stackpane;

    @FXML
    private AnchorPane root;

    @FXML
    public TextArea textArea;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnCrear;

    private List<Asignatura> list;
    private List<Respuesta> respuestas;

    @FXML
    private JFXComboBox < String > cobobox;

    @FXML
    private JFXButton CrearRespuestas;

    @FXML
    private JFXButton BorrarRespuestas;

    @FXML
    private JFXTreeTableView <Respuesta> treeView;

    @FXML
    void btnBorrar(ActionEvent event) {

        Stage stage = (Stage) stackpane.getScene().getWindow();

        stage.close();
    }

    @FXML
    void btnCrearListener(ActionEvent event) {

        if (textArea.getLength() == 0) {

            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Accion incorrecta"));
            content.setBody(new Text("No se pueden crear una pregunta en blanco"));
            JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

            JFXButton button = new JFXButton("Ok");
            button.setOnAction(new EventHandler < ActionEvent > () {

                @Override
                public void handle(ActionEvent arg0) {
                    dialog.close();

                }

            });
            content.setActions(button);
            dialog.show();

        } else {

            Pregunta pregunta = new Pregunta(textArea.getText(), true);
            pregunta.setRespuestas(respuestas);

            Contexto contexto = new Contexto(Events.COMMAND_PREGUNTA_CREATE, pregunta);

            Controlador.getInstance().accion(contexto);

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	JFXTreeTableColumn<Respuesta, String> pregunta = new JFXTreeTableColumn<>("Pregunta");
		pregunta.setPrefWidth(150);
		pregunta.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<Respuesta, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Respuesta, String> param) {
						return param.getValue().getValue().getTituloStringProperty();
					}
				});
		JFXTreeTableColumn<Respuesta, String> deptEstado = new JFXTreeTableColumn<>("Estado");
		deptEstado.setPrefWidth(150);
		deptEstado.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<Respuesta, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Respuesta, String> param) {
						return param.getValue().getValue().getCorrectaStringProperty();
					}
				});

		ObservableList<Respuesta> users = FXCollections.observableArrayList();
		treeView.getColumns().setAll(pregunta,deptEstado);
		treeView.setShowRoot(false);
		treeView.getSelectionModel().getSelectedItem();
    	
    	
        //	state = false;
    	respuestas = new ArrayList<Respuesta>();
    	
        Contexto contexto = new Contexto(Events.COMMAND_ASIGNATURA_READ_ALL_PREGUNTA_CREATE, null);
        Controlador.getInstance().accion(contexto);

        List < String > value = list.stream().map(a -> a.getTitulo()).collect(Collectors.toList());

        cobobox.setItems(FXCollections.observableArrayList(value));
        cobobox.valueProperty().addListener(new ChangeListener < String > () {

            @Override
            public void changed(ObservableValue<? extends String > observable, String oldValue, String newValue) {
                Asignatura asig = list.stream().filter(a -> a.getTitulo() == newValue).findFirst().get();
                //	state = asig.isActivo();
                //	btnonoff.setSelected(state);
            }

        });


    }

    @Override
    public void update(Contexto contexto) {
        switch (contexto.getEvent()) {
            case ASIGNATURA_READ_ALL_PREGUNTA_CREATE_OK:
                list = (List<Asignatura>) contexto.getDato();
                break;

            case CRUD_CREATE_PREGUNTA_OK:

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Pregunta Creada"));
                content.setBody(new Text(contexto.getEvent().getMessage()));
                JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

                JFXButton button = new JFXButton("Ok");
                button.setOnAction(new EventHandler < ActionEvent > () {

                    @Override
                    public void handle(ActionEvent arg0) {
                        dialog.close();

                    }

                });
                content.setActions(button);
                dialog.show();

                break;

            case CRUD_CREATE_PREGUNTA_KO:

                content = new JFXDialogLayout();
                content.setHeading(new Text("Error"));
                content.setBody(new Text(contexto.getEvent().getMessage()));
                dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

                button = new JFXButton("Ok");
                button.setOnAction(new EventHandler < ActionEvent > () {

                    @Override
                    public void handle(ActionEvent arg0) {
                        dialog.close();

                    }

                });
                content.setActions(button);
                dialog.show();

                break;
                
            case CRUD_CREATE_RESPUESTA_OK:
            	
            	respuestas.add((Respuesta) contexto.getDato());
            	
            	ObservableList<Respuesta> users = FXCollections.observableArrayList();
    		
    			for (Respuesta a: respuestas) {
    				users.add(a);
    			}
    			final TreeItem<Respuesta> root = new RecursiveTreeItem<Respuesta>(users, RecursiveTreeObject::getChildren);
    			treeView.setRoot(root);
            	break;

            default:

                content = new JFXDialogLayout();
                content.setHeading(new Text("Error"));
                dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);

                button = new JFXButton("Ok");
                button.setOnAction(new EventHandler < ActionEvent > () {

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
    void coboboxAction(ActionEvent event) {

    }
    @FXML
    void BorrarRespuestasAction(ActionEvent event) {
    	Contexto contexto = new Contexto(Events.SHOW_RESPUESTA_DELETE, null);
        Controlador.getInstance().accion(contexto);
        try {
        	Thread.sleep(1000);
        	respuestas.add(new Respuesta("SI", true, true));
        	Controlador.getInstance().accion(new Contexto(Events.COMMAND_GET_RESPUESTAS, respuestas));
        }catch(Exception e) {}
        
    }

    @FXML
    void CrearRespuestasAction(ActionEvent event) {
    	Contexto contexto = new Contexto(Events.SHOW_RESPUESTA_CREATE, null);
        Controlador.getInstance().accion(contexto);
    }


}