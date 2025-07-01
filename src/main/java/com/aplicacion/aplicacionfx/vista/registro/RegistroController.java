package com.aplicacion.aplicacionfx.vista.registro;

import com.aplicacion.aplicacionfx.modelo.UsuarioUI;
import com.aplicacion.aplicacionfx.servicio.UsuarioServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistroController implements Initializable {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmarPasswordField;
    @FXML
    private ComboBox<String> rolComboBox;
    @FXML
    private CheckBox activoCheckBox;
    @FXML
    private Label mensajeErrorLabel;

    private UsuarioServicio usuarioServicio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioServicio = new UsuarioServicio();

        rolComboBox.getItems().addAll("Administrador", "Usuario", "Invitado");
        rolComboBox.getSelectionModel().selectFirst();
        activoCheckBox.setSelected(true);
    }

    @FXML
    private void handleRegistrarUsuario(ActionEvent event){
        mensajeErrorLabel.setText("");

        String nombre = nombreField.getText().trim(); // .trim() para eliminar espacios en blacon
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmarPaswword = confirmarPasswordField.getText().trim();
        String rol = rolComboBox.getSelectionModel().getSelectedItem();
        boolean activo = activoCheckBox.isSelected();

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || confirmarPaswword.isEmpty() || rol == null){
            mensajeErrorLabel.setText("Todos los campos son obligatorios");
            return;
        }

        if (!password.equals(confirmarPaswword)){
            mensajeErrorLabel.setText("La contraseña no coinciden.");
            return;
        }

        if (password.length() < 6){
            mensajeErrorLabel.setText("La contraseña es muy corta");
            return;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")){
            mensajeErrorLabel.setText("El formato del email no es valido");
            return;
        }

        UsuarioUI nuevoUsuario =  new UsuarioUI(null, nombre, email, password,rol, activo);


        try {
            UsuarioUI usuarioRegidtrado = usuarioServicio.registrarUsuario(nuevoUsuario);
            cleanForm();
            mensajeErrorLabel.setText("Usuario registrado con éxito");

        } catch (Exception e){
            mensajeErrorLabel.setText("Error al registrar usuario");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancelarRegistro(ActionEvent event){
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void cleanForm(){
        nombreField.clear();
        emailField.clear();
        passwordField.clear();
        confirmarPasswordField.clear();
        rolComboBox.getSelectionModel().selectFirst();
        activoCheckBox.setSelected(true);
        mensajeErrorLabel.setText("");
    }
}
