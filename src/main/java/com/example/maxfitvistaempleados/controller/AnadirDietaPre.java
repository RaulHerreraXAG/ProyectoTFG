package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.dieta.Dieta_Pre_AnadirDAO;
import com.example.maxfitvistaempleados.dieta.Dieta_Predeterminada;
import com.example.maxfitvistaempleados.dieta.Dietas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnadirDietaPre implements Initializable {
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
    private ComboBox<String> cbTipoDieta;
    @javafx.fxml.FXML
    private Button ButtonCrear;
    @javafx.fxml.FXML
    private Button ButtonAtras;

    private Dieta_Pre_AnadirDAO dietaPreAnadirDAO = new Dieta_Pre_AnadirDAO();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> dietas = FXCollections.observableArrayList();
        dietas.addAll("Definicion","Equilibrio","Volumen");
        cbTipoDieta.setItems(dietas);

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
    public void Cliente(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Clientes");
    }

    @javafx.fxml.FXML
    public void CReceta(ActionEvent actionEvent) throws IOException {
        String tipoDieta = cbTipoDieta.getValue();
        Clientes clienteActual = Sesion.getCliente(); // Obtén el cliente actual de la sesión

        if (tipoDieta != null && clienteActual != null) {
            Long idCliente = clienteActual.getMatricula(); // Aquí asumimos que getMatricula devuelve un Long
            dietaPreAnadirDAO.addDietasToCliente(idCliente, tipoDieta);
            Main.changeScene("dietaPreXcliente-view.fxml","Dieta predeterminada");
        } else {
            // Manejo del caso cuando no hay ningún valor seleccionado en el ComboBox o cliente actual es nulo
            System.out.println("Seleccione un tipo de dieta y asegúrese de que haya un cliente en la sesión.");
        }
    }

    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Main.changeScene("dietaPreXcliente-view.fxml","Dieta predeterminada");
    }


}