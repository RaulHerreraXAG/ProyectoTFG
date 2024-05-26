package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.ClienteDAOImp;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.empleados.Empleado;
import com.example.maxfitvistaempleados.empleados.EmpleadoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditarEmpleado implements Initializable {
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
    private Button ButtonEliminarCliente;

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    @javafx.fxml.FXML
    private ComboBox<String> cbGenero;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Empleado empleado = Sesion.getEmpleado();

        if(empleado != null){
            txtNombre.setText(Sesion.getEmpleado().getNombre());
            txtApelldos.setText(Sesion.getEmpleado().getApellidos());
            txtCorreo.setText(Sesion.getEmpleado().getCorreo());
            txtTF.setText(String.valueOf(Sesion.getEmpleado().getTelefono()));
            txtTrabajo.setText(Sesion.getEmpleado().getTiposTrabajo());
            txtSalario.setText(String.valueOf(Sesion.getEmpleado().getSalario()));
            cbGenero.setValue(Sesion.getEmpleado().getGenero());
        }else{
            showAlert("Error", "No se ha seleccionado ningún empleado.");
        }
        cbGenero.getItems().addAll("Hombre", "Mujer");
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
    public void CrearEMP(ActionEvent actionEvent) throws IOException {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        Empleado empleado = new Empleado();

        empleado.setMatricula(Sesion.getEmpleado().getMatricula());
        empleado.setContrasena(Sesion.getEmpleado().getContrasena());
        empleado.setApellidos(txtApelldos.getText());
        empleado.setNombre(txtNombre.getText());
        empleado.setCorreo(txtCorreo.getText());
        empleado.setSalario(Double.valueOf(txtSalario.getText()));
        empleado.setTelefono(Long.valueOf(txtTF.getText()));
        empleado.setTiposTrabajo(txtTrabajo.getText());
        empleado.setGenero(cbGenero.getValue());



        empleadoDAO.update(empleado);
        Sesion.setEmpleado(empleado);

        Main.changeScene("view-admin.fxml", "Clientes");

        //Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("El cliente ha sido actualizado");
        alert.setContentText("Nombre del Empleado: " + Sesion.getEmpleado().getNombre() + " " + Sesion.getEmpleado().getApellidos());
    }

    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-admin.fxml","Empleado");
    }


    @javafx.fxml.FXML
    public void EliminarCliente(ActionEvent actionEvent) throws IOException {
        var empleadoSelec = Sesion.getEmpleado();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Deseas borrar el empleado llamado: " + empleadoSelec.getNombre() + "?");
        var result = alert.showAndWait().get();

        if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            empleadoDAO.delete(empleadoSelec);
            Main.changeScene("view-admin.fxml", "Empleado");
        }
    }
}