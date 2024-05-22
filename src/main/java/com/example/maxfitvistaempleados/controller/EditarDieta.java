package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.ClienteDAOImp;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.dieta.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditarDieta implements Initializable {
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
    private TextField txtNombre;
    @javafx.fxml.FXML
    private Button ButtonCrear;
    @javafx.fxml.FXML
    private Button ButtonAtras;
    @javafx.fxml.FXML
    private Button ButtonEliminar;
    @javafx.fxml.FXML
    private ComboBox<String> cbDia;
    @javafx.fxml.FXML
    private TextField txtTiempo;
    @javafx.fxml.FXML
    private TextField txtKcal;
    @javafx.fxml.FXML
    private TextArea txtPasos;

    private DietasDAO dietasDAO = new DietasDAO();
    @javafx.fxml.FXML
    private TableView<RecetasAlimentos> tvAlimentos;
    @javafx.fxml.FXML
    private TableColumn<RecetasAlimentos , String> CCAlimentos;
    @javafx.fxml.FXML
    private TableColumn<RecetasAlimentos , String> CCCantidad;

    private ObservableList<RecetasAlimentos> observableList;

    private RecetasDAOAlimentos recetasDAOAlimentos = new RecetasDAOAlimentos();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> dias = FXCollections.observableArrayList();
        dias.addAll("Lunes","Martes","Miercoles","Jueves","Viernes","Sábado","Domingo");
        cbDia.setItems(dias);


        txtNombre.setText(Sesion.getDietas().getReceta().getNombre());
        txtKcal.setText(String.valueOf(Sesion.getDietas().getKcal()));
        txtPasos.setText(Sesion.getDietas().getReceta().getPasos());
        txtTiempo.setText(String.valueOf(Sesion.getDietas().getReceta().getTiempoPreparacion()));
        cbDia.setValue(Sesion.getDietas().getDia());

        observableList = FXCollections.observableArrayList();

        Recetas recetas = Sesion.getRecetas();
        List<RecetasAlimentos> recetasList = recetasDAOAlimentos.getByReceta(recetas);
        observableList.addAll(recetasList);

        this.CCAlimentos.setCellValueFactory((fila)->{
            String CCAlimentos = String.valueOf(fila.getValue().getAlimento().getNombre());
            return new SimpleStringProperty(CCAlimentos);
        });
        this.CCCantidad.setCellValueFactory((fila)->{
            String CCAlimentos = String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(CCAlimentos);
        });




        tvAlimentos.setItems(observableList);

    }

    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) {
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
    public void VolverAtras(ActionEvent actionEvent) {
    }



    @javafx.fxml.FXML
    public void CReceta(ActionEvent actionEvent) throws IOException {
        DietasDAO dietasDAO1 = new DietasDAO();
        RecetaDAO recetaDAO = new RecetaDAO();
        ClienteDAOImp clientesDAO = new ClienteDAOImp();  // Asegúrate de tener este DAO para Clientes

        Dietas dietas = new Dietas();


        // Obtener el cliente actual de la sesión
        Clientes clientes = Sesion.getCliente();
        Recetas recetas = Sesion.getDietas().getReceta();
        Long recetaID = Sesion.getDietas().getReceta().getIdReceta();

        // Verificar si el cliente ya está guardado (tiene un ID asignado)
        if (clientes.getMatricula() == null) {
            // Guardar el cliente primero
            clientesDAO.save(clientes);
        }

        dietas.setId_dieta(Sesion.getDietas().getId_dieta());
        dietas.setClientes(clientes);  // Asignar el cliente guardado a la dieta
        dietas.setKcal(Sesion.getDietas().getKcal());
        dietas.setCarbohidratos(Sesion.getDietas().getCarbohidratos());
        dietas.setDia(cbDia.getValue());
        dietas.setGrasas(Sesion.getDietas().getGrasas());
        dietas.setCarbohidratos(Sesion.getDietas().getCarbohidratos());
        dietas.setReceta(recetas);

        System.out.println(recetas.getIdReceta());
        System.out.println(recetas.getNombre());

        recetas.setPasos(txtPasos.getText());
        recetas.setTiempoPreparacion(Integer.valueOf(txtTiempo.getText()));
        recetas.setNombre(txtNombre.getText());
        recetas.setKcalTotal(Sesion.getDietas().getReceta().getKcalTotal());
        recetas.setCarbohidratosTotal(Sesion.getDietas().getReceta().getCarbohidratosTotal());
        recetas.setGrasasTotal(Sesion.getDietas().getReceta().getGrasasTotal());

        dietasDAO1.update(dietas);
        recetaDAO.update(recetas);



        Main.changeScene("dietaXcliente-view.fxml","Dieta del Cliente");

        // Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("El cliente ha sido actualizado");
        alert.setContentText("Nombre del Cliente: " + Sesion.getCliente().getNombre() + " realizado por " +  Sesion.getEmpleado().getNombre() + " " + Sesion.getEmpleado().getApellidos());
        alert.showAndWait();
    }


    @javafx.fxml.FXML
    public void EReceta(ActionEvent actionEvent) {
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}