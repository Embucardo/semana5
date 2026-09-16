module com.example.caso5 {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.caso5 to javafx.fxml;
    exports com.example.caso5;
}