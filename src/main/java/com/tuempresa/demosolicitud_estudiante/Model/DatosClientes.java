package com.tuempresa.demosolicitud_estudiante.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatosClientes {

    private static final ObservableList<Cliente> clientes =
            FXCollections.observableArrayList();

    public static ObservableList<Cliente> getClientes() {
        return clientes;
    }

    public static void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
}