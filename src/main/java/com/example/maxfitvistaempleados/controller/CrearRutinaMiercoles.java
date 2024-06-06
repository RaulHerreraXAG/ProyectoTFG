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

public class CrearRutinaMiercoles implements Initializable {
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
            lblDIa.setText("Miércoles");


    }

    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) throws IOException {
        Main.login("Inicio Sesión");
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

        // Obtener los ejercicios seleccionados en los ComboBoxes
        Ejercicios ejercicioSeleccionado = cbEjercicio1.getValue();
        Ejercicios ejercicioSeleccionado2 = cbEjercicio2.getValue();
        Ejercicios ejercicioSeleccionado3 = cbEjercicio3.getValue();
        Ejercicios ejercicioSeleccionado4 = cbEjercicio4.getValue();
        Ejercicios ejercicioSeleccionado5 = cbEjercicio5.getValue();
        Ejercicios ejercicioSeleccionado6 = cbEjercicio6.getValue();
        Ejercicios ejercicioSeleccionado7 = cbEjercicio7.getValue();
        Ejercicios ejercicioSeleccionado8 = cbEjercicio8.getValue();

        // Verificar si se seleccionaron los primeros cinco ejercicios
        if (ejercicioSeleccionado == null || ejercicioSeleccionado2 == null || ejercicioSeleccionado3 == null ||
                ejercicioSeleccionado4 == null || ejercicioSeleccionado5 == null) {
            showAlert("Error", "Seleccione al menos los primeros cinco ejercicios.");
            return;
        }

        // Verificar que los campos de repeticiones y series no estén vacíos para los primeros cinco ejercicios
        if (txtRepe.getText().isEmpty() || txtSeries1.getText().isEmpty() ||
                txtRepe2.getText().isEmpty() || txtSeries2.getText().isEmpty() ||
                txtRepe3.getText().isEmpty() || txtSeries3.getText().isEmpty() ||
                txtRepe4.getText().isEmpty() || txtSeries4.getText().isEmpty() ||
                txtRepe5.getText().isEmpty() || txtSeries5.getText().isEmpty()) {
            showAlert("Error", "Por favor, complete todos los campos de repeticiones y series para los primeros cinco ejercicios.");
            return;
        }

        try {
            // Crear una nueva instancia de Rutina y establecer sus atributos
            Rutina nuevaRutina1 = new Rutina();
            nuevaRutina1.setClientes(cliente);
            nuevaRutina1.setDia(lblDIa.getText());
            nuevaRutina1.setRepeticiones(Integer.valueOf(txtRepe.getText()));
            nuevaRutina1.setSeries(Integer.valueOf(txtSeries1.getText()));
            nuevaRutina1.setEjercicios(ejercicioSeleccionado);

            Rutina nuevaRutina2 = new Rutina();
            nuevaRutina2.setClientes(cliente);
            nuevaRutina2.setDia(lblDIa.getText());
            nuevaRutina2.setRepeticiones(Integer.valueOf(txtRepe2.getText()));
            nuevaRutina2.setSeries(Integer.valueOf(txtSeries2.getText()));
            nuevaRutina2.setEjercicios(ejercicioSeleccionado2);

            Rutina nuevaRutina3 = new Rutina();
            nuevaRutina3.setClientes(cliente);
            nuevaRutina3.setDia(lblDIa.getText());
            nuevaRutina3.setRepeticiones(Integer.valueOf(txtRepe3.getText()));
            nuevaRutina3.setSeries(Integer.valueOf(txtSeries3.getText()));
            nuevaRutina3.setEjercicios(ejercicioSeleccionado3);

            Rutina nuevaRutina4 = new Rutina();
            nuevaRutina4.setClientes(cliente);
            nuevaRutina4.setDia(lblDIa.getText());
            nuevaRutina4.setRepeticiones(Integer.valueOf(txtRepe4.getText()));
            nuevaRutina4.setSeries(Integer.valueOf(txtSeries4.getText()));
            nuevaRutina4.setEjercicios(ejercicioSeleccionado4);

            Rutina nuevaRutina5 = new Rutina();
            nuevaRutina5.setClientes(cliente);
            nuevaRutina5.setDia(lblDIa.getText());
            nuevaRutina5.setRepeticiones(Integer.valueOf(txtRepe5.getText()));
            nuevaRutina5.setSeries(Integer.valueOf(txtSeries5.getText()));
            nuevaRutina5.setEjercicios(ejercicioSeleccionado5);

            // Guardar la nueva rutina en la base de datos para los primeros cinco ejercicios
            rutinaDAO.save(nuevaRutina1);
            rutinaDAO.save(nuevaRutina2);
            rutinaDAO.save(nuevaRutina3);
            rutinaDAO.save(nuevaRutina4);
            rutinaDAO.save(nuevaRutina5);

            // Crear y guardar rutinas para los ejercicios opcionales si están completos
            if (!txtRepe6.getText().isEmpty() && !txtSeries6.getText().isEmpty() && ejercicioSeleccionado6 != null) {
                Rutina nuevaRutina6 = new Rutina();
                nuevaRutina6.setClientes(cliente);
                nuevaRutina6.setDia(lblDIa.getText());
                nuevaRutina6.setRepeticiones(Integer.valueOf(txtRepe6.getText()));
                nuevaRutina6.setSeries(Integer.valueOf(txtSeries6.getText()));
                nuevaRutina6.setEjercicios(ejercicioSeleccionado6);
                rutinaDAO.save(nuevaRutina6);
            }

            if (!txtRepe7.getText().isEmpty() && !txtSeries7.getText().isEmpty() && ejercicioSeleccionado7 != null) {
                Rutina nuevaRutina7 = new Rutina();
                nuevaRutina7.setClientes(cliente);
                nuevaRutina7.setDia(lblDIa.getText());
                nuevaRutina7.setRepeticiones(Integer.valueOf(txtRepe7.getText()));
                nuevaRutina7.setSeries(Integer.valueOf(txtSeries7.getText()));
                nuevaRutina7.setEjercicios(ejercicioSeleccionado7);
                rutinaDAO.save(nuevaRutina7);
            }

            if (!txtRepe8.getText().isEmpty() && !txtSeries8.getText().isEmpty() && ejercicioSeleccionado8 != null) {
                Rutina nuevaRutina8 = new Rutina();
                nuevaRutina8.setClientes(cliente);
                nuevaRutina8.setDia(lblDIa.getText());
                nuevaRutina8.setRepeticiones(Integer.valueOf(txtRepe8.getText()));
                nuevaRutina8.setSeries(Integer.valueOf(txtSeries8.getText()));
                nuevaRutina8.setEjercicios(ejercicioSeleccionado8);
                rutinaDAO.save(nuevaRutina8);
            }

            // Cambiar de escena después de guardar todas las rutinas
            Main.changeScene("RutinaXcliente-view.fxml", "Rutina");
        } catch (NumberFormatException e) {
            showAlert("Error", "Por favor, ingrese valores numéricos válidos para repeticiones y series.");
        } catch (Exception e) {
            showAlert("Error", "Ha ocurrido un error al guardar la rutina. Intente nuevamente.");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void SiguienteDia(ActionEvent actionEvent) throws IOException {
        RutinaDAO rutinaDAO = new RutinaDAO();
        Clientes cliente = Sesion.getCliente();

        // Obtener los ejercicios seleccionados en los ComboBoxes
        Ejercicios ejercicioSeleccionado = cbEjercicio1.getValue();
        Ejercicios ejercicioSeleccionado2 = cbEjercicio2.getValue();
        Ejercicios ejercicioSeleccionado3 = cbEjercicio3.getValue();
        Ejercicios ejercicioSeleccionado4 = cbEjercicio4.getValue();
        Ejercicios ejercicioSeleccionado5 = cbEjercicio5.getValue();
        Ejercicios ejercicioSeleccionado6 = cbEjercicio6.getValue();
        Ejercicios ejercicioSeleccionado7 = cbEjercicio7.getValue();
        Ejercicios ejercicioSeleccionado8 = cbEjercicio8.getValue();

        // Verificar si se seleccionaron los primeros cinco ejercicios
        if (ejercicioSeleccionado == null || ejercicioSeleccionado2 == null || ejercicioSeleccionado3 == null ||
                ejercicioSeleccionado4 == null || ejercicioSeleccionado5 == null) {
            showAlert("Error", "Seleccione al menos los primeros cinco ejercicios.");
            return;
        }

        // Verificar que los campos de repeticiones y series no estén vacíos para los primeros cinco ejercicios
        if (txtRepe.getText().isEmpty() || txtSeries1.getText().isEmpty() ||
                txtRepe2.getText().isEmpty() || txtSeries2.getText().isEmpty() ||
                txtRepe3.getText().isEmpty() || txtSeries3.getText().isEmpty() ||
                txtRepe4.getText().isEmpty() || txtSeries4.getText().isEmpty() ||
                txtRepe5.getText().isEmpty() || txtSeries5.getText().isEmpty()) {
            showAlert("Error", "Por favor, complete todos los campos de repeticiones y series para los primeros cinco ejercicios.");
            return;
        }

        try {
            // Crear una nueva instancia de Rutina y establecer sus atributos
            Rutina nuevaRutina1 = new Rutina();
            nuevaRutina1.setClientes(cliente);
            nuevaRutina1.setDia(lblDIa.getText());
            nuevaRutina1.setRepeticiones(Integer.valueOf(txtRepe.getText()));
            nuevaRutina1.setSeries(Integer.valueOf(txtSeries1.getText()));
            nuevaRutina1.setEjercicios(ejercicioSeleccionado);

            Rutina nuevaRutina2 = new Rutina();
            nuevaRutina2.setClientes(cliente);
            nuevaRutina2.setDia(lblDIa.getText());
            nuevaRutina2.setRepeticiones(Integer.valueOf(txtRepe2.getText()));
            nuevaRutina2.setSeries(Integer.valueOf(txtSeries2.getText()));
            nuevaRutina2.setEjercicios(ejercicioSeleccionado2);

            Rutina nuevaRutina3 = new Rutina();
            nuevaRutina3.setClientes(cliente);
            nuevaRutina3.setDia(lblDIa.getText());
            nuevaRutina3.setRepeticiones(Integer.valueOf(txtRepe3.getText()));
            nuevaRutina3.setSeries(Integer.valueOf(txtSeries3.getText()));
            nuevaRutina3.setEjercicios(ejercicioSeleccionado3);

            Rutina nuevaRutina4 = new Rutina();
            nuevaRutina4.setClientes(cliente);
            nuevaRutina4.setDia(lblDIa.getText());
            nuevaRutina4.setRepeticiones(Integer.valueOf(txtRepe4.getText()));
            nuevaRutina4.setSeries(Integer.valueOf(txtSeries4.getText()));
            nuevaRutina4.setEjercicios(ejercicioSeleccionado4);

            Rutina nuevaRutina5 = new Rutina();
            nuevaRutina5.setClientes(cliente);
            nuevaRutina5.setDia(lblDIa.getText());
            nuevaRutina5.setRepeticiones(Integer.valueOf(txtRepe5.getText()));
            nuevaRutina5.setSeries(Integer.valueOf(txtSeries5.getText()));
            nuevaRutina5.setEjercicios(ejercicioSeleccionado5);

            // Guardar la nueva rutina en la base de datos para los primeros cinco ejercicios
            rutinaDAO.save(nuevaRutina1);
            rutinaDAO.save(nuevaRutina2);
            rutinaDAO.save(nuevaRutina3);
            rutinaDAO.save(nuevaRutina4);
            rutinaDAO.save(nuevaRutina5);

            // Crear y guardar rutinas para los ejercicios opcionales si están completos
            if (!txtRepe6.getText().isEmpty() && !txtSeries6.getText().isEmpty() && ejercicioSeleccionado6 != null) {
                Rutina nuevaRutina6 = new Rutina();
                nuevaRutina6.setClientes(cliente);
                nuevaRutina6.setDia(lblDIa.getText());
                nuevaRutina6.setRepeticiones(Integer.valueOf(txtRepe6.getText()));
                nuevaRutina6.setSeries(Integer.valueOf(txtSeries6.getText()));
                nuevaRutina6.setEjercicios(ejercicioSeleccionado6);
                rutinaDAO.save(nuevaRutina6);
            }

            if (!txtRepe7.getText().isEmpty() && !txtSeries7.getText().isEmpty() && ejercicioSeleccionado7 != null) {
                Rutina nuevaRutina7 = new Rutina();
                nuevaRutina7.setClientes(cliente);
                nuevaRutina7.setDia(lblDIa.getText());
                nuevaRutina7.setRepeticiones(Integer.valueOf(txtRepe7.getText()));
                nuevaRutina7.setSeries(Integer.valueOf(txtSeries7.getText()));
                nuevaRutina7.setEjercicios(ejercicioSeleccionado7);
                rutinaDAO.save(nuevaRutina7);
            }

            if (!txtRepe8.getText().isEmpty() && !txtSeries8.getText().isEmpty() && ejercicioSeleccionado8 != null) {
                Rutina nuevaRutina8 = new Rutina();
                nuevaRutina8.setClientes(cliente);
                nuevaRutina8.setDia(lblDIa.getText());
                nuevaRutina8.setRepeticiones(Integer.valueOf(txtRepe8.getText()));
                nuevaRutina8.setSeries(Integer.valueOf(txtSeries8.getText()));
                nuevaRutina8.setEjercicios(ejercicioSeleccionado8);
                rutinaDAO.save(nuevaRutina8);
            }

            // Cambiar de escena después de guardar todas las rutinas
            Main.changeScene("CR4-view.fxml", "Rutina Jueves");
        } catch (NumberFormatException e) {
            showAlert("Error", "Por favor, ingrese valores numéricos válidos para repeticiones y series.");
        } catch (Exception e) {
            showAlert("Error", "Ha ocurrido un error al guardar la rutina. Intente nuevamente.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Main.changeScene("CR2-view.fxml", "Rutina Martes");
    }


}