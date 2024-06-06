package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.ingreso.Ingresos;
import com.example.maxfitvistaempleados.ingreso.IngresosDAO;
import com.example.maxfitvistaempleados.pago.Pagos;
import com.example.maxfitvistaempleados.pago.PagosDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PagoController implements Initializable {
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private ComboBox<String> cbPago;
    @javafx.fxml.FXML
    private ComboBox<String> cbGrupo;
    @javafx.fxml.FXML
    private TableView<Pagos> tvPagos;
    @javafx.fxml.FXML
    private TableColumn<Pagos ,String> CCNombre;
    @javafx.fxml.FXML
    private TableColumn<Pagos ,String> CCFecha;
    @javafx.fxml.FXML
    private TableColumn<Pagos ,String> CCPago;
    @javafx.fxml.FXML
    private TableColumn<Pagos ,String> CCGrupo;
    @javafx.fxml.FXML
    private TableColumn<Pagos ,String> CCDinero;
    @javafx.fxml.FXML
    private Button btnCerrarSesion;
    @javafx.fxml.FXML
    private Button btnCliente;
    @javafx.fxml.FXML
    private Button btnPago;
    @javafx.fxml.FXML
    private Button btnDietas;
    @javafx.fxml.FXML
    private Button btnRutina;
    @javafx.fxml.FXML
    private Button btnCP;
    private ObservableList<Pagos> observableList;

    private PagosDAO pagosDAO = new PagosDAO();
    @javafx.fxml.FXML
    private TableColumn<Pagos ,String> CCEmpleado;
    @javafx.fxml.FXML
    private TextField txtNombreEmp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> tiposPago = FXCollections.observableArrayList("Efectivo", "Tarjeta","Todos");
        ObservableList<String> tiposdeCompra = FXCollections.observableArrayList("Gym","Zumba","Yoga","Pilates","Boxeo","Todos");
        cbGrupo.setItems(tiposdeCompra);
        cbPago.setItems(tiposPago);
        observableList = FXCollections.observableArrayList();
        this.CCEmpleado.setCellValueFactory((fila) ->{
            String CCEmpleado = String.valueOf(fila.getValue().getEmpleado());
            return new SimpleStringProperty(CCEmpleado);
        });
        this.CCNombre.setCellValueFactory((fila) ->{
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

        observableList.addAll(pagosDAO.getAll());
        tvPagos.setItems(observableList);
        // Al hacer dos veces click en la tabla te lleva a la vista de editar cliente
        tvPagos.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Pagos selectedPagos = tvPagos.getSelectionModel().getSelectedItem();
                if (selectedPagos != null) {
                    Sesion.setPagos(selectedPagos);
                    try {
                        Main.changeScene("EP-view.fxml","Editar Pagos");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


    @javafx.fxml.FXML
    public void bqNombreIngreso(ActionEvent actionEvent) {
        String nombreIngreso = txtNombre.getText().toLowerCase(); // Convertir a minúsculas para una comparación sin distinción de mayúsculas/minúsculas

        // Verificar si el campo de búsqueda está vacío
        if (nombreIngreso.isEmpty()) {
            // Si está vacío, mostrar toda la información de la tabla nuevamente
            tvPagos.setItems(observableList);
        } else {
            // Filtrar la lista de ingresos por el nombre ingresado (con aproximación)
            ObservableList<Pagos> resultados = FXCollections.observableArrayList();
            for (Pagos pagos : observableList) {
                // Obtener el nombre del ingreso en minúsculas para una comparación sin distinción de mayúsculas/minúsculas
                String nombreIngresoLowerCase = pagos.getNombre().toLowerCase();
                // Verificar si el nombre del ingreso contiene la cadena ingresada por el usuario
                if (nombreIngresoLowerCase.contains(nombreIngreso)) {
                    resultados.add(pagos);
                }
            }

            // Actualizar la tabla con los resultados de la búsqueda
            tvPagos.setItems(resultados);
        }
    }


    @javafx.fxml.FXML
    public void bqPago(ActionEvent actionEvent) {
        // Obtener el tipo de pago seleccionado en el ComboBox
        String tipoPagoSeleccionado = cbPago.getValue();

        // Verificar si se ha seleccionado "Todos"
        if ("Todos".equals(tipoPagoSeleccionado)) {
            // Mostrar toda la información de la tabla nuevamente
            tvPagos.setItems(observableList);
        } else {
            // Realizar la búsqueda de pagos asociados con el tipo de pago seleccionado
            PagosDAO pagosDAO = new PagosDAO();
            List<Pagos> pagosList = pagosDAO.obtenerIngresosPorTipoPago(tipoPagoSeleccionado);

            // Convertir la lista de pagos a un ObservableList
            ObservableList<Pagos> resultados = FXCollections.observableArrayList(pagosList);

            // Actualizar la tabla tvPagos con los resultados de la búsqueda
            tvPagos.setItems(resultados);
        }
    }

    @javafx.fxml.FXML
    public void bqNombreIngresoEmp(ActionEvent actionEvent) {
        String nombreIngreso = txtNombreEmp.getText().toLowerCase(); // Convertir a minúsculas para una comparación sin distinción de mayúsculas/minúsculas

        // Verificar si el campo de búsqueda está vacío
        if (nombreIngreso.isEmpty()) {
            // Si está vacío, mostrar toda la información de la tabla nuevamente
            tvPagos.setItems(observableList);
        } else {
            // Filtrar la lista de ingresos por el nombre ingresado (con aproximación)
            ObservableList<Pagos> resultados = FXCollections.observableArrayList();
            for (Pagos pagos : observableList) {
                // Obtener el nombre del ingreso en minúsculas para una comparación sin distinción de mayúsculas/minúsculas
                String nombreIngresoLowerCase = pagos.getEmpleado().toLowerCase();
                // Verificar si el nombre del ingreso contiene la cadena ingresada por el usuario
                if (nombreIngresoLowerCase.contains(nombreIngreso)) {
                    resultados.add(pagos);
                }
            }

            // Actualizar la tabla con los resultados de la búsqueda
            tvPagos.setItems(resultados);
        }
    }

    @javafx.fxml.FXML
    public void bqGrupo(ActionEvent actionEvent) {
        // Obtener el grupo seleccionado en el ComboBox
        String grupoSeleccionado = cbGrupo.getValue();

        // Verificar si se ha seleccionado "Todos"
        if ("Todos".equals(grupoSeleccionado)) {
            // Mostrar toda la información de la tabla nuevamente
            tvPagos.setItems(observableList);
        } else {
            // Realizar la búsqueda de pagos asociados con el grupo seleccionado
            PagosDAO pagosDAO = new PagosDAO();
            List<Pagos> pagosList = pagosDAO.obtenerIngresosPorTipoComprar(grupoSeleccionado);

            // Convertir la lista de pagos a un ObservableList
            ObservableList<Pagos> resultados = FXCollections.observableArrayList(pagosList);

            // Actualizar la tabla tvPagos con los resultados de la búsqueda
            tvPagos.setItems(resultados);
        }
    }
    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) throws IOException {
        Main.changeScene("login-view.fxml","Inicio Sesion");
    }

    @javafx.fxml.FXML
    public void Cliente(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Cliente");
    }

    @javafx.fxml.FXML
    public void Pago(ActionEvent actionEvent) throws IOException {
        Main.changeScene("pago-view.fxml","Pago");
    }

    @javafx.fxml.FXML
    public void Dietas(ActionEvent actionEvent) throws IOException {
        Main.changeScene("dietas-view.fxml","Dietas");
    }

    @javafx.fxml.FXML
    public void Rutina(ActionEvent actionEvent) throws IOException {
        Main.changeScene("Rutina-view.fxml","Rutina");
    }
    public void Clientes(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Clientes");
    }

    @javafx.fxml.FXML
    public void CrearPago(ActionEvent actionEvent) throws IOException {
        Main.changeScene("CP-view.fxml","Dietas");
    }



}

