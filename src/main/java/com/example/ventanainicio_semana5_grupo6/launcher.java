package com.example.ventanainicio_semana5_grupo6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ventanaInicio.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Sistema de Gestión de Torneo Deportivo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
