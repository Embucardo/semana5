module com.example.ventanainicio_semana5_grupo6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ventanainicio_semana5_grupo6 to javafx.fxml;
    exports com.example.ventanainicio_semana5_grupo6;
}