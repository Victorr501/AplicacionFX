package com.aplicacion.aplicacionfx.vista.usuarios;

import com.aplicacion.aplicacionfx.modelo.UsuarioUI;
import com.aplicacion.aplicacionfx.servicio.UsuarioServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    private UsuarioServicio usuarioServicio;

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
        usuarioServicio = new UsuarioServicio();
        //Es de prueba y se modificara en un futuro
        try {
            cargarUsuarios();
        } catch (IOException e) {
            mensajeEstado.setText("Error al cargar usuarios");
        }


        // 2. Vincular la ObservableList a la TableView
        //    Esto es lo que "conecta" los datos con la interfaz visual.
        tablaUsuarios.setItems(listaUsuarios);
    }

    private void cargarUsuarios() throws IOException {

        listaUsuarios.clear();
        listaUsuarios = usuarioServicio.obtenerTodosLosUsuarios();

        mensajeEstado.setText("Usuarios de prueba cargados");
    }

    @FXML
    private void handleCargarUsuarios(ActionEvent event){
        try {
            cargarUsuarios();
        } catch (IOException e) {
            mensajeEstado.setText("Error al cargar usuarios");
        }
        // aqqui es donde se carga los datos en la base de datos
        tablaUsuarios.setItems(listaUsuarios);
    }

    @FXML
    private void handleAnadirUsuario(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/aplicacion/aplicacionfx/vista/registro/RegistroView.fxml"));
            Parent root = fxmlLoader.load();
            // Opcional: Si necesitas pasar datos al RegistroController, hazlo AQUÍ
            // RegistroController registroController = fxmlLoader.getController();
            // registroController.setSomeData(data); // Un método que crees en RegistroController

            // 2. Crear un nuevo Stage (ventana)
            Stage registroStage = new Stage();
            registroStage.setTitle("Registrar Nuevo Usuario");
            registroStage.setScene(new Scene(root));

            // 3. Configurar la ventana como modal (opcional pero común para formularios)
            //    Esto bloquea la interacción con la ventana principal hasta que se cierra esta.
            registroStage.initModality(Modality.APPLICATION_MODAL); // O Modality.WINDOW_MODAL si quieres que bloquee solo a su ventana padre

            // 4. Establecer el propietario del Stage (la ventana actual)
            //    Esto es útil si usas Modality.WINDOW_MODAL, para que la nueva ventana
            //    se superponga a la padre y se minimice/restaure con ella.
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            registroStage.initOwner(currentStage);


            // 5. Mostrar la nueva ventana y esperar a que se cierre (si es modal)
            registroStage.showAndWait();

            // Opcional: Cuando la ventana de registro se cierra, puedes refrescar la tabla de usuarios
            // Por ejemplo, si el registro fue exitoso
            // handleCargarUsuarios(null); // Recarga los usuarios para ver el nuevo
            // O puedes pasar una referencia para que el registro controller notifique a este.

        } catch (Exception e){
            e.printStackTrace();
            mensajeEstado.setText("Error al abrir formulario de registro");
        }
    }

    @FXML
    private void handleEditarUsuario(ActionEvent event){

    }

    @FXML
    private void handleEliminarUsuario(ActionEvent event){
        UsuarioUI usuarioSelecionado = tablaUsuarios.getSelectionModel().getSelectedItem();

        if (usuarioSelecionado == null){
            mensajeEstado.setText("Por favor, selecciona un usuario para eliminar.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cofirmar Eliminación");
        alert.setHeaderText("¿Estas seguro que quieres eliminar a " + usuarioSelecionado.getNombre() + "?");
        alert.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            try {
                usuarioServicio.eliminarUsuario(usuarioSelecionado.getId());


                mensajeEstado.setText("Usuario " + usuarioSelecionado.getNombre() + " eliminado exitosamente.");
                cargarUsuarios();
                tablaUsuarios.setItems(listaUsuarios);
            } catch (Exception e){
                mensajeEstado.setText("Error al elimiar usuario: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            mensajeEstado.setText("Eliminación de usuario cacelada.");
        }
    }
}
