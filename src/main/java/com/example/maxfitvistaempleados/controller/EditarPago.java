package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
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

public class EditarPago implements Initializable {
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
    private Button ButtonPago;
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
    private PagosDAO pagosDAO = new PagosDAO();



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
    public void Epago(ActionEvent actionEvent) throws IOException {
        PagosDAO pagosDAO1 = new PagosDAO();

        Pagos pagosAct = new Pagos();

        pagosAct.setId(Sesion.getPagos().getId());
        pagosAct.setNombre(txtNombre.getText());
        pagosAct.setDinero(Double.valueOf(txtDinero.getText()));
        pagosAct.setGrupo(cbGrupo.getValue());
        pagosAct.setEmpleado(Sesion.getEmpleado().getNombre());
        pagosAct.setFecha(Sesion.getPagos().getFecha());
        pagosAct.setTipopago(cbPago.getValue());
        pagosAct.setDescripcion(TADesc.getText());

        pagosDAO1.update(pagosAct);

        Main.changeScene("pago-view.fxml","Pagos");
    }

    @javafx.fxml.FXML
    public void BorrarPago(ActionEvent actionEvent) throws IOException {
        var pagoSeleccionado = Sesion.getPagos();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Deseas borrar el pago: " + pagoSeleccionado.getNombre() + "?");
        var result = alert.showAndWait().get();

        if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE){
            pagosDAO.delete(pagoSeleccionado);
            Main.changeScene("pago-view.fxml","Pagos");
        }
    }

    @javafx.fxml.FXML
    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Main.changeScene("pago-view.fxml","Pagos");
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


        txtNombre.setText(Sesion.getPagos().getNombre());
        txtDinero.setText(String.valueOf(Sesion.getPagos().getDinero()));
        TADesc.setText(Sesion.getPagos().getDescripcion());
        cbPago.setValue(Sesion.getPagos().getTipopago());
        cbGrupo.setValue(Sesion.getPagos().getGrupo());

    }
}