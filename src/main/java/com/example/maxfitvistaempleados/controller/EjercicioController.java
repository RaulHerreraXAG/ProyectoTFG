package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.ingreso.Ingresos;
import com.example.maxfitvistaempleados.rutina.Ejercicios;
import com.example.maxfitvistaempleados.rutina.EjerciciosDAO;
import com.example.maxfitvistaempleados.rutina.Rutina;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EjercicioController  implements Initializable {
    Ejercicios ejercicios = new Ejercicios();
    @javafx.fxml.FXML
    private TextField txtEjercicio;
    @javafx.fxml.FXML
    private TextField txtNum;
    @javafx.fxml.FXML
    private ComboBox<String> cbGrupo1;
    @javafx.fxml.FXML
    private TableView<Ejercicios> tvEjercicio;
    @javafx.fxml.FXML
    private TableColumn<Ejercicios , String> CCID;
    @javafx.fxml.FXML
    private TableColumn<Ejercicios , String> CCEjercicio;
    @javafx.fxml.FXML
    private TableColumn<Ejercicios , String> CCGrupo;
    @javafx.fxml.FXML
    private TableColumn<Ejercicios , String> CCNumero;
    @javafx.fxml.FXML
    private TableColumn<Ejercicios , String>CCDescripcion;
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
    private Button btnEj;
    @javafx.fxml.FXML
    private Button btnBI;
    @javafx.fxml.FXML
    private Button btnVolver;

    private ObservableList<Ejercicios> observableList;

    private EjerciciosDAO ejerciciosDAO = new EjerciciosDAO();




    @javafx.fxml.FXML
    public void bqEjercicio(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void bqNum(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void bqGrupo(ActionEvent actionEvent) {
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
    public void CrearEjerc(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void BorrarEjer(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void Volver(ActionEvent actionEvent) throws IOException {
        Main.changeScene("Rutina-view.fxml","Rutina");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> grupoMuscular = FXCollections.observableArrayList();
        grupoMuscular.addAll("Biceps","Triceps","Hombro","Cuadriceps","Gemelo","Femoral","Lumbar","Espalda","Glúteo","Pecho","Cardio","Abdominales");
        cbGrupo1.setItems(grupoMuscular);

        observableList = FXCollections.observableArrayList();

        this.CCID.setCellValueFactory((fila) ->{
            String CCID = String.valueOf(fila.getValue().getId());
            return new SimpleStringProperty(CCID);
        });
        this.CCEjercicio.setCellValueFactory((fila) ->{
            String CCEjercicio = String.valueOf(fila.getValue().getNombre());
            return new SimpleStringProperty(CCEjercicio);
        });
        this.CCGrupo.setCellValueFactory((fila) -> {
            String CCGrupo = String.valueOf(fila.getValue().getCuerpo());
            return new SimpleStringProperty(CCGrupo);
        });
        this.CCNumero.setCellValueFactory((fila) -> {
            String CCNumero = String.valueOf(fila.getValue().getNumero());
            return new SimpleStringProperty(CCNumero);
        });
        this.CCDescripcion.setCellValueFactory((fila) -> {
            String CCDescripcion = String.valueOf(fila.getValue().getObservacion());
            return new SimpleStringProperty(CCDescripcion);
        });

        observableList.addAll(ejerciciosDAO.getAll());

        tvEjercicio.setItems(observableList);

        tvEjercicio.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Ejercicios ejercicios = tvEjercicio.getSelectionModel().getSelectedItem();
                if (ejercicios != null) {
                    Sesion.setEjercicios(ejercicios);
                    try {
                        Main.changeScene("EE-view.fxml","Editar Ejercicios");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


}