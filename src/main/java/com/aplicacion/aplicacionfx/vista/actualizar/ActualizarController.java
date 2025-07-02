package com.aplicacion.aplicacionfx.vista.actualizar;

import com.aplicacion.aplicacionfx.modelo.UsuarioUI;
import com.aplicacion.aplicacionfx.servicio.UsuarioServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ActualizarController {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<String> rolComboBox;
    @FXML
    private CheckBox activoCheckBox;

    @FXML private Label mensajeErrorLabel;

    private UsuarioServicio usuarioServicio;
    private String id;
    private Stage dialogStage;

    public ActualizarController(){
        this.usuarioServicio = new UsuarioServicio();
    }

    public void setUserId(String userId){
        this.id = userId;
        try {
            cargarDatosUsuario(id);
        } catch (Exception e) {
            System.err.println("Error al cargar el usuario " + e );
        }
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    private void cargarDatosUsuario(String id) throws Exception {
        rolComboBox.getItems().addAll("Administrador", "Usuario", "Invitado");

        UsuarioUI ui = usuarioServicio.obtenerUsuarioPorId(id);
        if (id == null){
            mensajeErrorLabel.setText("No se pudo encontrar el usuario");
            return;
        }
        if (!ui.getNombre().isEmpty()){
            nombreField.setText(ui.getNombre());
        }
        if (!ui.getEmail().isEmpty()){
            emailField.setText(ui.getEmail());
        }
        if (!ui.getRol().isEmpty()){
            rolComboBox.getSelectionModel().select(ui.getRol());
        } else {
            rolComboBox.getSelectionModel().clearSelection();
        }
        activoCheckBox.setSelected(ui.isActivo());

    }

    @FXML
    private void handleActualizar(ActionEvent event){

    }

    @FXML
    private void handleCancelarActualizacion(ActionEvent event){
        if (dialogStage != null){
            dialogStage.close();
        }
    }
}
