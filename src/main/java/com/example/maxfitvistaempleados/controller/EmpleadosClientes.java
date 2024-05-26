package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.ClienteDAOImp;
import com.example.maxfitvistaempleados.clientes.Clientes;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class EmpleadosClientes implements Initializable {
    @javafx.fxml.FXML
    private TextField txtBusqueda;
    @javafx.fxml.FXML
    private Button ButtonSesion;
    @javafx.fxml.FXML
    private Button buittonCliente;
    @javafx.fxml.FXML
    private Button buttonIngresos;
    @javafx.fxml.FXML
    private Button btnPagos;
    @javafx.fxml.FXML
    private Button btnDietas;
    @javafx.fxml.FXML
    private Button btnRutina;
    @javafx.fxml.FXML
    private ComboBox<String> cbGenero;
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
    private TextArea txtObservacion;
    @javafx.fxml.FXML
    private TextField txtEdad;
    @javafx.fxml.FXML
    private TextField txtFechaFin;
    @javafx.fxml.FXML
    private Button ButtonCrear;
    @javafx.fxml.FXML
    private Button ButtonRenovar;

    private final ClienteDAOImp clienteDAO = new ClienteDAOImp();
    @javafx.fxml.FXML
    private Button ButtonEditar;

    private ObservableList<Clientes> observableList;
    @javafx.fxml.FXML
    private Button ButtonEditarRutina;
    @javafx.fxml.FXML
    private Button ButtonEditarDieta;

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
            // Inicialmente establece todos los campos de texto como no editables
            txtNombre.setEditable(false);
            txtApelldos.setEditable(false);
            cbGenero.setDisable(true);
            txtFechaInicio.setEditable(false);
            txtEdad.setEditable(false);
            txtAltura.setEditable(false);
            txtPeso.setEditable(false);
            txtObservacion.setEditable(false);
            txtMatricula.setEditable(false);
            txtFechaFin.setEditable(false);


        cbGenero.getItems().addAll("Hombre", "Mujer");

    }

    @javafx.fxml.FXML
    public void Busquedad(ActionEvent actionEvent) {
        String textoBusqueda = txtBusqueda.getText().trim().toLowerCase();
        ArrayList<Clientes> clientesList = clienteDAO.getAll();
        boolean encontrado = false;

        for (Clientes cliente : clientesList) {
            String nombreCompleto = cliente.getNombre().toLowerCase() + " " + cliente.getApellidos().toLowerCase();
            if (nombreCompleto.equals(textoBusqueda)) {
                // Configurar el cliente encontrado en la sesión
                Sesion.setCliente(cliente);

                // Establecer los datos del cliente en los campos de texto
                txtNombre.setText(cliente.getNombre());
                txtApelldos.setText(cliente.getApellidos());
                cbGenero.setValue(cliente.getGenero().toString());
                txtFechaInicio.setText(String.valueOf(cliente.getFechaInicio()));
                txtEdad.setText(String.valueOf(cliente.getEdad()));
                txtAltura.setText(String.valueOf(cliente.getAltura()));
                txtPeso.setText(String.valueOf(cliente.getPeso()));
                txtObservacion.setText(cliente.getObservacion());
                txtMatricula.setText(String.valueOf(cliente.getMatricula()));
                txtFechaFin.setText(String.valueOf(cliente.getFechaFin()));

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


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApelldos.clear();
        cbGenero.getSelectionModel().clearSelection();
        txtFechaInicio.clear();
        txtEdad.clear();
        txtAltura.clear();
        txtPeso.clear();
        txtObservacion.clear();
        txtMatricula.clear();
        txtFechaFin.clear();
    }

    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) throws IOException {
        Main.changeScene("login-view.fxml","Inicio Sesión");
    }

    @javafx.fxml.FXML
    public void Ingresos(ActionEvent actionEvent) throws IOException {
        Main.changeScene("ingreso-view.fxml","Ingresos");
    }

    @javafx.fxml.FXML
    public void Pagos(ActionEvent actionEvent) throws IOException {
        Main.changeScene("pago-view.fxml","Pagos");

    }

    @javafx.fxml.FXML
    public void Dietas(ActionEvent actionEvent) throws IOException {
        Main.changeScene("dieta-view.fxml","Dietas");

    }

    @javafx.fxml.FXML
    public void Rutina(ActionEvent actionEvent) throws IOException {
        Main.changeScene("Rutina-view.fxml","Rutina");

    }

    @javafx.fxml.FXML
    public void CCliente(ActionEvent actionEvent) throws IOException {
        Main.changeScene("CC-view.fxml","Crear Cliente");
    }
    public void Clientes(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Clientes");
    }


    @javafx.fxml.FXML
    public void ECliente(ActionEvent actionEvent) throws IOException {
        Clientes cliente = Sesion.getCliente();
        if (cliente != null) {
            Main.changeScene("editarcliente-view.fxml", "Editar Cliente");
        } else {
            // Manejar el caso en que el cliente es nulo
            showAlert("Error", "No se ha seleccionado ningún cliente para editar.");
        }
    }
    @javafx.fxml.FXML
    public void RMatricula(ActionEvent actionEvent) throws IOException {
        Main.changeScene("RenuevaMatricula-view.fxml","Renovar Matricula");
    }

    @javafx.fxml.FXML
    public void ERutina(ActionEvent actionEvent) throws IOException {
            Clientes clientes = Sesion.getCliente();
            if (clientes != null){
                Main.changeScene("RutinaXcliente-view.fxml","Rutina del cliente");
            }else {
                // Manejar el caso en que el cliente es nulo
                showAlert("Error", "No se ha seleccionado ningún cliente para ver la rutina.");
            }
        }

    @javafx.fxml.FXML
    public void EDieta(ActionEvent actionEvent) throws IOException {
        Clientes clientes = Sesion.getCliente();
        if (clientes != null){
            Main.changeScene("dietaXcliente-view.fxml","Dietas del cliente");
        }else {
            // Manejar el caso en que el cliente es nulo
            showAlert("Error", "No se ha seleccionado ningún cliente para ver la rutina.");
        }
    }
}
