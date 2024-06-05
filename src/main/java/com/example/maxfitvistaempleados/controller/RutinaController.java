package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.ingreso.Ingresos;
import com.example.maxfitvistaempleados.pago.Pagos;
import com.example.maxfitvistaempleados.rutina.Ejercicios;
import com.example.maxfitvistaempleados.rutina.Rutina;
import com.example.maxfitvistaempleados.rutina.RutinaDAO;
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

public class RutinaController implements Initializable {
    @javafx.fxml.FXML
    private ComboBox<String> cbDia;
    @javafx.fxml.FXML
    private ComboBox<String> cbGrupo1;
    @javafx.fxml.FXML
    private TableColumn<Rutina ,String> CCDia;
    @javafx.fxml.FXML
    private TableColumn<Rutina ,String> CCGrupo;
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
    private ObservableList<Rutina> observableList;
    private RutinaDAO rutinaDAO = new RutinaDAO();
    @javafx.fxml.FXML
    private TableView<Rutina> tvRutina;
    @javafx.fxml.FXML
    private Button btnEjer;
    @javafx.fxml.FXML
    private TableColumn<Rutina ,String> CCSeries;
    @javafx.fxml.FXML
    private TableColumn<Rutina ,String> CCRepes;
    @javafx.fxml.FXML
    private TableColumn<Rutina ,String> CCEjercicio;
    @javafx.fxml.FXML
    private TableColumn<Rutina ,String> CCliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> grupoMuscular = FXCollections.observableArrayList();
        grupoMuscular.addAll("Biceps","Triceps","Hombro","Cuadriceps","Gemelo","Femoral","Lumbar","Espalda","Glúteo","Pecho","Cardio","Abdominales");
        cbGrupo1.setItems(grupoMuscular);

        ObservableList<String> dia = FXCollections.observableArrayList();
        dia.addAll("Lunes","Martes","Miércoles","Jueves","Viernes","Sábado");
        cbDia.setItems(dia);

        observableList = FXCollections.observableArrayList();

        this.CCDia.setCellValueFactory((fila) ->{
            String CCDia = String.valueOf(fila.getValue().getDia());
            return new SimpleStringProperty(CCDia);
        });
        this.CCEjercicio.setCellValueFactory((fila) -> {
            String cEjercicio = "";
            String ejercicio = String.valueOf(fila.getValue().getEjercicios().getNombre()); // Obtiene el ejercicio asociado a la rutina
            if (ejercicio != null) {
                Ejercicios ejercicios = new Ejercicios();
                cEjercicio = ejercicios.getNombre(); // Obtiene el nombre del ejercicio si no es null
            }
            return new SimpleStringProperty(ejercicio);
        });

        this.CCGrupo.setCellValueFactory((fila) -> {
            String cFecha = String.valueOf(fila.getValue().getEjercicios().getCuerpo());
            return new SimpleStringProperty(cFecha);
        });
        this.CCSeries.setCellValueFactory((fila) ->{
            String cSeries = String.valueOf(fila.getValue().getSeries());
            return new SimpleStringProperty(cSeries);
        });
        this.CCRepes.setCellValueFactory((fila) -> {
            String cRepes = String.valueOf(fila.getValue().getRepeticiones());
            return new SimpleStringProperty(cRepes);
        });
        this.CCliente.setCellValueFactory((fila) -> {
            String cliente = "";
            Clientes clientes = fila.getValue().getClientes();
            if (clientes != null) {
                cliente = clientes.getNombre();
            }
            return new SimpleStringProperty(cliente);
        });


        observableList.addAll(rutinaDAO.getAll());

        tvRutina.setItems(observableList);

        tvRutina.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Rutina rutina = tvRutina.getSelectionModel().getSelectedItem();
                if (rutina != null) {
                    Sesion.setRutina(rutina);
                    try {
                        Main.changeScene("ER-view.fxml","Editar Ejercicio");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }




    @javafx.fxml.FXML
    public void bqDia(ActionEvent actionEvent) {
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
    public void Ejercicios(ActionEvent actionEvent) throws IOException {
        Main.changeScene("Ejercicios-view.fxml","Ejercicios");
    }



}