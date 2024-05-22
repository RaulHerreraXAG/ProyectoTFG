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

public class CrearRutina implements Initializable {
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
        ejerciciosObservableList.setAll(ejerciciosDAO.getAllNames());
        cbEjercicio1.setItems(ejerciciosObservableList);
        cbEjercicio2.setItems(ejerciciosObservableList);
        cbEjercicio3.setItems(ejerciciosObservableList);
        cbEjercicio4.setItems(ejerciciosObservableList);
        cbEjercicio5.setItems(ejerciciosObservableList);
        cbEjercicio6.setItems(ejerciciosObservableList);
        cbEjercicio7.setItems(ejerciciosObservableList);
        cbEjercicio8.setItems(ejerciciosObservableList);
        lblDIa.setText("Lunes");


    }

    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void Clientes(ActionEvent actionEvent) {
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
    public void FR(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void SiguienteDia(ActionEvent actionEvent) throws IOException {
        RutinaDAO rutinaDAO = new RutinaDAO();
        Ejercicios ejercicios = new Ejercicios();
        Long idNuevo = RutinaDAO.countClientes() + 1;
        Clientes clientes = Sesion.getCliente();

        Rutina newrutina = new Rutina();
        Ejercicios ejercicios1 = new Ejercicios();

        newrutina.setId(Math.toIntExact(idNuevo));
        ejercicios1.setNombre(String.valueOf(cbEjercicio1.getValue()));
        newrutina.setRepeticiones(Integer.valueOf(txtRepe.getText()));
        newrutina.setSeries(Integer.valueOf(txtSeries1.getText()));
        newrutina.setClientes(clientes);
        newrutina.setDia(lblDIa.getText());
        rutinaDAO.save(newrutina);
        Sesion.setRutina(newrutina);

        if (newrutina != null){
            Main.changeScene("RutinaXcliente-view.fxml","Rutina");
        }

    }

    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) {
    }


}