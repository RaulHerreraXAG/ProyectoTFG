package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.empleados.Empleado;
import com.example.maxfitvistaempleados.empleados.EmpleadoDAO;
import com.example.maxfitvistaempleados.pago.Pagos;
import com.example.maxfitvistaempleados.pago.PagosDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.EnumMap;
import java.util.ResourceBundle;

public class CrearPago implements Initializable {
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
    private Button ButtonCrear;
    @javafx.fxml.FXML
    private Button ButtonAtras;
    @javafx.fxml.FXML
    private TextField txtDinero;
    @javafx.fxml.FXML
    private ComboBox<String> cbPago;
    @javafx.fxml.FXML
    private ComboBox<String> cbGrupo;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextArea TADesc;

    private ObservableList<Empleado> empleadoObservableList;


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


    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Main.changeScene("pago-view.fxml","Pagos");
    }
    public void Clientes(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Clientes");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        empleadoObservableList = FXCollections.observableArrayList();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        ObservableList<String> tiposPago = FXCollections.observableArrayList();
        tiposPago.addAll("Efectivo", "Tarjeta");
        cbPago.setItems(tiposPago);

        ObservableList<String> grupo = FXCollections.observableArrayList();
        grupo.addAll("Gym", "Zumba", "Yoga", "Pilates", "Boxeo");
        cbGrupo.setItems(grupo);
    }

    @javafx.fxml.FXML
    public void CPago(ActionEvent actionEvent) throws IOException {
        PagosDAO pagosDAO = new PagosDAO();
        Empleado empleado = Sesion.getEmpleado();
        Long idNuevo = PagosDAO.countClientes() + 1;
        Pagos pagoNuevo = new Pagos();

        pagoNuevo.setId(idNuevo);
        pagoNuevo.setNombre(txtNombre.getText());
        pagoNuevo.setDescripcion(TADesc.getText());
        pagoNuevo.setTipopago(cbPago.getValue());
        pagoNuevo.setGrupo(cbGrupo.getValue());
        pagoNuevo.setDinero(Double.valueOf(txtDinero.getText()));
        pagoNuevo.setFecha(LocalDate.now());
        pagoNuevo.setEmpleado(empleado.getNombre());
        pagosDAO.save(pagoNuevo);
        Sesion.setPagos(pagoNuevo);

        if (pagoNuevo != null){
            Main.changeScene("pago-view.fxml","Pago");
        }

        //Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("Tu Pago ha sido creado");
        alert.setContentText("Nombre del Pago: " + Sesion.getPagos().getNombre() + " realizado por " + Sesion.getEmpleado().getNombre() + " "+ Sesion.getEmpleado().getApellidos());


    }
}
