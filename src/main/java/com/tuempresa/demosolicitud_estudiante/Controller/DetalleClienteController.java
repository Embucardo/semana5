package com.tuempresa.demosolicitud_estudiante.Controller;

import com.tuempresa.demosolicitud_estudiante.Model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DetalleClienteController {

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblTipoCliente;

    @FXML
    private Label lblCiudad;

    @FXML
    private Label lblFechaNacimiento;

    @FXML
    private Label lblTipoSolicitud;

    @FXML
    private Label lblServicios;

    @FXML
    private ImageView imgFotografia;

    private final DateTimeFormatter formatoFecha =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void cargarCliente(Cliente cliente) {

        lblNombre.setText(
                cliente.getNombreCompleto()
        );

        lblTipoCliente.setText(
                cliente.getTipoCliente()
        );

        lblCiudad.setText(
                cliente.getCiudad()
        );

        lblFechaNacimiento.setText(
                cliente.getFechaNacimiento()
                        .format(formatoFecha)
        );

        lblTipoSolicitud.setText(
                cliente.getTipoSolicitud()
        );

        lblServicios.setText(
                cliente.getServicios()
        );

        if (cliente.getRutaFotografia() != null
                && !cliente.getRutaFotografia().isEmpty()) {

            Image imagen = new Image(
                    cliente.getRutaFotografia()
            );

            imgFotografia.setImage(imagen);
        }
    }

    @FXML
    private void volver(ActionEvent event)
            throws IOException {

        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource(
                        "/com/tuempresa/demosolicitud_estudiante/ConsultaClientes.fxml"
                ))
        );

        Stage stage =
                (Stage)
                        ((Node) event.getSource())
                                .getScene()
                                .getWindow();

        stage.getScene().setRoot(root);

        stage.setTitle(
                "Consulta de Clientes"
        );
    }
}