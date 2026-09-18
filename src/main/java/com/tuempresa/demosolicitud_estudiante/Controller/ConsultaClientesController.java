package com.tuempresa.demosolicitud_estudiante.Controller;

import com.tuempresa.demosolicitud_estudiante.Model.Cliente;
import com.tuempresa.demosolicitud_estudiante.Model.DatosClientes;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ConsultaClientesController {

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colTipoCliente;

    @FXML
    private TableColumn<Cliente, String> colCiudad;

    @FXML
    private TableColumn<Cliente, LocalDate> colFechaNacimiento;

    @FXML
    private TableColumn<Cliente, String> colTipoSolicitud;

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getNombreCompleto()
                )
        );

        colTipoCliente.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getTipoCliente()
                )
        );

        colCiudad.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getCiudad()
                )
        );

        colFechaNacimiento.setCellValueFactory(
                dato -> new SimpleObjectProperty<>(
                        dato.getValue().getFechaNacimiento()
                )
        );

        colTipoSolicitud.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getTipoSolicitud()
                )
        );

        tablaClientes.setItems(
                DatosClientes.getClientes()
        );
    }

    @FXML
    private void seleccionarCliente(
            MouseEvent event
    ) throws IOException {

        if (event.getClickCount() == 2) {

            Cliente clienteSeleccionado =
                    tablaClientes
                            .getSelectionModel()
                            .getSelectedItem();

            if (clienteSeleccionado != null) {

                FXMLLoader loader =
                        new FXMLLoader(
                                getClass().getResource(
                                        "/com/tuempresa/demosolicitud_estudiante/DetalleCliente.fxml"
                                )
                        );

                Parent root = loader.load();

                DetalleClienteController controller =
                        loader.getController();

                controller.cargarCliente(
                        clienteSeleccionado
                );

                Stage stage =
                        (Stage)
                                tablaClientes
                                        .getScene()
                                        .getWindow();

                stage.getScene().setRoot(root);
                stage.setTitle("Detalle del Cliente");
            }
        }
    }

    @FXML
    private void editarCliente(
            ActionEvent event
    ) throws IOException {

        Cliente clienteSeleccionado =
                tablaClientes
                        .getSelectionModel()
                        .getSelectedItem();

        if (clienteSeleccionado == null) {

            Alert alerta =
                    new Alert(
                            Alert.AlertType.WARNING
                    );

            alerta.setTitle("Editar cliente");

            alerta.setHeaderText(
                    "No hay cliente seleccionado"
            );

            alerta.setContentText(
                    "Seleccione un cliente de la tabla."
            );

            alerta.showAndWait();

            return;
        }

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/com/tuempresa/demosolicitud_estudiante/EditarCliente.fxml"
                        )
                );

        Parent root = loader.load();

        EditarClienteController controller =
                loader.getController();

        controller.cargarCliente(
                clienteSeleccionado
        );

        Stage stage =
                (Stage)
                        ((Node) event.getSource())
                                .getScene()
                                .getWindow();

        stage.getScene().setRoot(root);
        stage.setTitle("Editar Cliente");
    }

    @FXML
    private void volverMenu(
            ActionEvent event
    ) throws IOException {

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/tuempresa/demosolicitud_estudiante/VentanaPrincipal.fxml"
                )
        );

        Stage stage =
                (Stage)
                        ((Node) event.getSource())
                                .getScene()
                                .getWindow();

        stage.getScene().setRoot(root);

        stage.setTitle(
                "Sistema de Gestión de Solicitudes"
        );
    }
}