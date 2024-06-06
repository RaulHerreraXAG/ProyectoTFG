package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.rutina.Ejercicios;
import com.example.maxfitvistaempleados.rutina.EjerciciosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditarEjercicio  implements Initializable {
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
    private TextField txtCuerpo;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextArea txtObservacion;
    @javafx.fxml.FXML
    private TextField txtCorreo;
    @javafx.fxml.FXML
    private Button ButtonCrear;
    @javafx.fxml.FXML
    private Button ButtonAtras;
    @javafx.fxml.FXML
    private Button ButtonEliminar;

    private EjerciciosDAO ejerciciosDAO = new EjerciciosDAO();



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
        Main.changeScene("pago-view.fxml","Pago");
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
        Main.changeScene("Ejercicio-view.fxml","Ejercicio");
    }

    @javafx.fxml.FXML
    public void CEjercicio(ActionEvent actionEvent) throws IOException {
        EjerciciosDAO ejerciciosDAO = new EjerciciosDAO();
        Ejercicios ejercicios = new Ejercicios();

        Ejercicios ejercicios1 = Sesion.getEjercicios();

        ejercicios.setId(ejercicios1.getId());
        ejercicios.setNombre(txtNombre.getText());
        ejercicios.setCuerpo(txtCuerpo.getText());
        ejercicios.setNumero(Integer.valueOf(txtCorreo.getText()));
        ejercicios.setObservacion((txtObservacion.getText()));
        ejerciciosDAO.update(ejercicios);
        Sesion.setEjercicios(ejercicios);

        if (ejercicios != null){
            Main.changeScene("Ejercicios-view.fxml","Ejericicios");
        }

        //Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("El ejercicio ha sido actualizado");
        alert.setContentText("Nombre del ejercicio: " + Sesion.getEjercicios().getNombre());
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void Eliminar(ActionEvent actionEvent) throws IOException {
        var ejercicioSelec = Sesion.getEjercicios();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Deseas borrar el ejercicio llamado: " + ejercicioSelec.getNombre() + "?");
        var result = alert.showAndWait().get();

        if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            ejerciciosDAO.delete(ejercicioSelec);
            Main.changeScene("Ejercicios-view.fxml", "Ejercicios");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtCorreo.setText(String.valueOf(Sesion.getEjercicios().getNumero()));
        txtCuerpo.setText(Sesion.getEjercicios().getCuerpo());
        txtNombre.setText(Sesion.getEjercicios().getNombre());
        txtObservacion.setText(Sesion.getEjercicios().getObservacion());
    }
}