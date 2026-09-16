module com.tuempresa.demosolicitud_estudiante {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.tuempresa.demosolicitud_estudiante to javafx.fxml;
    opens com.tuempresa.demosolicitud_estudiante.Controller to javafx.fxml;

    exports com.tuempresa.demosolicitud_estudiante;
}