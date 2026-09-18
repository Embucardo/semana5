module com.tuempresa.demosolicitud_estudiante {

    requires javafx.controls;
    requires javafx.fxml;

    // Tu proyecto
    opens com.tuempresa.demosolicitud_estudiante to javafx.fxml;
    opens com.tuempresa.demosolicitud_estudiante.Controller to javafx.fxml;

    // Ventana 1 de Jorge
    opens com.example.ventanainicio_semana5_grupo6 to javafx.fxml;

    exports com.tuempresa.demosolicitud_estudiante;
    exports com.example.ventanainicio_semana5_grupo6;
}