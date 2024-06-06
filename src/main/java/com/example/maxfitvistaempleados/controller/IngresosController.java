package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.empleados.Empleado;
import com.example.maxfitvistaempleados.empleados.EmpleadoDAO;
import com.example.maxfitvistaempleados.ingreso.Ingresos;
import com.example.maxfitvistaempleados.ingreso.IngresosDAO;
import com.example.maxfitvistaempleados.pago.PagosDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class IngresosController implements Initializable {
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TableColumn<Ingresos, String> CCNombre;
    @javafx.fxml.FXML
    private TableColumn<Ingresos, String> CCFecha;
    @javafx.fxml.FXML
    private TableColumn<Ingresos, String> CCPago;
    @javafx.fxml.FXML
    private TableColumn<Ingresos, String> CCGrupo;
    @javafx.fxml.FXML
    private TableColumn<Ingresos, String> CCDinero;
    @javafx.fxml.FXML
    private Button btnCerrarSesion;
    @javafx.fxml.FXML
    private Button btnCliente;
    @javafx.fxml.FXML
    private Button btnPago;
    @javafx.fxml.FXML
    private TableView<Ingresos> tvIngresos;
    private ObservableList<Ingresos> observableList;
    @FXML
    private TableColumn<Ingresos, String> CCID;
    private IngresosDAO ingresosDAO = new IngresosDAO();

    private ArrayList<Empleado> listaEmpleados;
    @FXML
    private TextField tfEmpleado;
    @FXML
    private ComboBox<String> cbPago;
    @FXML
    private ComboBox<String> cbGrupo;

    public IngresosController() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> tiposPago = FXCollections.observableArrayList("Efectivo", "Tarjeta", "Todos");
        ObservableList<String> tiposdeCompra = FXCollections.observableArrayList("Gym", "Zumba", "Yoga", "Pilates", "Boxeo", "Todos");
        cbGrupo.setItems(tiposdeCompra);
        cbPago.setItems(tiposPago);
        observableList = FXCollections.observableArrayList();
        this.CCID.setCellValueFactory((fila) -> {
            String cID = String.valueOf(fila.getValue().getEmpleado());
            return new SimpleStringProperty(cID);
        });
        this.CCNombre.setCellValueFactory((fila) -> {
            String cNombre = String.valueOf(fila.getValue().getNombre());
            return new SimpleStringProperty(cNombre);
        });
        this.CCFecha.setCellValueFactory((fila) -> {
            String cFecha = String.valueOf(fila.getValue().getFecha());
            return new SimpleStringProperty(cFecha);
        });
        this.CCPago.setCellValueFactory((fila) -> {
            String cPago = String.valueOf(fila.getValue().getTipopago());
            return new SimpleStringProperty(cPago);
        });
        this.CCGrupo.setCellValueFactory((fila) -> {
            String cGrupo = String.valueOf(fila.getValue().getGrupo());
            return new SimpleStringProperty(cGrupo);
        });
        this.CCDinero.setCellValueFactory((fila) -> {
            String cDinero = String.valueOf(fila.getValue().getDinero());
            return new SimpleStringProperty(cDinero);
        });

        observableList.addAll(ingresosDAO.getAll());
        tvIngresos.setItems(observableList);
        // Al hacer dos veces click en la tabla te lleva a la vista de editar cliente
        tvIngresos.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Ingresos selectedIngreso = tvIngresos.getSelectionModel().getSelectedItem();
                if (selectedIngreso != null) {
                    Sesion.setIngresos(selectedIngreso);
                    try {
                        Main.changeScene("EI-view.fxml", "Editar Ingresos");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


    }

    @javafx.fxml.FXML
    public void bqNombreIngreso(ActionEvent actionEvent) {
        // Obtener el nombre del ingreso ingresado por el usuario desde un TextField
        String nombreIngreso = txtNombre.getText().toLowerCase(); // Convertir a minúsculas para una comparación sin distinción de mayúsculas/minúsculas

        // Verificar si el campo de búsqueda está vacío
        if (nombreIngreso.isEmpty()) {
            // Si está vacío, mostrar toda la información de la tabla nuevamente
            tvIngresos.setItems(observableList);
        } else {
            // Filtrar la lista de ingresos por el nombre ingresado (con aproximación)
            ObservableList<Ingresos> resultados = FXCollections.observableArrayList();
            for (Ingresos ingreso : observableList) {
                // Obtener el nombre del ingreso en minúsculas para una comparación sin distinción de mayúsculas/minúsculas
                String nombreIngresoLowerCase = ingreso.getNombre().toLowerCase();
                // Verificar si el nombre del ingreso contiene la cadena ingresada por el usuario
                if (nombreIngresoLowerCase.contains(nombreIngreso)) {
                    resultados.add(ingreso);
                }
            }

            // Actualizar la tabla con los resultados de la búsqueda
            tvIngresos.setItems(resultados);
        }
    }


    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) throws IOException {
        Main.changeScene("login-view.fxml", "Inicio Sesión");
    }

    @javafx.fxml.FXML
    public void Cliente(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml", "Cliente");
    }

    @javafx.fxml.FXML
    public void Pago(ActionEvent actionEvent) throws IOException {
        Main.changeScene("pago-view.fxml", "Pagos");
    }

    @javafx.fxml.FXML
    public void Dietas(ActionEvent actionEvent) throws IOException {
        Main.changeScene("dieta-view.fxml", "Dietas");

    }

    @javafx.fxml.FXML
    public void Rutina(ActionEvent actionEvent) throws IOException {
        Main.changeScene("rutina-view.fxml", "Rutina");
    }

    public void Clientes(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml", "Clientes");
    }

    @javafx.fxml.FXML
    public void CrearIngreso(ActionEvent actionEvent) throws IOException {
        Main.changeScene("CI-view.fxml", "Creador de Ingresos");
    }


    @FXML
    public void bqEmpleado(ActionEvent actionEvent) {
        // Obtener el nombre del ingreso ingresado por el usuario desde un TextField
        String nombreIngresoEmp = tfEmpleado.getText().toLowerCase(); // Convertir a minúsculas para una comparación sin distinción de mayúsculas/minúsculas

        // Verificar si el campo de búsqueda está vacío
        if (nombreIngresoEmp.isEmpty()) {
            // Si está vacío, mostrar toda la información de la tabla nuevamente
            tvIngresos.setItems(observableList);
        } else {
            // Filtrar la lista de ingresos por el nombre ingresado (con aproximación)
            ObservableList<Ingresos> resultados = FXCollections.observableArrayList();
            for (Ingresos ingreso : observableList) {
                // Obtener el nombre del ingreso en minúsculas para una comparación sin distinción de mayúsculas/minúsculas
                String nombreIngresoLowerCase = ingreso.getEmpleado().toLowerCase();
                // Verificar si el nombre del ingreso contiene la cadena ingresada por el usuario
                if (nombreIngresoLowerCase.contains(nombreIngresoEmp)) {
                    resultados.add(ingreso);
                }
            }

            // Actualizar la tabla con los resultados de la búsqueda
            tvIngresos.setItems(resultados);
        }
    }

    @FXML
    public void bqPago(ActionEvent actionEvent) {
        // Obtener el tipo de pago seleccionado en el ComboBox
        String tipoPagoSeleccionado = cbPago.getValue();

        // Verificar si se ha seleccionado "Todos"
        if ("Todos".equals(tipoPagoSeleccionado)) {
            // Mostrar toda la información de la tabla nuevamente
            tvIngresos.setItems(observableList);
        } else {
            // Realizar la búsqueda de ingresos asociados con el tipo de pago seleccionado
            IngresosDAO ingresosDAO = new IngresosDAO();
            List<Ingresos> ingresosList = ingresosDAO.obtenerIngresosPorTipoPago(tipoPagoSeleccionado);

            // Convertir la lista de ingresos a un ObservableList
            ObservableList<Ingresos> resultados = FXCollections.observableArrayList(ingresosList);

            // Actualizar la tabla tvIngresos con los resultados de la búsqueda
            tvIngresos.setItems(resultados);
        }
    }

    @FXML
    public void bqGrupo(ActionEvent actionEvent) {
        // Obtener el grupo seleccionado en el ComboBox
        String grupoSeleccionado = cbGrupo.getValue();

        // Verificar si se ha seleccionado "Todos"
        if ("Todos".equals(grupoSeleccionado)) {
            // Mostrar toda la información de la tabla nuevamente
            tvIngresos.setItems(observableList);
        } else {
            // Realizar la búsqueda de ingresos asociados con el grupo seleccionado
            IngresosDAO ingresosDAO = new IngresosDAO();
            List<Ingresos> ingresosList = ingresosDAO.obtenerIngresosPorTipoComprar(grupoSeleccionado);

            // Convertir la lista de ingresos a un ObservableList
            ObservableList<Ingresos> resultados = FXCollections.observableArrayList(ingresosList);

            // Actualizar la tabla tvIngresos con los resultados de la búsqueda
            tvIngresos.setItems(resultados);
        }
    }
}