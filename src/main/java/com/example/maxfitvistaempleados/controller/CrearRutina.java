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
import javafx.scene.control.*;
import javafx.util.StringConverter;

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
        ejerciciosObservableList.setAll(ejerciciosDAO.getAll());

        // Set items for all ComboBoxes
        setComboBoxItems(cbEjercicio1);
        setComboBoxItems(cbEjercicio2);
        setComboBoxItems(cbEjercicio3);
        setComboBoxItems(cbEjercicio4);
        setComboBoxItems(cbEjercicio5);
        setComboBoxItems(cbEjercicio6);
        setComboBoxItems(cbEjercicio7);
        setComboBoxItems(cbEjercicio8);
        lblDIa.setText("Lunes");


    }
    private void setComboBoxItems(ComboBox<Ejercicios> comboBox) {
        comboBox.setItems(ejerciciosObservableList);
        comboBox.setConverter(new StringConverter<Ejercicios>() {
            @Override
            public String toString(Ejercicios object) {
                if (object != null) {
                    return object.getNombre();
                } else {
                    return ""; // or any default string value you want to return when object is null
                }
            }


            @Override
            public Ejercicios fromString(String string) {
                return comboBox.getItems().stream().filter(ap ->
                        ap.getNombre().equals(string)).findFirst().orElse(null);
            }
        });
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
    public void SiguienteDia(ActionEvent actionEvent) throws IOException {


        RutinaDAO rutinaDAO = new RutinaDAO();
        Clientes cliente = Sesion.getCliente();

        // Obtener el ejercicio seleccionado en el ComboBox
        Ejercicios ejercicioSeleccionado = cbEjercicio1.getValue();
        Ejercicios ejercicioSeleccionado2 = cbEjercicio2.getValue();
        Ejercicios ejercicioSeleccionado3= cbEjercicio3.getValue();
        Ejercicios ejercicioSeleccionado4 = cbEjercicio4.getValue();
        Ejercicios ejercicioSeleccionado5 = cbEjercicio5.getValue();
        Ejercicios ejercicioSeleccionado6 = cbEjercicio6.getValue();
        Ejercicios ejercicioSeleccionado7 = cbEjercicio7.getValue();
        Ejercicios ejercicioSeleccionado8 = cbEjercicio8.getValue();



        // Verificar si se seleccionó un ejercicio
        if (ejercicioSeleccionado == null) {
            // Manejo del caso cuando no se ha seleccionado ningún ejercicio
            System.out.println("Seleccione un ejercicio.");
            return;
        }


        // Crear una nueva instancia de Rutina y establecer sus atributos
        Rutina nuevaRutina = new Rutina();
        nuevaRutina.setClientes(cliente);
        nuevaRutina.setDia(lblDIa.getText());
        nuevaRutina.setRepeticiones(Integer.valueOf(txtRepe.getText()));
        nuevaRutina.setSeries(Integer.valueOf(txtSeries1.getText()));
        nuevaRutina.setEjercicios(ejercicioSeleccionado); // Establecer el ejercicio en la rutina

        // Crear una nueva instancia de Rutina y establecer sus atributos
        Rutina nuevaRutina2 = new Rutina();
        nuevaRutina2.setClientes(cliente);
        nuevaRutina2.setDia(lblDIa.getText());
        nuevaRutina2.setRepeticiones(Integer.valueOf(txtRepe2.getText()));
        nuevaRutina2.setSeries(Integer.valueOf(txtSeries2.getText()));
        nuevaRutina2.setEjercicios(ejercicioSeleccionado2); // Establecer el ejercicio en la rutina


        // Crear una nueva instancia de Rutina y establecer sus atributos
        Rutina nuevaRutina3 = new Rutina();
        nuevaRutina3.setClientes(cliente);
        nuevaRutina3.setDia(lblDIa.getText());
        nuevaRutina3.setRepeticiones(Integer.valueOf(txtRepe3.getText()));
        nuevaRutina3.setSeries(Integer.valueOf(txtSeries3.getText()));
        nuevaRutina3.setEjercicios(ejercicioSeleccionado3); // Establecer el ejercicio en la rutina
        // Crear una nueva instancia de Rutina y establecer sus atributos
        Rutina nuevaRutina4 = new Rutina();
        nuevaRutina4.setClientes(cliente);
        nuevaRutina4.setDia(lblDIa.getText());
        nuevaRutina4.setRepeticiones(Integer.valueOf(txtRepe4.getText()));
        nuevaRutina4.setSeries(Integer.valueOf(txtSeries4.getText()));
        nuevaRutina4.setEjercicios(ejercicioSeleccionado4); // Establecer el ejercicio en la rutina
        // Crear una nueva instancia de Rutina y establecer sus atributos
        Rutina nuevaRutina5 = new Rutina();
        nuevaRutina5.setClientes(cliente);
        nuevaRutina5.setDia(lblDIa.getText());
        nuevaRutina5.setRepeticiones(Integer.valueOf(txtRepe5.getText()));
        nuevaRutina5.setSeries(Integer.valueOf(txtSeries5.getText()));
        nuevaRutina5.setEjercicios(ejercicioSeleccionado5); // Establecer el ejercicio en la rutina
        // Crear una nueva instancia de Rutina y establecer sus atributos
        Rutina nuevaRutina6 = new Rutina();
        nuevaRutina6.setClientes(cliente);
        nuevaRutina6.setDia(lblDIa.getText());
        nuevaRutina6.setRepeticiones(Integer.valueOf(txtRepe6.getText()));
        nuevaRutina6.setSeries(Integer.valueOf(txtSeries6.getText()));
        nuevaRutina6.setEjercicios(ejercicioSeleccionado6); // Establecer el ejercicio en la rutina
        // Crear una nueva instancia de Rutina y establecer sus atributos
        Rutina nuevaRutina7 = new Rutina();
        nuevaRutina7.setClientes(cliente);
        nuevaRutina7.setDia(lblDIa.getText());
        nuevaRutina7.setRepeticiones(Integer.valueOf(txtRepe7.getText()));
        nuevaRutina7.setSeries(Integer.valueOf(txtSeries7.getText()));
        nuevaRutina7.setEjercicios(ejercicioSeleccionado7); // Establecer el ejercicio en la rutina
        // Crear una nueva instancia de Rutina y establecer sus atributos
        Rutina nuevaRutina8 = new Rutina();
        nuevaRutina8.setClientes(cliente);
        nuevaRutina8.setDia(lblDIa.getText());
        nuevaRutina8.setRepeticiones(Integer.valueOf(txtRepe8.getText()));
        nuevaRutina8.setSeries(Integer.valueOf(txtSeries8.getText()));
        nuevaRutina8.setEjercicios(ejercicioSeleccionado8); // Establecer el ejercicio en la rutina

        // Guardar la nueva rutina en la base de datos
        rutinaDAO.save(nuevaRutina);
        rutinaDAO.save(nuevaRutina2);
        rutinaDAO.save(nuevaRutina3);
        rutinaDAO.save(nuevaRutina4);
        rutinaDAO.save(nuevaRutina5);
        rutinaDAO.save(nuevaRutina6);
        rutinaDAO.save(nuevaRutina7);
        rutinaDAO.save(nuevaRutina8);
        Sesion.setRutina(nuevaRutina);
        if(nuevaRutina != null && nuevaRutina2 != null && nuevaRutina3 != null && nuevaRutina4 != null){
            Main.changeScene("CR2-view.fxml", "Rutina Martes");
        }else{
            showAlert("Necesitas rellenar la primera columna","Rellena como minimo la primera columna para poder añadir el día");
        }

    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) {
    }


}