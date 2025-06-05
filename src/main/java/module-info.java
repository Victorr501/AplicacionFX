module com.aplicacion.aplicacionfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.aplicacion.aplicacionfx to javafx.fxml;
    exports com.aplicacion.aplicacionfx;
}