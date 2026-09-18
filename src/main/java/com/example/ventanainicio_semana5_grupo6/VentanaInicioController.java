package com.example.ventanainicio_semana5_grupo6;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class VentanaInicioController {

    @FXML
    private TextField usuario;

    @FXML
    private TextField contrasena;

    @FXML
    private Label mensaje;

    @FXML
    private Button close;

    @FXML
    private void verificar() throws IOException {

        String us =
                usuario.getText();

        String password =
                contrasena.getText();

        if (us.isEmpty()
                || password.isEmpty()) {

            mensaje.setText(
                    "El usuario o la contraseña está vacío"
            );

            usuario.clear();
            contrasena.clear();

            return;
        }

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/tuempresa/demosolicitud_estudiante/VentanaPrincipal.fxml"
                )
        );

        Stage stage =
                (Stage)
                        usuario
                                .getScene()
                                .getWindow();

        stage.getScene().setRoot(root);

        stage.setTitle(
                "Sistema de Gestión de Solicitudes"
        );
    }

    @FXML
    private void cerrar() {

        Alert alerta =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        alerta.setTitle(
                "Confirmación de salida"
        );

        alerta.setHeaderText(
                "¿Cerrar?"
        );

        alerta.setContentText(
                "¿Desea cerrar la ventana?"
        );

        Optional<ButtonType> resultado =
                alerta.showAndWait();

        if (resultado.isPresent()
                && resultado.get()
                == ButtonType.OK) {

            Stage stage =
                    (Stage)
                            close
                                    .getScene()
                                    .getWindow();

            stage.close();
        }
    }
}