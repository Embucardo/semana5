package com.tuempresa.demosolicitud_estudiante;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader =
                new FXMLLoader(
                        HelloApplication.class.getResource(
                                "/com/example/ventanainicio_semana5_grupo6/ventanaInicio.fxml"
                        )
                );

        Scene scene =
                new Scene(
                        fxmlLoader.load()
                );

        stage.setTitle(
                "Sistema de Gestión de Solicitudes"
        );

        stage.setScene(scene);

        stage.show();
    }
}