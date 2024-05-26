package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.rutina.Ejercicios;
import com.example.maxfitvistaempleados.rutina.EjerciciosDAO;
import com.example.maxfitvistaempleados.rutina.Rutina;
import com.example.maxfitvistaempleados.rutina.RutinaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CrearRutinaViernes implements Initializable {
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
    private Button ButtonFR;
    @javafx.fxml.FXML
    private Button ButtonSD;
    @javafx.fxml.FXML
    private Button ButtonAtras;
    @javafx.fxml.FXML
    private Label lblDIa;
    @javafx.fxml.FXML
    private ComboBox<Ejercicios> cbEjercicio4;
    @javafx.fxml.FXML
    private TextField txtRepe4;
    @javafx.fxml.FXML
    private TextField txtSeries4;
    @javafx.fxml.FXML
    private ComboBox<Ejercicios> cbEjercicio1;
    @javafx.fxml.FXML
    private TextField txtRepe;
    @javafx.fxml.FXML
    private TextField txtSeries1;
    @javafx.fxml.FXML
    private ComboBox<Ejercicios> cbEjercicio2;
    @javafx.fxml.FXML
    private TextField txtRepe2;
    @javafx.fxml.FXML
    private TextField txtSeries2;
    @javafx.fxml.FXML
    private ComboBox<Ejercicios> cbEjercicio3;
    @javafx.fxml.FXML
    private TextField txtRepe3;
    @javafx.fxml.FXML
    private TextField txtSeries3;
    @javafx.fxml.FXML
    private ComboBox<Ejercicios> cbEjercicio5;
    @javafx.fxml.FXML
    private TextField txtRepe5;
    @javafx.fxml.FXML
    private TextField txtSeries5;
    @javafx.fxml.FXML
    private ComboBox<Ejercicios> cbEjercicio6;
    @javafx.fxml.FXML
    private TextField txtRepe6;
    @javafx.fxml.FXML
    private TextField txtSeries6;
    @javafx.fxml.FXML
    private ComboBox<Ejercicios> cbEjercicio7;
    @javafx.fxml.FXML
    private TextField txtRepe7;
    @javafx.fxml.FXML
    private TextField txtSeries7;
    @javafx.fxml.FXML
    private ComboBox<Ejercicios> cbEjercicio8;
    @javafx.fxml.FXML
    private TextField txtRepe8;
    @javafx.fxml.FXML
    private TextField txtSeries8;

    private ObservableList<Ejercicios> ejerciciosObservableList;
    private ObservableList<Rutina> rutinaObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ejerciciosObservableList = FXCollections.observableArrayList();
        EjerciciosDAO ejerciciosDAO = new EjerciciosDAO();
        ejerciciosObservableList.setAll(ejerciciosDAO.getAll());
        cbEjercicio1.setItems(ejerciciosObservableList);
        cbEjercicio2.setItems(ejerciciosObservableList);
        cbEjercicio3.setItems(ejerciciosObservableList);
        cbEjercicio4.setItems(ejerciciosObservableList);
        cbEjercicio5.setItems(ejerciciosObservableList);
        cbEjercicio6.setItems(ejerciciosObservableList);
        cbEjercicio7.setItems(ejerciciosObservableList);
        cbEjercicio8.setItems(ejerciciosObservableList);
        lblDIa.setText("Viernes");


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
    public void Pago(ActionEvent actionEvent) throws IOException {
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
    public void FR(ActionEvent actionEvent) throws IOException {
        RutinaDAO rutinaDAO = new RutinaDAO();
        Clientes cliente = Sesion.getCliente();

        // Obtener el ejercicio seleccionado en el ComboBox
        Ejercicios ejercicioSeleccionado = cbEjercicio1.getValue();

// Verificar si se seleccionó un ejercicio
        if (ejercicioSeleccionado == null) {
            // Manejo del caso cuando no se ha seleccionado ningún ejercicio
            System.out.println("Seleccione un ejercicio.");
            return;
        }

// Obtener el ID del ejercicio seleccionado
        Integer idEjercicio = ejercicioSeleccionado.getId();




        // Crear una nueva instancia de Rutina y establecer sus atributos
        Rutina nuevaRutina = new Rutina();
        nuevaRutina.setClientes(cliente);
        nuevaRutina.setDia(lblDIa.getText());
        nuevaRutina.setRepeticiones(Integer.valueOf(txtRepe.getText()));
        nuevaRutina.setSeries(Integer.valueOf(txtSeries1.getText()));
        nuevaRutina.setEjercicios(ejercicioSeleccionado); // Establecer el ejercicio en la rutina

        // Guardar la nueva rutina en la base de datos
        rutinaDAO.save(nuevaRutina);
        Sesion.setRutina(nuevaRutina);

        // Cambiar a la siguiente escena
        Main.changeScene("RutinaXcliente-view.fxml", "Rutina");
    }


    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) {
    }


}