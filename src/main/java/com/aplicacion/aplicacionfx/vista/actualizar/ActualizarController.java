package com.aplicacion.aplicacionfx.vista.actualizar;

import com.aplicacion.aplicacionfx.servicio.UsuarioServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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


    private UsuarioServicio usuarioServicio;
    private String id;
    private Stage dialogStage;

    public ActualizarController(){
        this.usuarioServicio = new UsuarioServicio();
    }

    public void setUserId(String userId){
        this.id = userId;
        cargarDatosUsuario(id);
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    private void cargarDatosUsuario(String id){

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
