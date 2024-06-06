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
    private Button btnVolver;

    private ObservableList<Ejercicios> observableList;

    private EjerciciosDAO ejerciciosDAO = new EjerciciosDAO();




    @javafx.fxml.FXML
    public void bqEjercicio(ActionEvent actionEvent) {
        // Obtener el nombre del ejercicio ingresado por el usuario desde un TextField
        String nombreEjercicio = txtEjercicio.getText().toLowerCase(); // Convertir a minúsculas para una comparación sin distinción de mayúsculas/minúsculas

        // Verificar si el campo de búsqueda está vacío
        if (nombreEjercicio.isEmpty()) {
            // Si está vacío, mostrar toda la información de la tabla nuevamente
            tvEjercicio.setItems(observableList);
        } else {
            // Filtrar la lista de ejercicios por el nombre ingresado (con aproximación)
            ObservableList<Ejercicios> resultados = FXCollections.observableArrayList();
            for (Ejercicios ejercicio : observableList) {
                // Obtener el nombre del ejercicio en minúsculas para una comparación sin distinción de mayúsculas/minúsculas
                String nombreEjercicioLowerCase = ejercicio.getNombre().toLowerCase();
                // Verificar si el nombre del ejercicio contiene la cadena ingresada por el usuario
                if (nombreEjercicioLowerCase.contains(nombreEjercicio)) {
                    resultados.add(ejercicio);
                }
            }

            // Actualizar la tabla con los resultados de la búsqueda
            tvEjercicio.setItems(resultados);
        }
    }

    @javafx.fxml.FXML
    public void bqNum(ActionEvent actionEvent) {
        // Obtener el número del ejercicio ingresado por el usuario desde un TextField
        String numEjercicio = txtNum.getText();

        // Verificar si el campo de búsqueda está vacío
        if (numEjercicio.isEmpty()) {
            // Si está vacío, mostrar toda la información de la tabla nuevamente
            tvEjercicio.setItems(observableList);
        } else {
            // Filtrar la lista de ejercicios por el número ingresado
            ObservableList<Ejercicios> resultados = FXCollections.observableArrayList();
            for (Ejercicios ejercicio : observableList) {
                // Obtener el número del ejercicio y convertirlo a String para comparar
                String numEjercicioString = String.valueOf(ejercicio.getNumero());
                // Verificar si el número del ejercicio coincide con el número ingresado por el usuario
                if (numEjercicioString.equals(numEjercicio)) {
                    resultados.add(ejercicio);
                }
            }

            // Actualizar la tabla con los resultados de la búsqueda
            tvEjercicio.setItems(resultados);
        }
    }

    @javafx.fxml.FXML
    public void bqGrupo(ActionEvent actionEvent) {
        // Obtener el grupo muscular seleccionado en el ComboBox
        String grupoMuscularSeleccionado = cbGrupo1.getValue();

        // Verificar si se ha seleccionado un grupo muscular
        if (grupoMuscularSeleccionado != null) {
            // Filtrar la lista de ejercicios por el grupo muscular seleccionado
            ObservableList<Ejercicios> resultados = FXCollections.observableArrayList();
            for (Ejercicios ejercicio : observableList) {
                // Obtener el grupo muscular del ejercicio y verificar si coincide con el grupo seleccionado
                if (ejercicio.getCuerpo().equals(grupoMuscularSeleccionado)) {
                    resultados.add(ejercicio);
                }
            }

            // Actualizar la tabla con los resultados de la búsqueda
            tvEjercicio.setItems(resultados);
        } else {
            // Si no se ha seleccionado ningún grupo muscular, mostrar toda la información de la tabla nuevamente
            tvEjercicio.setItems(observableList);
        }
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
    public void CrearEjerc(ActionEvent actionEvent) throws IOException {
        Main.changeScene("C-E-view.fxml","Crear Ejercicio");
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
                        Main.changeScene("ER-view.fxml","Editar Ejercicios");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

}