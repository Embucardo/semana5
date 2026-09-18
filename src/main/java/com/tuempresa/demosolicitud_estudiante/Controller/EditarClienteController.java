package com.tuempresa.demosolicitud_estudiante.Controller;

import com.tuempresa.demosolicitud_estudiante.Model.Cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EditarClienteController {

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> cbTipoCliente;

    @FXML
    private ComboBox<String> cbCiudad;

    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    private ComboBox<String> cbTipoSolicitud;

    @FXML
    private CheckBox chkInternet;

    @FXML
    private CheckBox chkTelefonia;

    @FXML
    private CheckBox chkCable;

    private Cliente cliente;

    private final DateTimeFormatter formatoFecha =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    public void initialize() {

        cbTipoCliente.getItems().addAll(
                "Particular",
                "Empresarial",
                "Corporativo"
        );

        cbCiudad.getItems().addAll(
                "Boaco",
                "Carazo",
                "Chinandega",
                "Chontales",
                "Estelí",
                "Granada",
                "Jinotega",
                "León",
                "Madriz",
                "Managua",
                "Masaya",
                "Matagalpa",
                "Nueva Segovia",
                "Río San Juan",
                "Rivas",
                "Costa Caribe Norte",
                "Costa Caribe Sur"
        );

        cbTipoSolicitud.getItems().addAll(
                "Consulta",
                "Contratación",
                "Reclamo"
        );

        dpFechaNacimiento.setConverter(
                new StringConverter<>() {

                    @Override
                    public String toString(LocalDate fecha) {

                        if (fecha == null) {
                            return "";
                        }

                        return formatoFecha.format(fecha);
                    }

                    @Override
                    public LocalDate fromString(String texto) {

                        if (texto == null
                                || texto.trim().isEmpty()) {

                            return null;
                        }

                        try {

                            return LocalDate.parse(
                                    texto.trim(),
                                    formatoFecha
                            );

                        } catch (DateTimeParseException e) {

                            return null;
                        }
                    }
                }
        );

        dpFechaNacimiento.setPromptText(
                "dd/MM/yyyy"
        );
    }

    public void cargarCliente(
            Cliente cliente
    ) {

        this.cliente = cliente;

        txtNombre.setText(
                cliente.getNombreCompleto()
        );

        cbTipoCliente.setValue(
                cliente.getTipoCliente()
        );

        cbCiudad.setValue(
                cliente.getCiudad()
        );

        dpFechaNacimiento.setValue(
                cliente.getFechaNacimiento()
        );

        cbTipoSolicitud.setValue(
                cliente.getTipoSolicitud()
        );

        String servicios =
                cliente.getServicios();

        chkInternet.setSelected(
                servicios.contains("Internet")
        );

        chkTelefonia.setSelected(
                servicios.contains("Telefonía")
        );

        chkCable.setSelected(
                servicios.contains("Cable")
        );
    }

    @FXML
    private void guardarCambios(
            ActionEvent event
    ) throws IOException {

        String nombre =
                txtNombre.getText().trim();

        if (nombre.isEmpty()) {

            mostrarError(
                    "Ingrese el nombre del cliente."
            );

            return;
        }

        if (!nombre.matches(
                "[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+"
        )) {

            mostrarError(
                    "El nombre solo puede contener letras."
            );

            return;
        }

        if (nombre.length() < 3) {

            mostrarError(
                    "Ingrese un nombre válido."
            );

            return;
        }

        if (cbTipoCliente.getValue() == null) {

            mostrarError(
                    "Seleccione el tipo de cliente."
            );

            return;
        }

        if (cbCiudad.getValue() == null) {

            mostrarError(
                    "Seleccione el departamento."
            );

            return;
        }

        String textoFecha =
                dpFechaNacimiento
                        .getEditor()
                        .getText()
                        .trim();

        if (textoFecha.isEmpty()) {

            mostrarError(
                    "Ingrese la fecha de nacimiento."
            );

            return;
        }

        LocalDate fechaNacimiento;

        try {

            fechaNacimiento =
                    LocalDate.parse(
                            textoFecha,
                            formatoFecha
                    );

        } catch (DateTimeParseException e) {

            mostrarError(
                    "La fecha de nacimiento no es válida.\n"
                            + "Utilice el formato dd/MM/yyyy."
            );

            return;
        }

        if (fechaNacimiento.isAfter(
                LocalDate.now()
        )) {

            mostrarError(
                    "La fecha de nacimiento no puede ser futura."
            );

            return;
        }

        if (cbTipoSolicitud.getValue() == null) {

            mostrarError(
                    "Seleccione el tipo de solicitud."
            );

            return;
        }

        if (!chkInternet.isSelected()
                && !chkTelefonia.isSelected()
                && !chkCable.isSelected()) {

            mostrarError(
                    "Seleccione al menos un servicio."
            );

            return;
        }

        String servicios = "";

        if (chkInternet.isSelected()) {
            servicios += "Internet ";
        }

        if (chkTelefonia.isSelected()) {
            servicios += "Telefonía ";
        }

        if (chkCable.isSelected()) {
            servicios += "Cable ";
        }

        servicios = servicios.trim();

        cliente.setNombreCompleto(nombre);

        cliente.setTipoCliente(
                cbTipoCliente.getValue()
        );

        cliente.setCiudad(
                cbCiudad.getValue()
        );

        cliente.setFechaNacimiento(
                fechaNacimiento
        );

        cliente.setTipoSolicitud(
                cbTipoSolicitud.getValue()
        );

        cliente.setServicios(servicios);

        Alert alerta =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alerta.setTitle("Editar cliente");

        alerta.setHeaderText(
                "Cliente actualizado correctamente"
        );

        alerta.setContentText(
                "Los cambios fueron guardados."
        );

        alerta.showAndWait();

        volverConsulta(event);
    }

    @FXML
    private void cancelar(
            ActionEvent event
    ) throws IOException {

        volverConsulta(event);
    }

    private void volverConsulta(
            ActionEvent event
    ) throws IOException {

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/tuempresa/demosolicitud_estudiante/ConsultaClientes.fxml"
                )
        );

        Stage stage =
                (Stage)
                        ((Node) event.getSource())
                                .getScene()
                                .getWindow();

        stage.getScene().setRoot(root);
        stage.setTitle("Consulta de Clientes");
    }

    private void mostrarError(
            String mensaje
    ) {

        Alert alerta =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alerta.setTitle("Error");

        alerta.setHeaderText(
                "Datos inválidos"
        );

        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}