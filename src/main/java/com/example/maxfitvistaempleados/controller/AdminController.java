package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.empleados.Empleado;
import com.example.maxfitvistaempleados.empleados.EmpleadoDAO;
import com.example.maxfitvistaempleados.rutina.Ejercicios;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @javafx.fxml.FXML
    private TextField txtBusqueda;
    @javafx.fxml.FXML
    private Button ButtonSesion;
    @javafx.fxml.FXML
    private Button buttonIngresos;
    @javafx.fxml.FXML
    private Button btnPagos;
    @javafx.fxml.FXML
    private Button btnEmpleados;
    @javafx.fxml.FXML
    private TextField txtFechaInicio;
    @javafx.fxml.FXML
    private TextField txtApelldos;
    @javafx.fxml.FXML
    private TextField txtPeso;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtAltura;
    @javafx.fxml.FXML
    private TextField txtMatricula;
    @javafx.fxml.FXML
    private Button ButtonCrear;
    @javafx.fxml.FXML
    private Button ButtonEditar;

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private ObservableList<Empleado> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtNombre.setEditable(false);
        txtApelldos.setEditable(false);
        txtFechaInicio.setEditable(false);
        txtAltura.setEditable(false);
        txtPeso.setEditable(false);
        txtMatricula.setEditable(false);
    }


    @javafx.fxml.FXML
    public void Busquedad(ActionEvent actionEvent) {
        String textoBusqueda = txtBusqueda.getText().trim().toLowerCase();
        ArrayList<Empleado> empleadosList = empleadoDAO.getAll();
        boolean encontrado = false;

        for (Empleado empleado : empleadosList) {
            String nombreCompleto = empleado.getNombre().toLowerCase() + " " + empleado.getApellidos().toLowerCase();
            if (nombreCompleto.equals(textoBusqueda)) {
                // Configurar el cliente encontrado en la sesión
                Sesion.setEmpleado(empleado);

                // Establecer los datos del cliente en los campos de texto
                txtNombre.setText(empleado.getNombre());
                txtApelldos.setText(empleado.getApellidos());
                txtFechaInicio.setText(String.valueOf(empleado.getCorreo()));
                txtAltura.setText(String.valueOf(empleado.getTelefono()));
                txtPeso.setText(String.valueOf(empleado.getTiposTrabajo()));
                txtMatricula.setText(String.valueOf(empleado.getMatricula()));

                // Marcar que se encontró el cliente
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            // Si no se encuentra ninguna coincidencia, muestra un mensaje de error y limpia todos los campos
            showAlert("Error", "No se encontraron coincidencias para el nombre completo proporcionado.");
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApelldos.clear();
        txtFechaInicio.clear();
        txtAltura.clear();
        txtPeso.clear();
        txtMatricula.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) throws IOException {
        Main.changeScene("login-view.fxml","Inicio Sesión");
    }
    @javafx.fxml.FXML
    public void Ingresos(ActionEvent actionEvent) throws IOException {
        Main.changeScene("ingresoAdmin-view.fxml","Ingresos");
    }

    @javafx.fxml.FXML
    public void Pagos(ActionEvent actionEvent) throws IOException {
        Main.changeScene("pagoAdmin-view.fxml","Pagos");

    }

    @javafx.fxml.FXML
    public void Empleados(ActionEvent actionEvent) throws IOException {
        Main.changeScene("View-admin.fxml","Empleados");
    }

    @javafx.fxml.FXML
    public void CCliente(ActionEvent actionEvent) throws IOException {
        Main.changeScene("CE-view.fxml","Crear Empleado");
    }


    @javafx.fxml.FXML
    public void ECliente(ActionEvent actionEvent) throws IOException {
        Empleado empleado = Sesion.getEmpleado();
        if (empleado != null) {
            Main.changeScene("EE-view.fxml", "Editar Empleado");
        } else {
            // Manejar el caso en que el cliente es nulo
            showAlert("Error", "No se ha seleccionado ningún empleado para editar.");
        }
    }


}