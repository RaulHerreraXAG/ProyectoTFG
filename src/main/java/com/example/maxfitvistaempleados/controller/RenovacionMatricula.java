package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.ClienteDAOImp;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.empleados.Empleado;
import com.example.maxfitvistaempleados.ingreso.Ingresos;
import com.example.maxfitvistaempleados.ingreso.IngresosDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RenovacionMatricula implements Initializable {
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
    private ComboBox<String> cbTipoRenovacion;
    @javafx.fxml.FXML
    private Button ButtonRenovar;
    @javafx.fxml.FXML
    private Button ButtonAtras;

    private ClienteDAOImp clienteDAOImp = new ClienteDAOImp();
    @javafx.fxml.FXML
    private Label lblTXT;
    @javafx.fxml.FXML
    private ComboBox<String> cbTipoPago;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> renovacion = FXCollections.observableArrayList();
        renovacion.addAll("1 mes","2 meses","3 meses","6 meses","1 año");
        cbTipoRenovacion.setItems(renovacion);

        ObservableList<String> pago = FXCollections.observableArrayList();
        pago.addAll("Tarjeta","Efectivo");
        cbTipoPago.setItems(pago);
        lblTXT.setText("Renovación de matrícula de: "+ Sesion.getCliente().getNombre() + " "+ Sesion.getCliente().getApellidos());
    }


    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) throws IOException {
        Main.changeScene("Login-view.fxml","Inicio Sesión");
    }

    @javafx.fxml.FXML
    public void Ingresos(ActionEvent actionEvent) throws IOException {
        Main.changeScene("Ingreso-view.fxml","Ingresos");
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
    public void Renovar(ActionEvent actionEvent) throws IOException {
        Clientes cliente = Sesion.getCliente();

        cliente.setMatricula(Sesion.getCliente().getMatricula());
        cliente.setNombre(Sesion.getCliente().getNombre());
        cliente.setApellidos(Sesion.getCliente().getApellidos());
        cliente.setCorreo(Sesion.getCliente().getCorreo());
        cliente.setAltura(Sesion.getCliente().getAltura());
        cliente.setEdad(Sesion.getCliente().getEdad());
        cliente.setContrasena(Sesion.getCliente().getContrasena());
        cliente.setGenero(Sesion.getCliente().getGenero());
        cliente.setPeso(Sesion.getCliente().getPeso());
        cliente.setObservacion(Sesion.getCliente().getObservacion());
        cliente.setFechaInicio(Sesion.getCliente().getFechaInicio());
        // Obtener la duración seleccionada por el usuario
        String duracionSeleccionada = cbTipoRenovacion.getValue();
        LocalDate fechaFin = calcularFechaFin(duracionSeleccionada);
        cliente.setFechaFin(fechaFin);

        clienteDAOImp.update(cliente);
        Sesion.setCliente(cliente);


        if(cliente != null) {
            Main.changeScene("view-empleado.fxml","Cliente");
        }
    }

    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Cliente");
    }


    private LocalDate calcularFechaFin(String duracionSeleccionada) {
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = null;
        try {
            switch (duracionSeleccionada) {
                case "1 mes":
                    fechaFin = fechaInicio.plusMonths(1);
                    IngresosDAO ingresosDAO = new IngresosDAO();
                    Empleado empleado = Sesion.getEmpleado();
                    Long idNuevo = IngresosDAO.countClientes() + 1;
                    Ingresos ingresos = new Ingresos();
                    Sesion.setIngresos(ingresos);

                    ingresos.setId(idNuevo);
                    ingresos.setNombre("Renovacion de matricula");
                    ingresos.setDescripcion("Se ha realizado una renovación de matricula para la persona " + Sesion.getCliente().getNombre() + " " + Sesion.getCliente().getApellidos());
                    ingresos.setTipopago(cbTipoPago.getValue());
                    ingresos.setGrupo("Gym");
                    ingresos.setDinero(33.00);
                    ingresos.setFecha(LocalDate.now());
                    ingresos.setEmpleado(empleado.getNombre());
                    ingresosDAO.save(ingresos);

                    break;
                case "2 meses":
                    fechaFin = fechaInicio.plusMonths(2);
                    IngresosDAO ingresosDAO2 = new IngresosDAO();
                    Empleado empleado2 = Sesion.getEmpleado();
                    Long idNuevo2 = IngresosDAO.countClientes() + 1;
                    Ingresos ingresos2 = new Ingresos();
                    Sesion.setIngresos(ingresos2);

                    ingresos2.setId(idNuevo2);
                    ingresos2.setNombre("Renovacion de matricula");
                    ingresos2.setDescripcion("Se ha realizado una renovación de matricula para la persona " + Sesion.getCliente().getNombre() + " " + Sesion.getCliente().getApellidos());
                    ingresos2.setTipopago(cbTipoPago.getValue());
                    ingresos2.setGrupo("Gym");
                    ingresos2.setDinero(60.00);
                    ingresos2.setFecha(LocalDate.now());
                    ingresos2.setEmpleado(empleado2.getNombre());
                    ingresosDAO2.save(ingresos2);
                    break;
                case "3 meses":
                    fechaFin = fechaInicio.plusMonths(3);
                    IngresosDAO ingresosDAO3 = new IngresosDAO();
                    Empleado empleado3 = Sesion.getEmpleado();
                    Long idNuevo3 = IngresosDAO.countClientes() + 1;
                    Ingresos ingresos3 = new Ingresos();
                    Sesion.setIngresos(ingresos3);

                    ingresos3.setId(idNuevo3);
                    ingresos3.setNombre("Renovacion de matricula");
                    ingresos3.setDescripcion("Se ha realizado una renovación de matricula para la persona " + Sesion.getCliente().getNombre() + " " + Sesion.getCliente().getApellidos());
                    ingresos3.setTipopago(cbTipoPago.getValue());
                    ingresos3.setGrupo("Gym");
                    ingresos3.setDinero(85.00);
                    ingresos3.setFecha(LocalDate.now());
                    ingresos3.setEmpleado(empleado3.getNombre());
                    ingresosDAO3.save(ingresos3);
                    break;
                case "6 meses":
                    fechaFin = fechaInicio.plusMonths(6);
                    IngresosDAO ingresosDAO4 = new IngresosDAO();
                    Empleado empleado4 = Sesion.getEmpleado();
                    Long idNuevo4 = IngresosDAO.countClientes() + 1;
                    Ingresos ingresos4 = new Ingresos();
                    Sesion.setIngresos(ingresos4);

                    ingresos4.setId(idNuevo4);
                    ingresos4.setNombre("Renovacion de matricula");
                    ingresos4.setDescripcion("Se ha realizado una renovación de matricula para la persona " + Sesion.getCliente().getNombre() + " " + Sesion.getCliente().getApellidos());
                    ingresos4.setTipopago(cbTipoPago.getValue());
                    ingresos4.setGrupo("Gym");
                    ingresos4.setDinero(160.00);
                    ingresos4.setFecha(LocalDate.now());
                    ingresos4.setEmpleado(empleado4.getNombre());
                    ingresosDAO4.save(ingresos4);
                    break;
                case "1 año":
                    fechaFin = fechaInicio.plusYears(1);
                    IngresosDAO ingresosDAO5 = new IngresosDAO();
                    Empleado empleado5 = Sesion.getEmpleado();
                    Long idNuevo5 = IngresosDAO.countClientes() + 1;
                    Ingresos ingresos5 = new Ingresos();
                    Sesion.setIngresos(ingresos5);

                    ingresos5.setId(idNuevo5);
                    ingresos5.setNombre("Renovacion de matricula");
                    ingresos5.setDescripcion("Se ha realizado una renovación de matricula para la persona " + Sesion.getCliente().getNombre() + " " + Sesion.getCliente().getApellidos());
                    ingresos5.setTipopago(cbTipoPago.getValue());
                    ingresos5.setGrupo("Gym");
                    ingresos5.setDinero(300.00);
                    ingresos5.setFecha(LocalDate.now());
                    ingresos5.setEmpleado(empleado5.getNombre());
                    ingresosDAO5.save(ingresos5);
                    break;
                default:
                    // Manejar un valor por defecto o lanzar una excepción en caso de una opción no válida
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return fechaFin;
    }
}
