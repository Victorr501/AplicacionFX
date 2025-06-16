package com.aplicacion.aplicacionfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAplicacionFX extends Application {

    @Override
    public void  start(Stage primaryStrage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainAplicacionFX.class.getResource("/com/aplicacion/aplicacionfx/vista/usuarios/UsuariosView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStrage.setTitle("GestorUsuarios");
        primaryStrage.setScene(scene);
        primaryStrage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
