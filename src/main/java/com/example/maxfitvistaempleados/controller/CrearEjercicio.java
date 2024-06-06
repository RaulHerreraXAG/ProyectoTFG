package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.empleados.Empleado;
import com.example.maxfitvistaempleados.empleados.EmpleadoDAO;
import com.example.maxfitvistaempleados.rutina.Ejercicios;
import com.example.maxfitvistaempleados.rutina.EjerciciosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CrearEjercicio implements Initializable {
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
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextArea txtObservacion;
    @javafx.fxml.FXML
    private Button ButtonCrear;
    @javafx.fxml.FXML
    private Button ButtonAtras;
    @javafx.fxml.FXML
    private TextField txtCuerpo;
    @javafx.fxml.FXML
    private TextField txtNumeroM;

    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) throws IOException {
        Main.login("Inicio Sesión");
    }
    @javafx.fxml.FXML
    public void Cliente(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-admin.fxml","Cliente");

    }

    @javafx.fxml.FXML
    public void Pago(ActionEvent actionEvent) throws IOException {
        Main.changeScene("pagoAdmin-view.fxml","Pago");
    }

    @javafx.fxml.FXML
    public void Ingresos(ActionEvent actionEvent) throws IOException {
        Main.changeScene("ingreso-view.fxml","Ingresos");
    }

    @javafx.fxml.FXML
    public void Pagos(ActionEvent actionEvent) throws IOException {
        Main.changeScene("pago-view.fxml","Pago");
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
        Main.changeScene("Rutina-view.fxml","Rutina");
    }


    @javafx.fxml.FXML
    public void CEJercicio(ActionEvent actionEvent) throws IOException {
        EjerciciosDAO ejerciciosDAO = new EjerciciosDAO();
        Long idNuevo = (EjerciciosDAO.countEjercicios() + 1);
        Ejercicios ejercicios = new Ejercicios();

        ejercicios.setId(Math.toIntExact(idNuevo));
        ejercicios.setNombre(txtNombre.getText());
        ejercicios.setCuerpo(txtCuerpo.getText());
        ejercicios.setNumero(Integer.valueOf(txtNumeroM.getText()));
        ejercicios.setObservacion(String.valueOf(txtObservacion.getText()));
        ejerciciosDAO.save(ejercicios);
        Sesion.setEjercicios(ejercicios);

        if (ejercicios != null){
            Main.changeScene("Ejercicios-view.fxml","Ejericicios");
        }

        //Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("El ejercicio ha sido creado");
        alert.setContentText("Nombre del ejercicio: " + Sesion.getEjercicios().getNombre());
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}