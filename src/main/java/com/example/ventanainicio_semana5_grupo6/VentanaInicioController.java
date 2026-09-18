package com.example.ventanainicio_semana5_grupo6;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class VentanaInicioController {
    @FXML private TextField usuario;
    @FXML private TextField contrasena;
    @FXML private Label mensaje;
    @FXML private Button close;

    @FXML
    private void verificar(){
        String us = usuario.getText();
        String password = contrasena.getText();

        if(us.isEmpty() || password.isEmpty()){
            mensaje.setText("El usuario o la contraseña esta vacio");
            usuario.clear();
            contrasena.clear();
        }
        else{
            usuario.clear();
            contrasena.clear();
            mensaje.setText("Bienvenido");
        }
    }

    @FXML
    private void cerrar() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación de salida");
        alerta.setHeaderText("¿Cerrar?");
        alerta.setContentText("¿Desea cerrar la ventana?");

        Optional<ButtonType> resultado = alerta.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();
        }
    }

}
