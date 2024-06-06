package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.dieta.Dietas;
import com.example.maxfitvistaempleados.dieta.DietasDAO;
import com.example.maxfitvistaempleados.dieta.RecetaDAO;
import com.example.maxfitvistaempleados.dieta.Recetas;
import com.example.maxfitvistaempleados.pago.PagosDAO;
import com.example.maxfitvistaempleados.rutina.Ejercicios;
import com.example.maxfitvistaempleados.rutina.EjerciciosDAO;
import com.example.maxfitvistaempleados.rutina.Rutina;
import com.example.maxfitvistaempleados.rutina.RutinaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CrearDieta implements Initializable {
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
    private Button ButtonFD;
    @javafx.fxml.FXML
    private Button ButtonSD;
    @javafx.fxml.FXML
    private Button ButtonAtras;
    @javafx.fxml.FXML
    private Label lblDIa;
    @javafx.fxml.FXML
    private ComboBox<String> cbMD4;
    @javafx.fxml.FXML
    private ComboBox<Recetas> cbReceta4;
    @javafx.fxml.FXML
    private ComboBox<String> cbMD1;
    @javafx.fxml.FXML
    private ComboBox<Recetas> cbReceta1;
    @javafx.fxml.FXML
    private ComboBox<String> cbMD2;
    @javafx.fxml.FXML
    private ComboBox<Recetas> cbReceta2;
    @javafx.fxml.FXML
    private ComboBox<String> cbMD3;
    @javafx.fxml.FXML
    private ComboBox<Recetas> cbReceta3;
    @javafx.fxml.FXML
    private ComboBox<String> cbMD5;
    @javafx.fxml.FXML
    private ComboBox<Recetas> cbReceta5;
    @javafx.fxml.FXML
    private ComboBox<String> cbMD6;
    @javafx.fxml.FXML
    private ComboBox<Recetas> cbReceta6;
    @javafx.fxml.FXML
    private ComboBox<String> cbMD7;
    @javafx.fxml.FXML
    private ComboBox<Recetas> cbReceta7;
    @javafx.fxml.FXML
    private ComboBox<String> cbMD8;
    @javafx.fxml.FXML
    private ComboBox<Recetas> cbReceta8;

    private ObservableList<Recetas> recetasObservableList;
    public void initialize(URL location, ResourceBundle resources) {
        recetasObservableList = FXCollections.observableArrayList();
        RecetaDAO recetaDAO = new RecetaDAO();
        recetasObservableList.setAll(recetaDAO.getAll());

        // Set items for all ComboBoxes
        setComboBoxItems(cbReceta1);
        setComboBoxItems(cbReceta2);
        setComboBoxItems(cbReceta3);
        setComboBoxItems(cbReceta4);
        setComboBoxItems(cbReceta5);
        setComboBoxItems(cbReceta6);
        setComboBoxItems(cbReceta7);
        setComboBoxItems(cbReceta8);

        initializeComboBoxFilters();

        lblDIa.setText("Lunes");
    }

    private void setComboBoxItems(ComboBox<Recetas> comboBox) {
        comboBox.setItems(recetasObservableList);
        comboBox.setConverter(new StringConverter<Recetas>() {
            @Override
            public String toString(Recetas object) {
                if (object != null) {
                    return object.getNombre();
                } else {
                    return ""; // or any default string value you want to return when object is null
                }
            }


            @Override
            public Recetas fromString(String string) {
                return comboBox.getItems().stream().filter(ap ->
                        ap.getNombre().equals(string)).findFirst().orElse(null);
            }
        });
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

    @javafx.fxml.FXML
    public void FD(ActionEvent actionEvent) throws IOException {
        DietasDAO dietasDAO = new DietasDAO();
        Clientes cliente = Sesion.getCliente();
        Long idNuevo = DietasDAO.countClientes() + 1;

        // Obtener las recetas seleccionadas en los ComboBoxes
        Recetas recetasSeleccionado1 = cbReceta1.getValue();
        Recetas recetasSeleccionado2 = cbReceta2.getValue();
        Recetas recetasSeleccionado3 = cbReceta3.getValue();
        Recetas recetasSeleccionado4 = cbReceta4.getValue();
        Recetas recetasSeleccionado5 = cbReceta5.getValue();
        Recetas recetasSeleccionado6 = cbReceta6.getValue();
        Recetas recetasSeleccionado7 = cbReceta7.getValue();
        Recetas recetasSeleccionado8 = cbReceta8.getValue();

        // Verificar si se seleccionaron al menos tres recetas
        if (recetasSeleccionado1 == null || recetasSeleccionado2 == null || recetasSeleccionado3 == null) {
            showAlert("Error", "Seleccione al menos las primeras tres recetas.");
            return;
        }

        // Crear una nueva instancia de Dietas y establecer sus atributos
        Dietas dietas1 = new Dietas();
        dietas1.setClientes(cliente);
        dietas1.setDia(lblDIa.getText());
        dietas1.setMomento(cbMD1.getValue());
        dietas1.setId_dieta(idNuevo);
        dietas1.setReceta(recetasSeleccionado1);

        Dietas dietas2 = new Dietas();
        dietas2.setClientes(cliente);
        dietas2.setDia(lblDIa.getText());
        dietas2.setMomento(cbMD2.getValue());
        dietas2.setId_dieta(idNuevo);
        dietas2.setReceta(recetasSeleccionado2);

        Dietas dietas3 = new Dietas();
        dietas3.setClientes(cliente);
        dietas3.setDia(lblDIa.getText());
        dietas3.setMomento(cbMD3.getValue());
        dietas3.setId_dieta(idNuevo);
        dietas3.setReceta(recetasSeleccionado3);

        // Guardar las primeras tres dietas en la base de datos
        dietasDAO.save(dietas1);
        dietasDAO.save(dietas2);
        dietasDAO.save(dietas3);

        // Crear y guardar dietas opcionales si están completas
        if (recetasSeleccionado4 != null && cbMD4.getValue() != null) {
            Dietas dietas4 = new Dietas();
            dietas4.setClientes(cliente);
            dietas4.setDia(lblDIa.getText());
            dietas4.setMomento(cbMD4.getValue());
            dietas4.setId_dieta(idNuevo);
            dietas4.setReceta(recetasSeleccionado4);
            dietasDAO.save(dietas4);
        }

        if (recetasSeleccionado5 != null && cbMD5.getValue() != null) {
            Dietas dietas5 = new Dietas();
            dietas5.setClientes(cliente);
            dietas5.setDia(lblDIa.getText());
            dietas5.setMomento(cbMD5.getValue());
            dietas5.setId_dieta(idNuevo);
            dietas5.setReceta(recetasSeleccionado5);
            dietasDAO.save(dietas5);
        }

        if (recetasSeleccionado6 != null && cbMD6.getValue() != null) {
            Dietas dietas6 = new Dietas();
            dietas6.setClientes(cliente);
            dietas6.setDia(lblDIa.getText());
            dietas6.setMomento(cbMD6.getValue());
            dietas6.setId_dieta(idNuevo);
            dietas6.setReceta(recetasSeleccionado6);
            dietasDAO.save(dietas6);
        }

        if (recetasSeleccionado7 != null && cbMD7.getValue() != null) {
            Dietas dietas7 = new Dietas();
            dietas7.setClientes(cliente);
            dietas7.setDia(lblDIa.getText());
            dietas7.setMomento(cbMD7.getValue());
            dietas7.setId_dieta(idNuevo);
            dietas7.setReceta(recetasSeleccionado7);
            dietasDAO.save(dietas7);
        }

        if (recetasSeleccionado8 != null && cbMD8.getValue() != null) {
            Dietas dietas8 = new Dietas();
            dietas8.setClientes(cliente);
            dietas8.setDia(lblDIa.getText());
            dietas8.setMomento(cbMD8.getValue());
            dietas8.setId_dieta(idNuevo);
            dietas8.setReceta(recetasSeleccionado8);
            dietasDAO.save(dietas8);
        }

        // Guardar la dieta en la sesión
        Sesion.setDietas(dietas1);

        // Cambiar a la siguiente escena
        Main.changeScene("dietaXcliente-view.fxml", "Dietas");
    }

    @javafx.fxml.FXML
    public void SiguienteDia(ActionEvent actionEvent) throws IOException {
        DietasDAO dietasDAO = new DietasDAO();
        Clientes cliente = Sesion.getCliente();
        Long idNuevo = DietasDAO.countClientes() + 1;

        // Obtener las recetas seleccionadas en los ComboBoxes
        Recetas recetasSeleccionado1 = cbReceta1.getValue();
        Recetas recetasSeleccionado2 = cbReceta2.getValue();
        Recetas recetasSeleccionado3 = cbReceta3.getValue();
        Recetas recetasSeleccionado4 = cbReceta4.getValue();
        Recetas recetasSeleccionado5 = cbReceta5.getValue();
        Recetas recetasSeleccionado6 = cbReceta6.getValue();
        Recetas recetasSeleccionado7 = cbReceta7.getValue();
        Recetas recetasSeleccionado8 = cbReceta8.getValue();

        // Verificar si se seleccionaron al menos tres recetas
        if (recetasSeleccionado1 == null || recetasSeleccionado2 == null || recetasSeleccionado3 == null) {
            showAlert("Error", "Seleccione al menos las primeras tres recetas.");
            return;
        }

        // Crear una nueva instancia de Dietas y establecer sus atributos
        Dietas dietas1 = new Dietas();
        dietas1.setClientes(cliente);
        dietas1.setDia(lblDIa.getText());
        dietas1.setMomento(cbMD1.getValue());
        dietas1.setId_dieta(idNuevo);
        dietas1.setReceta(recetasSeleccionado1);

        Dietas dietas2 = new Dietas();
        dietas2.setClientes(cliente);
        dietas2.setDia(lblDIa.getText());
        dietas2.setMomento(cbMD2.getValue());
        dietas2.setId_dieta(idNuevo);
        dietas2.setReceta(recetasSeleccionado2);

        Dietas dietas3 = new Dietas();
        dietas3.setClientes(cliente);
        dietas3.setDia(lblDIa.getText());
        dietas3.setMomento(cbMD3.getValue());
        dietas3.setId_dieta(idNuevo);
        dietas3.setReceta(recetasSeleccionado3);

        // Guardar las primeras tres dietas en la base de datos
        dietasDAO.save(dietas1);
        dietasDAO.save(dietas2);
        dietasDAO.save(dietas3);

        // Crear y guardar dietas opcionales si están completas
        if (recetasSeleccionado4 != null && cbMD4.getValue() != null) {
            Dietas dietas4 = new Dietas();
            dietas4.setClientes(cliente);
            dietas4.setDia(lblDIa.getText());
            dietas4.setMomento(cbMD4.getValue());
            dietas4.setId_dieta(idNuevo);
            dietas4.setReceta(recetasSeleccionado4);
            dietasDAO.save(dietas4);
        }

        if (recetasSeleccionado5 != null && cbMD5.getValue() != null) {
            Dietas dietas5 = new Dietas();
            dietas5.setClientes(cliente);
            dietas5.setDia(lblDIa.getText());
            dietas5.setMomento(cbMD5.getValue());
            dietas5.setId_dieta(idNuevo);
            dietas5.setReceta(recetasSeleccionado5);
            dietasDAO.save(dietas5);
        }

        if (recetasSeleccionado6 != null && cbMD6.getValue() != null) {
            Dietas dietas6 = new Dietas();
            dietas6.setClientes(cliente);
            dietas6.setDia(lblDIa.getText());
            dietas6.setMomento(cbMD6.getValue());
            dietas6.setId_dieta(idNuevo);
            dietas6.setReceta(recetasSeleccionado6);
            dietasDAO.save(dietas6);
        }

        if (recetasSeleccionado7 != null && cbMD7.getValue() != null) {
            Dietas dietas7 = new Dietas();
            dietas7.setClientes(cliente);
            dietas7.setDia(lblDIa.getText());
            dietas7.setMomento(cbMD7.getValue());
            dietas7.setId_dieta(idNuevo);
            dietas7.setReceta(recetasSeleccionado7);
            dietasDAO.save(dietas7);
        }

        if (recetasSeleccionado8 != null && cbMD8.getValue() != null) {
            Dietas dietas8 = new Dietas();
            dietas8.setClientes(cliente);
            dietas8.setDia(lblDIa.getText());
            dietas8.setMomento(cbMD8.getValue());
            dietas8.setId_dieta(idNuevo);
            dietas8.setReceta(recetasSeleccionado8);
            dietasDAO.save(dietas8);
        }

        // Guardar la dieta en la sesión
        Sesion.setDietas(dietas1);

        // Cambiar a la siguiente escena
        Main.changeScene("CD2-view.fxml", "Crear Dietas Martes");
    }



    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) {
    }
    private void initializeComboBoxFilters() {
        // Configurar las opciones para los ComboBox de momento del día
        ObservableList<String> momentosDia = FXCollections.observableArrayList("Desayuno", "Almuerzo", "Cena");
        cbMD1.setItems(momentosDia);
        cbMD2.setItems(momentosDia);
        cbMD3.setItems(momentosDia);
        cbMD4.setItems(momentosDia);
        cbMD5.setItems(momentosDia);
        cbMD6.setItems(momentosDia);
        cbMD7.setItems(momentosDia);
        cbMD8.setItems(momentosDia);

        // Agregar listeners para filtrar las recetas cuando se selecciona un momento del día
        cbMD1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterRecetasByTipoComida(newValue, cbReceta1);
            }
        });
        cbMD2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterRecetasByTipoComida(newValue, cbReceta2);
            }
        });
        cbMD3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterRecetasByTipoComida(newValue, cbReceta3);
            }
        });
        cbMD4.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterRecetasByTipoComida(newValue, cbReceta4);
            }
        });
        cbMD5.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterRecetasByTipoComida(newValue, cbReceta5);
            }
        });
        cbMD6.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterRecetasByTipoComida(newValue, cbReceta6);
            }
        });
        cbMD7.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterRecetasByTipoComida(newValue, cbReceta7);
            }
        });
        cbMD8.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterRecetasByTipoComida(newValue, cbReceta8);
            }
        });
    }

    private void filterRecetasByTipoComida(String tipoComida, ComboBox<Recetas> comboBox) {
        ObservableList<Recetas> recetasFiltradas = FXCollections.observableArrayList();
        for (Recetas receta : recetasObservableList) {
            if (receta.getMomento_dia().equals(tipoComida)) {
                recetasFiltradas.add(receta);
            }
        }
        comboBox.setItems(recetasFiltradas);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @javafx.fxml.FXML
    public void Cliente(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-admin.fxml","Cliente");

    }

    @javafx.fxml.FXML
    public void Pago(ActionEvent actionEvent) throws IOException {
        Main.changeScene("pagoAdmin-view.fxml","Pago");
    }
}