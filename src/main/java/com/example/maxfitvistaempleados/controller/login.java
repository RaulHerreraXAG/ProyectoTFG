package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.admin.Admin;
import com.example.maxfitvistaempleados.admin.AdminDAO;
import com.example.maxfitvistaempleados.clientes.ClienteDAOImp;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.empleados.Empleado;
import com.example.maxfitvistaempleados.empleados.EmpleadoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.Serializable;

public class login implements Serializable {
    @javafx.fxml.FXML
    private TextField txtUser;
    @javafx.fxml.FXML
    private PasswordField txtPassword;
    @javafx.fxml.FXML
    private Button btnIniciar;
    @FXML
    private Label info;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        String email = txtUser.getText();
        String password = txtPassword.getText();
        if (email.length() < 4 || password.length() < 4) {
            info.setText("Introduce los datos");
            info.setStyle("-fx-background-color:red; -fx-text-fill: white;");

        } else {
            Empleado emp = (new EmpleadoDAO()).validateUser(email, password);
            Sesion.setEmpleado(emp);
            if (emp == null) {
                info.setText("Usuario no encontrado");
                info.setStyle("-fx-background-color:red; -fx-text-fill: white;");
            } else {
                info.setText("Usuario " + email + "(" + password + ") correcto");
                info.setStyle("-fx-background-color:green; -fx-text-fill: white;");
                try {
                    Main.changeScene("view-empleado.fxml", "MaxFit Empleados");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (email.length() < 4 || password.length() < 4) {
            info.setText("Introduce los datos");
            info.setStyle("-fx-background-color:red; -fx-text-fill: white;");

        } else {
            Admin admin = (new AdminDAO()).validateUser(email, password);
            Sesion.setAdmin(admin);
            if (admin == null) {
                info.setText("Usuario no encontrado");
                info.setStyle("-fx-background-color:red; -fx-text-fill: white;");
            } else {
                info.setText("Usuario " + email + "(" + password + ") correcto");
                info.setStyle("-fx-background-color:green; -fx-text-fill: white;");
                try {
                    Main.changeScene("view-empleado.fxml", "MaxFit Admin");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
