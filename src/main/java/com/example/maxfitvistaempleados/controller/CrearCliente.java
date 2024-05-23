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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CrearCliente implements Initializable {
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
    private ComboBox cbFinFecha;
    @javafx.fxml.FXML
    private TextField txtCorreo;


    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void Ingresos(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void Pagos(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void Dietas(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void Rutina(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void CCliente(ActionEvent actionEvent) throws IOException {
        ClienteDAOImp clienteDAOImp = new ClienteDAOImp();
        Long idNuevo = ClienteDAOImp.countClientes() + 1;
        String contrasenyapredeterminada = "Maxfit2024";
        Clientes cliente = new Clientes();

        cliente.setMatricula(idNuevo);
        cliente.setNombre(txtNombre.getText());
        cliente.setApellidos(txtApelldos.getText());
        cliente.setCorreo(txtCorreo.getText());
        cliente.setAltura(Double.valueOf(txtAltura.getText()));
        cliente.setEdad(Integer.parseInt(txtEdad.getText()));
        cliente.setContrasena(contrasenyapredeterminada);
        cliente.setGenero(cbGenero.getValue());
        cliente.setPeso(Double.valueOf(txtPeso.getText()));
        cliente.setObservacion(txtObservacion.getText());
        cliente.setFechaInicio(LocalDate.now());
        // Obtener la duración seleccionada por el usuario
        String duracionSeleccionada = cbFinFecha.getValue().toString();
        LocalDate fechaFin = calcularFechaFin(duracionSeleccionada);
        cliente.setFechaFin(fechaFin);

        clienteDAOImp.save(cliente);
        Sesion.setCliente(cliente);





        if (cliente != null){
            Main.changeScene("view-empleado.fxml","Cliente");
        }

        //Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("El cliente ha sido creado");
        alert.setContentText("Nombre del Cliente: " + Sesion.getCliente().getNombre() + " realizado por " +  Sesion.getEmpleado().getNombre() + " "+ Sesion.getEmpleado().getApellidos());
        alert.showAndWait();

    }

    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Cliente");
    }
    public void Clientes(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Clientes");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> meses = FXCollections.observableArrayList();
        meses.addAll("1 mes","2 meses","3 meses","6 meses","1 año");
        cbFinFecha.setItems(meses);

        ObservableList<String> genero = FXCollections.observableArrayList();
        genero.addAll("Hombre", "Mujer");
        cbGenero.setItems(genero);


    }

    // Método para calcular la fecha de finalización basada en la duración seleccionada
    private LocalDate calcularFechaFin(String duracionSeleccionada) {
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = null;

        switch (duracionSeleccionada) {
            case "1 mes":
                fechaFin = fechaInicio.plusMonths(1);
                break;
            case "2 meses":
                fechaFin = fechaInicio.plusMonths(2);
                break;
            case "3 meses":
                fechaFin = fechaInicio.plusMonths(3);
                break;
            case "6 meses":
                fechaFin = fechaInicio.plusMonths(6);
                break;
            case "1 año":
                fechaFin = fechaInicio.plusYears(1);
                break;
            default:
                // Manejar un valor por defecto o lanzar una excepción en caso de una opción no válida
                break;
        }

        return fechaFin;
    }
}