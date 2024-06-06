package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.empleados.Empleado;
import com.example.maxfitvistaempleados.empleados.EmpleadoDAO;
import com.example.maxfitvistaempleados.ingreso.Ingresos;
import com.example.maxfitvistaempleados.ingreso.IngresosDAO;
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
import java.util.ResourceBundle;

public class CrearIngresos implements Initializable {
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
        Main.changeScene("ingreso-view.fxml","Ingresos");
    }

    @javafx.fxml.FXML
    public void CIngresos(ActionEvent actionEvent) throws IOException {
        IngresosDAO ingresosDAO = new IngresosDAO();
        Empleado empleado = Sesion.getEmpleado();
        Long idNuevo = IngresosDAO.countClientes() + 1;
        Ingresos ingresos = new Ingresos();
        Sesion.setIngresos(ingresos);

        ingresos.setId(idNuevo);
        ingresos.setNombre(txtNombre.getText());
        ingresos.setDescripcion(TADesc.getText());
        ingresos.setTipopago(cbPago.getValue());
        ingresos.setGrupo(cbGrupo.getValue());
        ingresos.setDinero(Double.valueOf(txtDinero.getText()));
        ingresos.setFecha(LocalDate.now());
        ingresos.setEmpleado(empleado.getNombre());
        ingresosDAO.save(ingresos);

        if (ingresos != null){
            Main.changeScene("ingreso-view.fxml","Ingresos");
        }

        //Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("Tu ingreso ha sido creado");
        alert.setContentText("Nombre del ingreso: " + Sesion.getIngresos().getNombre() + " realizado por " + Sesion.getEmpleado().getNombre() + " "+ Sesion.getEmpleado().getApellidos());
        alert.showAndWait();
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

    public void Clientes(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Clientes");
    }
}