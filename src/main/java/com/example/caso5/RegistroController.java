package com.example.caso5;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;

public class RegistroController {

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtApellidos;

    @FXML
    private ComboBox<String> cbTipoCliente;

    @FXML
    private ComboBox<String> cbCiudad;

    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    private RadioButton rbConsulta;

    @FXML
    private RadioButton rbContratacion;

    @FXML
    private RadioButton rbReclamo;

    @FXML
    private CheckBox chkInternet;

    @FXML
    private CheckBox chkTelefonia;

    @FXML
    private CheckBox chkCable;

    @FXML
    private ImageView imgFotografia;

    private ToggleGroup grupoSolicitud = new ToggleGroup();

    @FXML
    public void initialize() {

        // Tipo de cliente
        cbTipoCliente.getItems().addAll(
                "Particular",
                "Empresarial",
                "Corporativo"
        );

        // Ciudades
        cbCiudad.getItems().addAll(
                "Managua",
                "León",
                "Masaya",
                "Granada",
                "Estelí"
        );

        // ToggleGroup
        rbConsulta.setToggleGroup(grupoSolicitud);
        rbContratacion.setToggleGroup(grupoSolicitud);
        rbReclamo.setToggleGroup(grupoSolicitud);
    }

    @FXML
    private void seleccionarFotografia() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar fotografía");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Imágenes",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"
                )
        );

        File archivo = fileChooser.showOpenDialog(
                imgFotografia.getScene().getWindow()
        );

        if (archivo != null) {

            Image imagen = new Image(
                    archivo.toURI().toString()
            );

            imgFotografia.setImage(imagen);
        }
    }

    @FXML
    private void guardarCliente() {

        if (txtNombres.getText().trim().isEmpty()) {
            mostrarError("Ingrese los nombres.");
            return;
        }

        if (txtApellidos.getText().trim().isEmpty()) {
            mostrarError("Ingrese los apellidos.");
            return;
        }

        if (cbTipoCliente.getValue() == null) {
            mostrarError("Seleccione el tipo de cliente.");
            return;
        }

        if (cbCiudad.getValue() == null) {
            mostrarError("Seleccione la ciudad.");
            return;
        }

        if (dpFechaNacimiento.getValue() == null) {
            mostrarError("Seleccione la fecha de nacimiento.");
            return;
        }

        if (dpFechaNacimiento.getValue().isAfter(LocalDate.now())) {
            mostrarError("La fecha de nacimiento no puede ser futura.");
            return;
        }

        if (grupoSolicitud.getSelectedToggle() == null) {
            mostrarError("Seleccione el tipo de solicitud.");
            return;
        }

        if (!chkInternet.isSelected()
                && !chkTelefonia.isSelected()
                && !chkCable.isSelected()) {

            mostrarError("Seleccione al menos un servicio.");
            return;
        }

        RadioButton solicitudSeleccionada =
                (RadioButton) grupoSolicitud.getSelectedToggle();

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

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle("Registro");
        alerta.setHeaderText("Cliente registrado correctamente");

        alerta.setContentText(
                "Nombres: " + txtNombres.getText()
                        + "\nApellidos: " + txtApellidos.getText()
                        + "\nTipo de cliente: " + cbTipoCliente.getValue()
                        + "\nCiudad: " + cbCiudad.getValue()
                        + "\nFecha de nacimiento: " + dpFechaNacimiento.getValue()
                        + "\nTipo de solicitud: " + solicitudSeleccionada.getText()
                        + "\nServicios: " + servicios
        );

        alerta.showAndWait();
    }

    @FXML
    private void limpiarFormulario() {

        txtNombres.clear();
        txtApellidos.clear();

        cbTipoCliente.setValue(null);
        cbCiudad.setValue(null);

        dpFechaNacimiento.setValue(null);

        grupoSolicitud.selectToggle(null);

        chkInternet.setSelected(false);
        chkTelefonia.setSelected(false);
        chkCable.setSelected(false);

        imgFotografia.setImage(null);
    }

    @FXML
    private void cancelar() {

        Stage stage = (Stage) imgFotografia
                .getScene()
                .getWindow();

        stage.close();
    }

    private void mostrarError(String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);

        alerta.setTitle("Error");
        alerta.setHeaderText("Datos incompletos");
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}