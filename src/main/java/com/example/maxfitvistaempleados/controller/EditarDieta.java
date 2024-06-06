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

    private ObservableList<Recetas> observableList;

    private RecetaDAO recetaDAO = new RecetaDAO();
    @javafx.fxml.FXML
    private TextArea txtAlimentos;
    @javafx.fxml.FXML
    private TextField txtGrasas;
    @javafx.fxml.FXML
    private TextField txtCH;
    @javafx.fxml.FXML
    private ComboBox<String> cbMenu;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> dias = FXCollections.observableArrayList();
        dias.addAll("Lunes","Martes","Miercoles","Jueves","Viernes","Sábado","Domingo");
        cbDia.setItems(dias);
        ObservableList<String> menu = FXCollections.observableArrayList();
        menu.addAll("Desayuno","Almuerzo","Cena");
        cbMenu.setItems(menu);


        txtNombre.setText(Sesion.getDietas().getReceta().getNombre());
        txtKcal.setText(String.valueOf(Sesion.getDietas().getReceta().getKcalTotal()));
        txtPasos.setText(Sesion.getDietas().getReceta().getPasos());
        txtTiempo.setText(String.valueOf(Sesion.getDietas().getReceta().getTiempoPreparacion()));
        cbDia.setValue(Sesion.getDietas().getDia());
        cbMenu.setValue(Sesion.getDietas().getMomento());
        txtCH.setText(String.valueOf(Sesion.getDietas().getReceta().getCarbohidratosTotal()));
        txtGrasas.setText(String.valueOf(Sesion.getDietas().getReceta().getGrasasTotal()));
        // Formatear la lista de alimentos
        String alimentos = Sesion.getDietas().getReceta().getAlimentos();
        String[] alimentosArray = alimentos.split(";");
        StringBuilder alimentosFormateados = new StringBuilder();
        for (int i = 0; i < alimentosArray.length; i++) {
            alimentosFormateados.append("Alimento ").append(i + 1).append(" : ").append(alimentosArray[i].trim()).append("\n");
        }
        txtAlimentos.setText(alimentosFormateados.toString());

        observableList = FXCollections.observableArrayList();

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
    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Main.changeScene("dietaXcliente-view.fxml","Dieta del cliente");
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
        dietas.setDia(cbDia.getValue());
        dietas.setMomento(cbMenu.getValue());
        dietas.setReceta(recetas);

        System.out.println(recetas.getIdReceta());
        System.out.println(recetas.getNombre());

        recetas.setPasos(txtPasos.getText());
        recetas.setTiempoPreparacion(Integer.valueOf(txtTiempo.getText()));
        recetas.setNombre(txtNombre.getText());
        recetas.setKcalTotal(Double.valueOf(txtKcal.getText()));
        recetas.setCarbohidratosTotal(Double.valueOf(txtCH.getText()));
        recetas.setGrasasTotal(Double.valueOf(txtGrasas.getText()));
        recetas.setAlimentos(txtAlimentos.getText());

        dietasDAO1.update(dietas);
        recetaDAO.update(recetas);



        Main.changeScene("dietaXcliente-view.fxml","Dieta del Cliente");

        // Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("La receta ha sido actualizada");
        alert.setContentText("Nombre de la receta: " + Sesion.getDietas().getReceta().getNombre() + " realizado por " +  Sesion.getEmpleado().getNombre() + " " + Sesion.getEmpleado().getApellidos());
        alert.showAndWait();
    }


    @javafx.fxml.FXML
    public void EReceta(ActionEvent actionEvent) throws IOException {
        var dietaSelec = Sesion.getDietas();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Deseas borrar la receta llamada: " + dietaSelec.getReceta().getNombre() + "?");
        var result = alert.showAndWait().get();

        if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            dietasDAO.delete(dietaSelec);
            Main.changeScene("dietaXcliente-view.fxml", "Dieta del cliente");
        }
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}