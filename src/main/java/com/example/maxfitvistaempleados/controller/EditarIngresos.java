package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.ingreso.Ingresos;
import com.example.maxfitvistaempleados.ingreso.IngresosDAO;
import com.example.maxfitvistaempleados.pago.Pagos;
import com.example.maxfitvistaempleados.pago.PagosDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditarIngresos implements Initializable {
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
    private Button ButtonBorrar;
    @javafx.fxml.FXML
    private Button ButtonAtras1;
    @javafx.fxml.FXML
    private TextField txtDinero;
    @javafx.fxml.FXML
    private ComboBox<String> cbPago;
    @javafx.fxml.FXML
    private ComboBox<String> cbGrupo;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextArea TADesc;
    @javafx.fxml.FXML
    private Button ButtonIng;

    private IngresosDAO ingresosDAO = new IngresosDAO();


    @javafx.fxml.FXML
    public void CerrarSesion(ActionEvent actionEvent) throws IOException {
        Main.changeScene("login-view.fxml","Inicio Sesión");
    }

    @javafx.fxml.FXML
    public void Ingresos(ActionEvent actionEvent) throws IOException {
        Main.changeScene("ingreso-view.fxml","Ingresos");
    }

    @javafx.fxml.FXML
    public void Pagos(ActionEvent actionEvent) throws IOException {
        if(Sesion.getEmpleado() != null) {
            Main.changeScene("pago-view.fxml", "Pagos");
        }
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
    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Main.changeScene("ingreso-view.fxml","Ingresos");
    }
    public void Clientes(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Clientes");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> pago = FXCollections.observableArrayList();
        pago.addAll("Gym", "Zumba", "Yoga", "Pilates", "Boxeo");
        cbPago.setItems(pago);

        ObservableList<String> grupo = FXCollections.observableArrayList();
        grupo.addAll("Efectivo", "Tarjeta");
        cbGrupo.setItems(grupo);


        txtNombre.setText(Sesion.getIngresos().getNombre());
        txtDinero.setText(String.valueOf(Sesion.getIngresos().getDinero()));
        TADesc.setText(Sesion.getIngresos().getDescripcion());
        cbPago.setValue(Sesion.getIngresos().getTipopago());
        cbGrupo.setValue(Sesion.getIngresos().getGrupo());

    }

    @javafx.fxml.FXML
    public void EIngreso(ActionEvent actionEvent) throws IOException {
        IngresosDAO ingresosDAO1 = new IngresosDAO();

        Ingresos ingresos = new Ingresos();

        ingresos.setId(Sesion.getIngresos().getId());
        ingresos.setNombre(txtNombre.getText());
        ingresos.setDinero(Double.valueOf(txtDinero.getText()));
        ingresos.setGrupo(cbGrupo.getValue());
        ingresos.setEmpleado(Sesion.getEmpleado().getNombre());
        ingresos.setFecha(Sesion.getIngresos().getFecha());
        ingresos.setTipopago(cbPago.getValue());
        ingresos.setDescripcion(TADesc.getText());

        ingresosDAO1.update(ingresos);

        if(Sesion.getEmpleado() != null) {
            Main.changeScene("ingreso-view.fxml", "Ingresos");
        }

        //Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("El ingreso ha sido actualizado");
        alert.setContentText("Nombre del Ingreso: " + Sesion.getIngresos().getNombre());
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void BorrarIngresos(ActionEvent actionEvent) throws IOException {
        var ingresosSeleccionado = Sesion.getIngresos();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Deseas borrar el Ingresos: " + ingresosSeleccionado.getNombre() + "?");
        var result = alert.showAndWait().get();

        if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE){
            ingresosDAO.delete(ingresosSeleccionado);
            if(Sesion.getEmpleado() != null) {
                Main.changeScene("ingreso-view.fxml", "Ingresos");
            }
        }
    }
}