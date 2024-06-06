package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.ClienteDAOImp;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.empleados.Empleado;
import com.example.maxfitvistaempleados.empleados.EmpleadoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CrearEmpleado implements Initializable {
    @javafx.fxml.FXML
    private Button ButtonSesion;
    @javafx.fxml.FXML
    private Button btnEmpleados;
    @javafx.fxml.FXML
    private Button buttonIngresos;
    @javafx.fxml.FXML
    private Button btnPagos;
    @javafx.fxml.FXML
    private TextField txtSalario;
    @javafx.fxml.FXML
    private TextField txtCorreo;
    @javafx.fxml.FXML
    private TextField txtTrabajo;
    @javafx.fxml.FXML
    private TextField txtApelldos;
    @javafx.fxml.FXML
    private TextField txtTF;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private Button ButtonCrear;
    @javafx.fxml.FXML
    private Button ButtonVolverAtras;
    @javafx.fxml.FXML
    private ComboBox<String> cbGenero;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbGenero.getItems().addAll("Hombre", "Mujer");
    }


    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) throws IOException {
        Main.login("Inicio Sesión");
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
    public void CrearEMP(ActionEvent actionEvent) throws IOException {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Long idNuevo = EmpleadoDAO.countClientes() + 1;
        String contrasenyapredeterminada = "Maxfit2024";
        Empleado empleado = new Empleado();

        empleado.setMatricula(idNuevo);
        empleado.setNombre(txtNombre.getText());
        empleado.setApellidos(txtApelldos.getText());
        empleado.setCorreo(txtCorreo.getText());
        empleado.setContrasena(contrasenyapredeterminada);
        empleado.setGenero(cbGenero.getValue());
        empleado.setTelefono(Long.valueOf(txtTF.getText()));
        empleado.setTiposTrabajo(txtTrabajo.getText());
        empleado.setSalario(Double.valueOf(txtSalario.getText()));
        empleadoDAO.save(empleado);
        Sesion.setEmpleado(empleado);

        if (empleado != null){
            Main.changeScene("view-admin.fxml","Empleado");
        }

        //Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("El cliente ha sido creado");
        alert.setContentText("Nombre del Empleado: " + Sesion.getEmpleado().getNombre() + " "+ Sesion.getEmpleado().getApellidos());
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-admin.fxml","Empleado");
    }
}