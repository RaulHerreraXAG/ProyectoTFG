package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.dieta.Alimentos;
import com.example.maxfitvistaempleados.dieta.AlimentosDAO;
import com.example.maxfitvistaempleados.dieta.Recetas;
import com.example.maxfitvistaempleados.dieta.RecetasAlimentos;
import com.example.maxfitvistaempleados.rutina.Rutina;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AlimentoController implements Initializable {
    @javafx.fxml.FXML
    private ComboBox cbDia;
    @javafx.fxml.FXML
    private ComboBox cbNombreReceta;
    @javafx.fxml.FXML
    private TableView<RecetasAlimentos> tvAlimento;
    @javafx.fxml.FXML
    private TableColumn<RecetasAlimentos, String> CCReceta;
    @javafx.fxml.FXML
    private TableColumn<RecetasAlimentos, String> CCNombre;
    @javafx.fxml.FXML
    private TableColumn<RecetasAlimentos, String> CCantidad;
    @javafx.fxml.FXML
    private TableColumn<RecetasAlimentos, String> CCarbo;
    @javafx.fxml.FXML
    private TableColumn<RecetasAlimentos, String> CCGrasas;
    @javafx.fxml.FXML
    private TableColumn<RecetasAlimentos, String> CCKcal;
    @javafx.fxml.FXML
    private Button btnCerrarSesion;
    @javafx.fxml.FXML
    private Button btnCliente;
    @javafx.fxml.FXML
    private Button BtnIngresos;
    @javafx.fxml.FXML
    private Button btnPago;
    @javafx.fxml.FXML
    private Button btnDietas;
    @javafx.fxml.FXML
    private Button btnRutina;
    @javafx.fxml.FXML
    private Button btnAtras;
    @javafx.fxml.FXML
    private Button btnAA;

    private ObservableList<RecetasAlimentos> observableList;
    private AlimentosDAO alimentosDAO = new AlimentosDAO();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        observableList = FXCollections.observableArrayList();

        this.CCantidad.setCellValueFactory((fila) ->{
            String CCantidad = String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(CCantidad);
        });
        this.CCarbo.setCellValueFactory((fila) ->{
            String CCarbo = String.valueOf(fila.getValue().getAlimento().getKcalXCarbohidrato());
            return new SimpleStringProperty(CCarbo);
        });
        this.CCKcal.setCellValueFactory((fila) ->{
            String CCKcal = String.valueOf(fila.getValue().getAlimento().getKcalXGramo());
            return new SimpleStringProperty(CCKcal);
        });
        this.CCGrasas.setCellValueFactory((fila) ->{
            String CCGrasas = String.valueOf(fila.getValue().getAlimento().getKcalXGrasa());
            return new SimpleStringProperty(CCGrasas);
        });
        this.CCReceta.setCellValueFactory((fila) ->{
            String CCReceta = String.valueOf(fila.getValue().getReceta().getNombre());
            return new SimpleStringProperty(CCReceta);
        });
        this.CCNombre.setCellValueFactory((fila) ->{
            String CCNombre = String.valueOf(fila.getValue().getAlimento().getNombre());
            return new SimpleStringProperty(CCNombre);
        });
    }



    @javafx.fxml.FXML
    public void bqDia(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void bqReceta(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void Cliente(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void Ingresos(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void Pago(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void Dietas(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void Rutina(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void AnadirAlimentos(ActionEvent actionEvent) {
    }


}