package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.rutina.Ejercicios;
import com.example.maxfitvistaempleados.rutina.Rutina;
import com.example.maxfitvistaempleados.rutina.RutinaDAO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class RutinaXClienteController implements Initializable {
    @javafx.fxml.FXML
    private ComboBox<String> cbDia;
    @javafx.fxml.FXML
    private ComboBox<String> cbGrupo1;
    @javafx.fxml.FXML
    private TableColumn<Rutina ,String> CCDia;
    @javafx.fxml.FXML
    private TableColumn<Rutina ,String> CCGrupo;
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
    private Button btnCR;
    private ObservableList<Rutina> observableList;
    private RutinaDAO rutinaDAO = new RutinaDAO();
    @javafx.fxml.FXML
    private TableView<Rutina> tvRutina;
    @javafx.fxml.FXML
    private TableColumn<Rutina ,String> CCSeries;
    @javafx.fxml.FXML
    private TableColumn<Rutina ,String> CCRepes;
    @javafx.fxml.FXML
    private TableColumn<Rutina ,String> CCEjercicio;
    @javafx.fxml.FXML
    private TableColumn<Rutina,String> CCliente;
    @javafx.fxml.FXML
    private Button btnAtras;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> grupoMuscular = FXCollections.observableArrayList();
        grupoMuscular.addAll("Todos","Biceps","Triceps","Hombro","Cuadriceps","Gemelo","Femoral","Lumbar","Espalda","Glúteo","Pecho","Cardio","Abdominales");
        cbGrupo1.setItems(grupoMuscular);

        ObservableList<String> dia = FXCollections.observableArrayList();
        dia.addAll("Todos","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado");
        cbDia.setItems(dia);

        observableList = FXCollections.observableArrayList();

        // Obtener el cliente de la sesión
        Clientes cliente = Sesion.getCliente();

        // Filtrar las rutinas por el cliente seleccionado
        List<Rutina> rutinasCliente = rutinaDAO.getByCliente(cliente);
        observableList.addAll(rutinasCliente);


        this.CCDia.setCellValueFactory((fila) ->{
            String CCDia = String.valueOf(fila.getValue().getDia());
            return new SimpleStringProperty(CCDia);
        });
        this.CCEjercicio.setCellValueFactory((fila) -> {
            String cEjercicio = "";
            String ejercicio = String.valueOf(fila.getValue().getEjercicios().getNombre()); // Obtiene el ejercicio asociado a la rutina
            if (ejercicio != null) {
                Ejercicios ejercicios = new Ejercicios();
                cEjercicio = ejercicios.getNombre(); // Obtiene el nombre del ejercicio si no es null
            }
            return new SimpleStringProperty(ejercicio);
        });

        this.CCGrupo.setCellValueFactory((fila) -> {
            String cFecha = String.valueOf(fila.getValue().getEjercicios().getCuerpo());
            return new SimpleStringProperty(cFecha);
        });
        this.CCSeries.setCellValueFactory((fila) ->{
            String cSeries = String.valueOf(fila.getValue().getSeries());
            return new SimpleStringProperty(cSeries);
        });
        this.CCRepes.setCellValueFactory((fila) -> {
            String cRepes = String.valueOf(fila.getValue().getRepeticiones());
            return new SimpleStringProperty(cRepes);
        });
        this.CCliente.setCellValueFactory((fila) -> {
            String clientee = "";
            Clientes clientes = fila.getValue().getClientes();
            if (clientes != null) {
                clientee = clientes.getNombre();
            }
            return new SimpleStringProperty(clientee);
        });


        //observableList.addAll(rutinaDAO.getAll());

        tvRutina.setItems(observableList);

        tvRutina.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Rutina rutina = tvRutina.getSelectionModel().getSelectedItem();
                if (rutina != null) {
                    Sesion.setRutina(rutina);
                    try {
                        Main.changeScene("ER-view.fxml","Editar Ejercicio");
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
            tvRutina.setItems(observableList);
        } else {
            // Filtrar la lista de rutinas por el día seleccionado
            ObservableList<Rutina> resultados = FXCollections.observableArrayList();
            for (Rutina rutina : observableList) {
                // Obtener el día de la rutina y verificar si coincide con el día seleccionado
                if (rutina.getDia().equals(diaSeleccionado)) {
                    resultados.add(rutina);
                }
            }

            // Actualizar la tabla con los resultados de la búsqueda
            tvRutina.setItems(resultados);
        }
    }

    @javafx.fxml.FXML
    public void bqGrupo(ActionEvent actionEvent) {
        // Obtener el grupo muscular seleccionado en el ComboBox
        String grupoMuscularSeleccionado = cbGrupo1.getValue();

        // Verificar si se ha seleccionado "Todos"
        if ("Todos".equals(grupoMuscularSeleccionado)) {
            // Mostrar toda la información de la tabla nuevamente
            tvRutina.setItems(observableList);
        } else {
            // Filtrar la lista de rutinas por el grupo muscular seleccionado
            ObservableList<Rutina> resultados = FXCollections.observableArrayList();
            for (Rutina rutina : observableList) {
                // Obtener el grupo muscular del ejercicio asociado a la rutina y verificar si coincide con el grupo seleccionado
                if (rutina.getEjercicios() != null && rutina.getEjercicios().getCuerpo().equals(grupoMuscularSeleccionado)) {
                    resultados.add(rutina);
                }
            }

            // Actualizar la tabla con los resultados de la búsqueda
            tvRutina.setItems(resultados);
        }
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

    public void CrearRutina(ActionEvent actionEvent) throws IOException {
        Clientes clientes = Sesion.getCliente();
        if (clientes != null) {
            List<Rutina> rutinas = rutinaDAO.getByCliente2(clientes);
            if (!rutinas.isEmpty()) {
                Rutina rutina = rutinas.get(0); // Obtiene la primera rutina de la lista
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("El cliente ya tiene una rutina.");
                alert.setContentText("¿Está seguro de que desea crear una nueva rutina? Esto borrará la rutina anterior.");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        rutinaDAO.deleteByCliente(clientes);
                        try {
                            Main.changeScene("CR-view.fxml", "Creación de Rutina");
                        } catch (IOException e) {
                            e.printStackTrace();
                            showAlert("Error", "No se pudo cambiar la escena.");
                        }
                    }
                });
            } else {
                Main.changeScene("CR-view.fxml", "Creación de Rutina");
            }
        } else {
            showAlert("ERROR", "No existe cliente");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }



    @Deprecated
    public void Ejercicios(ActionEvent actionEvent) throws IOException {
        Main.changeScene("Ejercicios-view.fxml","Ejercicios");
    }


    public void VolverAtras(ActionEvent actionEvent) throws IOException {
        Clientes clientes = Sesion.getCliente();
        if (clientes != null){
            Main.changeScene("view-empleado.fxml", "Cliente");
        }
    }

    @javafx.fxml.FXML
    public void DescargarRutina(ActionEvent actionEvent) throws SQLException, JRException {
        /*
    METODO PARA PDF / JASPERSOFT
    */
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/maxfitdb", "root", "");
        HashMap<String, Object> hashMap = new HashMap<>();

        // Asegúrate de pasar el parámetro correcto al informe
        hashMap.put("matriculaCliente", Sesion.getCliente().getMatricula());

        JasperPrint jasperPrint = JasperFillManager.fillReport("RutinaPDF.jasper", hashMap, connection);

        // Mostrar el informe en una ventana
        JasperViewer.viewReport(jasperPrint, false);

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("Rutina.pdf"));
        exp.setConfiguration(new SimplePdfExporterConfiguration());
        exp.exportReport();
    }

}