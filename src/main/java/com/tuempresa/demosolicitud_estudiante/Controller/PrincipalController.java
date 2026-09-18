package com.tuempresa.demosolicitud_estudiante.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class PrincipalController {

    @FXML
    private void abrirRegistro(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(
                getClass().getResource("/registro-view.fxml")
        );

        Stage stage = (Stage)
                ((Node) event.getSource())
                        .getScene()
                        .getWindow();

        stage.getScene().setRoot(root);
        stage.setTitle("Registro de Cliente");
    }

    @FXML
    private void abrirConsulta(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/tuempresa/demosolicitud_estudiante/ConsultaClientes.fxml"
                )
        );

        Stage stage = (Stage)
                ((Node) event.getSource())
                        .getScene()
                        .getWindow();

        stage.getScene().setRoot(root);
        stage.setTitle("Consulta de Clientes");
    }

    @FXML
    private void salir() {

        Alert alerta = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        alerta.setTitle("Salir");
        alerta.setHeaderText("¿Desea salir del sistema?");
        alerta.setContentText(
                "Presione Aceptar para cerrar la aplicación."
        );

        Optional<ButtonType> resultado =
                alerta.showAndWait();

        if (resultado.isPresent()
                && resultado.get() == ButtonType.OK) {

            Platform.exit();
        }
    }
}