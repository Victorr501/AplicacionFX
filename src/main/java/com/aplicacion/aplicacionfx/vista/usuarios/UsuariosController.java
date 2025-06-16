package com.aplicacion.aplicacionfx.vista.usuarios;

import com.aplicacion.aplicacionfx.modelo.UsuarioUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {

    // Elementos de la UI inyectados desde UsuariosView.fxml
    @FXML private TableView<UsuarioUI> tablaUsuarios;
    @FXML private TableColumn<UsuarioUI, String> columnaId;
    @FXML private TableColumn<UsuarioUI, String> columnaNombre;
    @FXML private TableColumn<UsuarioUI, String> columnaEmail;
    @FXML private TableColumn<UsuarioUI, String> columnaRol;
    @FXML private TableColumn<UsuarioUI, Boolean> columnaActivo;

    @FXML private Label mensajeEstado;


    // --- Fuente de datos para la tabla ---
    // Esta ObservableList contendrá los objetos UsuarioUI que se mostrarán en la tabla.
    // Cuando modificas esta lista, la tabla se actualiza automáticamente.
    private ObservableList<UsuarioUI> listaUsuarios = FXCollections.observableArrayList();


    /**
     * Este método se llama automáticamente por JavaFX después de que todos los
     * elementos @FXML han sido inyectados desde el FXML. Es el lugar ideal para
     * la inicialización del controlador.
     */
    @Override
    public  void initialize(URL url, ResourceBundle rb){
        // 1. Configurar cómo cada columna de la TableView obtiene su valor
        //    "id" debe coincidir con el nombre de la propiedad (o el getter sin "get")
        //    en tu clase UsuarioUI (ej. getId() o idProperty()).
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnaRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        columnaActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));

        //Es de prueba y se modificara en un futuro
        cargarUsuariosDePrueba();


        // 2. Vincular la ObservableList a la TableView
        //    Esto es lo que "conecta" los datos con la interfaz visual.
        tablaUsuarios.setItems(listaUsuarios);
    }

    private void cargarUsuariosDePrueba() {
        listaUsuarios.clear();

        // Crea algunos objetos UsuarioUI de ejemplo
        UsuarioUI user1 = new UsuarioUI("u001", "Ana García", "ana.garcia@example.com", "anagar", "USER", true);
        UsuarioUI user2 = new UsuarioUI("u002", "Pedro López", "pedro.lopez@example.com", "plopez", "ADMIN", true);
        UsuarioUI user3 = new UsuarioUI("u003", "Marta Ruiz", "marta.ruiz@example.com", "mruiz", "USER", false);
        UsuarioUI user4 = new UsuarioUI("u004", "Luis Fernández", "luis.f@example.com", "lfernandez", "EDITOR", true);

        listaUsuarios.addAll(user1,user2,user3,user4);

        mensajeEstado.setText("Usuarios de prueba cargados");
    }

    @FXML
    private void handleCargarUsuarios(ActionEvent event){}

    @FXML
    private void handleAnadirUsuario(ActionEvent event){}

    @FXML
    private void handleEditarUsuario(ActionEvent event){}

    @FXML
    private void handleEliminarUsuario(ActionEvent event){

    }
}
