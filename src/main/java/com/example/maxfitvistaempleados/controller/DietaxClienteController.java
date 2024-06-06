package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.dieta.Dietas;
import com.example.maxfitvistaempleados.dieta.DietasDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class DietaxClienteController implements Initializable {
    @javafx.fxml.FXML
    private ComboBox<String> cbDia;
    @javafx.fxml.FXML
    private ComboBox cbNombreReceta;
    @javafx.fxml.FXML
    private TableColumn<Dietas , String> CCDia;
    @javafx.fxml.FXML
    private TableColumn<Dietas , String> CCNReceta;
    @javafx.fxml.FXML
    private TableColumn<Dietas , String> CCTP;
    @javafx.fxml.FXML
    private TableColumn<Dietas , String> CCKcal;
    @javafx.fxml.FXML
    private TableColumn<Dietas , String> CCarbo;
    @javafx.fxml.FXML
    private TableColumn <Dietas , String> CCGrasas;
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
    private Button btnCD;
    @javafx.fxml.FXML
    private Button btnDP;
    @javafx.fxml.FXML
    private TableView<Dietas> tvDieta;

    private ObservableList<Dietas> observableList;

    private DietasDAO dietasDAO = new DietasDAO();
    @javafx.fxml.FXML
    private Button btnVA;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> dia = FXCollections.observableArrayList();
        dia.addAll("Todos","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado");
        cbDia.setItems(dia);

        observableList = FXCollections.observableArrayList();

        this.CCDia.setCellValueFactory((fila) ->{
            String CCDia = String.valueOf(fila.getValue().getDia());
            return new SimpleStringProperty(CCDia);
        });
        this.CCarbo.setCellValueFactory((fila) -> {
            String carbo = String.valueOf(fila.getValue().getReceta().getCarbohidratosTotal());
            return new SimpleStringProperty(carbo);
        });
        this.CCGrasas.setCellValueFactory((fila) -> {
            String grasas = String.valueOf(fila.getValue().getReceta().getGrasasTotal());
            return new SimpleStringProperty(grasas);
        });
        this.CCKcal.setCellValueFactory((fila) ->{
            String kcal = String.valueOf(fila.getValue().getReceta().getKcalTotal());
            return new SimpleStringProperty(kcal);
        });
        this.CCNReceta.setCellValueFactory((fila) -> {
            String nReceta = String.valueOf(fila.getValue().getReceta().getNombre());
            return new SimpleStringProperty(nReceta);
        });

        this.CCTP.setCellValueFactory((fila)->{
            String tpm = String.valueOf(fila.getValue().getReceta().getTiempoPreparacion());
            return new SimpleStringProperty(tpm);
        });
        // Obtener el cliente de la sesión
        Clientes cliente = Sesion.getCliente();

        // Filtrar las rutinas por el cliente seleccionado
        List<Dietas> dietasCliente = dietasDAO.getByCliente(cliente);
        observableList.addAll(dietasCliente);


        tvDieta.setItems(observableList);

        tvDieta.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Dietas dietas = tvDieta.getSelectionModel().getSelectedItem();
                if (dietas != null) {
                    Sesion.setDietas(dietas);
                    try {
                        Main.changeScene("editarDieta-view.fxml","Editar Dietas");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }




    @javafx.fxml.FXML
    public void bqDia(ActionEvent actionEvent) {
        // Obtener el día seleccionado en el ComboBox
        String diaSeleccionado = cbDia.getValue();

        // Verificar si se ha seleccionado "Todos"
        if ("Todos".equals(diaSeleccionado)) {
            // Mostrar toda la información de la tabla nuevamente
            tvDieta.setItems(observableList);
        } else {
            // Filtrar la lista de dietas por el día seleccionado
            ObservableList<Dietas> resultados = FXCollections.observableArrayList();
            for (Dietas dieta : observableList) {
                if (dieta.getDia().equals(diaSeleccionado)) {
                    resultados.add(dieta);
                }
            }

            // Actualizar la tabla con los resultados de la búsqueda
            tvDieta.setItems(resultados);
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
    public void CrearDieta(ActionEvent actionEvent) throws IOException {
        Clientes clientes = Sesion.getCliente();
        if (clientes != null) {
            List<Dietas> dietasCliente = dietasDAO.getByCliente(clientes);

            if (dietasCliente.isEmpty()) {
                // No hay dietas, cambiar directamente a la nueva escena.
                Main.changeScene("CD-view.fxml", "Creación de Dieta Lunes");
            } else {
                // Hay dietas, mostrar confirmación.
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("¿Está seguro de que desea borrar la dieta anterior?");
                alert.setContentText("Esta acción no se puede deshacer.");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        dietasDAO.deleteByCliente(clientes);
                        try {
                            Main.changeScene("CD-view.fxml", "Creación de Dieta Lunes");
                        } catch (IOException e) {
                            e.printStackTrace();
                            showAlert("Error", "No se pudo cambiar la escena.");
                        }
                    }
                });
            }
        } else {
            showAlert("ERROR", "No existe cliente");
        }
    }



    @javafx.fxml.FXML
    public void AnadirDietaPredeterminada(ActionEvent actionEvent) throws IOException {
        Clientes clientes = Sesion.getCliente();
        if (clientes != null) {
            List<Dietas> dietasCliente = dietasDAO.getByCliente(clientes);

            if (dietasCliente.isEmpty()) {
                // No hay dietas, cambiar directamente a la nueva escena.
                Main.changeScene("anadirDietaPre-view.fxml","Añadir dieta predeterminada");
            } else {
                // Hay dietas, mostrar confirmación.
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("¿Está seguro de que desea borrar la dieta anterior?");
                alert.setContentText("Esta acción no se puede deshacer.");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        dietasDAO.deleteByCliente(clientes);
                        try {
                            Main.changeScene("anadirDietaPre-view.fxml","Añadir dieta predeterminada");
                        } catch (IOException e) {
                            e.printStackTrace();
                            showAlert("Error", "No se pudo cambiar la escena.");
                        }
                    }
                });
            }
        } else {
            showAlert("ERROR", "No existe cliente");
        }
    }


    @javafx.fxml.FXML
    public void Goback(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Cliente");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean showConfirmationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        return alert.showAndWait().filter(response -> response == buttonTypeYes).isPresent();
    }

}