module AplicacionFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires okhttp3;

    // Exporta el paquete de tu MainAplicacionFX para que JavaFX pueda lanzar tu app
    exports com.aplicacion.aplicacionfx;


    // Abre el paquete de tus controladores para que FXMLLoader pueda instanciarlos y inyectar @FXML
    opens com.aplicacion.aplicacionfx.vista.usuarios to javafx.fxml;
    opens com.aplicacion.aplicacionfx.vista.registro to javafx.fxml;
    opens com.aplicacion.aplicacionfx.vista.actualizar to javafx.fxml;
    opens com.aplicacion.aplicacionfx.cliente.dto to com.google.gson;

    // Abre el paquete de tus clases de modelo (UsuarioUI) para que JavaFX.base pueda acceder vía reflexión
    opens com.aplicacion.aplicacionfx.modelo to javafx.base;
}