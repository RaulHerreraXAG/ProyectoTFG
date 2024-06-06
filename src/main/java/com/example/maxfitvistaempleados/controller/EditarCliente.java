package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.ClienteDAOImp;
import com.example.maxfitvistaempleados.clientes.Clientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditarCliente implements Initializable {
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
    private TextField txtApelldos;
    @javafx.fxml.FXML
    private TextField txtPeso;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtAltura;
    @javafx.fxml.FXML
    private TextArea txtObservacion;
    @javafx.fxml.FXML
    private TextField txtEdad;
    @javafx.fxml.FXML
    private Button ButtonCrear;
    @javafx.fxml.FXML
    private Button ButtonAtras;
    @javafx.fxml.FXML
    private Button ButtonEliminar;
    @javafx.fxml.FXML
    private TextField txtCorreo;

    private ClienteDAOImp clienteDAOImp = new ClienteDAOImp();


    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) throws IOException {
        Main.login("Inicio Sesión");
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
    public void Clientes(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Clientes");
    }

    @javafx.fxml.FXML
    public void CCliente(ActionEvent actionEvent) throws IOException {
        ClienteDAOImp clienteDAOImp1 = new ClienteDAOImp();

        Clientes clientes = new Clientes();

        clientes.setMatricula(Sesion.getCliente().getMatricula());
        clientes.setContrasena(Sesion.getCliente().getContrasena());
        clientes.setFechaInicio(Sesion.getCliente().getFechaInicio());
        clientes.setFechaFin(Sesion.getCliente().getFechaFin());
        clientes.setPeso(Double.valueOf(txtPeso.getText()));
        clientes.setObservacion(txtObservacion.getText());
        clientes.setEdad(Integer.parseInt(txtEdad.getText()));
        clientes.setGenero(cbGenero.getValue());
        clientes.setAltura(Double.valueOf(txtAltura.getText()));
        clientes.setApellidos(txtApelldos.getText());
        clientes.setNombre(txtNombre.getText());
        clientes.setCorreo(txtCorreo.getText());

        clienteDAOImp1.update(clientes);
        Sesion.setCliente(clientes);

        Main.changeScene("view-empleado.fxml", "Clientes");

        //Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("El cliente ha sido actualizado");
        alert.setContentText("Nombre del Cliente: " + Sesion.getCliente().getNombre() + " realizado por " +  Sesion.getEmpleado().getNombre() + " "+ Sesion.getEmpleado().getApellidos());

    }

    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Cliente");
    }

    @javafx.fxml.FXML
    public void ECliente(ActionEvent actionEvent) throws IOException {
        var clienteSeleccionado = Sesion.getCliente();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Deseas borrar el cliente llamado: " + clienteSeleccionado.getNombre() + "?");
        var result = alert.showAndWait().get();

        if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            clienteDAOImp.delete(clienteSeleccionado);
            Main.changeScene("view-empleado.fxml", "Clientes");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clientes cliente = Sesion.getCliente();


        ObservableList<String> genero = FXCollections.observableArrayList();
        genero.addAll("Hombre", "Mujer");
        cbGenero.setItems(genero);
        if (cliente != null) {
            txtNombre.setText(Sesion.getCliente().getNombre());
            txtAltura.setText(String.valueOf(Sesion.getCliente().getAltura()));
            txtApelldos.setText(Sesion.getCliente().getApellidos());
            txtCorreo.setText(Sesion.getCliente().getCorreo());
            txtEdad.setText(String.valueOf(Sesion.getCliente().getEdad()));
            txtObservacion.setText(Sesion.getCliente().getObservacion());
            txtPeso.setText(String.valueOf(Sesion.getCliente().getPeso()));
            cbGenero.setValue(Sesion.getCliente().getGenero());
        } else {
            showAlert("Error", "No se ha seleccionado ningún cliente.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}