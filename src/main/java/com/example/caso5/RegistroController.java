package com.example.caso5;

import com.tuempresa.demosolicitud_estudiante.Model.Cliente;
import com.tuempresa.demosolicitud_estudiante.Model.DatosClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

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

    private ToggleGroup grupoSolicitud;

    private String rutaFotografia;

    private final DateTimeFormatter formatoFecha =
            DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

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

        grupoSolicitud = new ToggleGroup();

        rbConsulta.setToggleGroup(grupoSolicitud);
        rbContratacion.setToggleGroup(grupoSolicitud);
        rbReclamo.setToggleGroup(grupoSolicitud);

        dpFechaNacimiento.setConverter(new StringConverter<>() {

            @Override
            public String toString(LocalDate fecha) {

                if (fecha == null) {
                    return "";
                }

                return formatoFecha.format(fecha);
            }

            @Override
            public LocalDate fromString(String texto) {

                if (texto == null || texto.trim().isEmpty()) {
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
        });

        dpFechaNacimiento.setPromptText("dd/MM/yyyy");
    }

    @FXML
    private void seleccionarFotografia() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle(
                "Seleccionar fotografía"
        );

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

            rutaFotografia =
                    archivo.toURI().toString();

            Image imagen =
                    new Image(rutaFotografia);

            imgFotografia.setImage(imagen);
        }
    }

    @FXML
    private void guardarCliente() {

        String nombres =
                txtNombres.getText().trim();

        String apellidos =
                txtApellidos.getText().trim();

        if (nombres.isEmpty()) {
            mostrarError(
                    "Ingrese los nombres."
            );
            return;
        }

        if (!nombres.matches(
                "[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+"
        )) {
            mostrarError(
                    "Los nombres solo pueden contener letras."
            );
            return;
        }

        if (nombres.length() < 2) {
            mostrarError(
                    "Ingrese un nombre válido."
            );
            return;
        }

        if (apellidos.isEmpty()) {
            mostrarError(
                    "Ingrese los apellidos."
            );
            return;
        }

        if (!apellidos.matches(
                "[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+"
        )) {
            mostrarError(
                    "Los apellidos solo pueden contener letras."
            );
            return;
        }

        if (apellidos.length() < 2) {
            mostrarError(
                    "Ingrese un apellido válido."
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
                    "La fecha de nacimiento no es válida.\n" +
                            "Utilice el formato dd/MM/yyyy."
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

        RadioButton solicitudSeleccionada =
                (RadioButton)
                        grupoSolicitud
                                .getSelectedToggle();

        if (solicitudSeleccionada == null) {
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

        if (rutaFotografia == null) {
            mostrarError(
                    "Seleccione una fotografía."
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

        Cliente cliente = new Cliente(
                nombres + " " + apellidos,
                cbTipoCliente.getValue(),
                cbCiudad.getValue(),
                fechaNacimiento,
                solicitudSeleccionada.getText(),
                servicios,
                rutaFotografia
        );

        DatosClientes.agregarCliente(cliente);

        Alert alerta = new Alert(
                Alert.AlertType.INFORMATION
        );

        alerta.setTitle(
                "Registro de cliente"
        );

        alerta.setHeaderText(
                "Cliente registrado correctamente"
        );

        alerta.setContentText(
                "El cliente " +
                        nombres + " " +
                        apellidos +
                        " fue registrado."
        );

        alerta.showAndWait();

        limpiarFormulario();
    }

    @FXML
    private void limpiarFormulario() {

        txtNombres.clear();
        txtApellidos.clear();

        cbTipoCliente.setValue(null);
        cbCiudad.setValue(null);

        dpFechaNacimiento.setValue(null);
        dpFechaNacimiento.getEditor().clear();

        grupoSolicitud.selectToggle(null);

        chkInternet.setSelected(false);
        chkTelefonia.setSelected(false);
        chkCable.setSelected(false);

        imgFotografia.setImage(null);

        rutaFotografia = null;
    }

    @FXML
    private void cancelar(ActionEvent event)
            throws IOException {

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

    private void mostrarError(String mensaje) {

        Alert alerta = new Alert(
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