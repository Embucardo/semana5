package com.tuempresa.demosolicitud_estudiante.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ConsultaClientesController {

    @FXML
    private TableView tablaClientes;


    @FXML
    private void seleccionarCliente(MouseEvent event) {

        if (event.getClickCount() == 2) {
            System.out.println("Doble clic sobre un cliente");
        }
    }


    @FXML
    private void volverMenu(ActionEvent event) {

        System.out.println("Volver al menú principal");

    }
}