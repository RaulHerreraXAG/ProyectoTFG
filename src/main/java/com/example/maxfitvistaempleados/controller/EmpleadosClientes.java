package com.example.maxfitvistaempleados.controller;

import com.example.maxfitvistaempleados.Main;
import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.ClienteDAOImp;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.dieta.Dieta_Pre_Anadir;
import com.example.maxfitvistaempleados.dieta.Dieta_Pre_AnadirDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.ListCell;

import javafx.scene.control.ChoiceDialog;
import javafx.util.Callback;

import java.util.List;
import java.util.Optional;


public class EmpleadosClientes implements Initializable {
    @javafx.fxml.FXML
    private TextField txtBusqueda;
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
    private ComboBox<String> cbGenero;
    @javafx.fxml.FXML
    private TextField txtFechaInicio;
    @javafx.fxml.FXML
    private TextField txtApelldos;
    @javafx.fxml.FXML
    private TextField txtPeso;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtAltura;
    @javafx.fxml.FXML
    private TextField txtMatricula;
    @javafx.fxml.FXML
    private TextArea txtObservacion;
    @javafx.fxml.FXML
    private TextField txtEdad;
    @javafx.fxml.FXML
    private TextField txtFechaFin;
    @javafx.fxml.FXML
    private Button ButtonCrear;
    @javafx.fxml.FXML
    private Button ButtonRenovar;

    private final ClienteDAOImp clienteDAO = new ClienteDAOImp();
    @javafx.fxml.FXML
    private Button ButtonEditar;

    private ObservableList<Clientes> observableList;
    @javafx.fxml.FXML
    private Button ButtonEditarRutina;
    @javafx.fxml.FXML
    private Button ButtonEditarDieta;

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
            // Inicialmente establece todos los campos de texto como no editables
            txtNombre.setEditable(false);
            txtApelldos.setEditable(false);
            cbGenero.setDisable(true);
            txtFechaInicio.setEditable(false);
            txtEdad.setEditable(false);
            txtAltura.setEditable(false);
            txtPeso.setEditable(false);
            txtObservacion.setEditable(false);
            txtMatricula.setEditable(false);
            txtFechaFin.setEditable(false);


        cbGenero.getItems().addAll("Hombre", "Mujer");

    }

    public class ClienteListCell extends ListCell<Clientes> {
        @Override
        protected void updateItem(Clientes cliente, boolean empty) {
            super.updateItem(cliente, empty);
            if (empty || cliente == null) {
                setText(null);
            } else {
                setText(cliente.getNombre() + " " + cliente.getApellidos());
            }
        }
    }



    public Optional<Clientes> mostrarSeleccionCliente(List<Clientes> clientes) {
        ChoiceDialog<Clientes> dialog = new ChoiceDialog<>(clientes.get(0), clientes);
        dialog.setTitle("Selección de Cliente");
        dialog.setHeaderText("Múltiples coincidencias encontradas");
        dialog.setContentText("Selecciona el cliente:");

        // Obtener el ComboBox del diálogo y aplicar la ListCell personalizada
        ComboBox<Clientes> comboBox = (ComboBox<Clientes>) dialog.getDialogPane().lookup(".combo-box");
        if (comboBox != null) {
            comboBox.setCellFactory(new Callback<ListView<Clientes>, ListCell<Clientes>>() {
                @Override
                public ListCell<Clientes> call(ListView<Clientes> param) {
                    return new ClienteListCell();
                }
            });
            comboBox.setButtonCell(new ClienteListCell());
        }

        // Cargar y aplicar el archivo CSS al DialogPane del ChoiceDialog
        DialogPane dialogPane = dialog.getDialogPane();
        URL cssResource = getClass().getResource("/style.css");
        if (cssResource != null) {
            dialogPane.getStylesheets().add(cssResource.toExternalForm());
            dialogPane.getStyleClass().add("custom-choice-dialog");
        } else {
            System.err.println("Resource not found: /style.css");
        }

        return dialog.showAndWait();
    }



    @javafx.fxml.FXML
    public void Busquedad(ActionEvent actionEvent) {
        String textoBusqueda = txtBusqueda.getText().trim().toLowerCase();
        ArrayList<Clientes> clientesList = clienteDAO.getAll();
        ArrayList<Clientes> clientesCoincidencia = new ArrayList<>();

        for (Clientes cliente : clientesList) {
            String nombreCompleto = cliente.getNombre().toLowerCase() + " " + cliente.getApellidos().toLowerCase();
            if (nombreCompleto.contains(textoBusqueda)) {
                clientesCoincidencia.add(cliente);
            }
        }

        if (clientesCoincidencia.size() == 1) {
            // Si hay solo una coincidencia, usar ese cliente
            Clientes cliente = clientesCoincidencia.get(0);
            configurarCliente(cliente);
        } else if (clientesCoincidencia.size() > 1) {
            // Si hay más de una coincidencia, mostrar el diálogo de selección
            Optional<Clientes> resultado = mostrarSeleccionCliente(clientesCoincidencia);
            resultado.ifPresent(this::configurarCliente);
        } else {
            // No se encontró ninguna coincidencia
            // Aquí puedes mostrar un mensaje de error o realizar otra acción
            mostrarMensajeError("No se encontró ningún cliente con ese nombre.");
        }
    }

    private void configurarCliente(Clientes cliente) {
        // Configurar el cliente encontrado en la sesión
        Sesion.setCliente(cliente);

        // Establecer los datos del cliente en los campos de texto
        txtNombre.setText(cliente.getNombre());
        txtApelldos.setText(cliente.getApellidos());
        cbGenero.setValue(cliente.getGenero().toString());
        txtFechaInicio.setText(String.valueOf(cliente.getFechaInicio()));
        txtEdad.setText(String.valueOf(cliente.getEdad()) + " Años");
        txtAltura.setText(String.valueOf(cliente.getAltura()) + " M");
        txtPeso.setText(String.valueOf(cliente.getPeso()) + " Kg");
        txtObservacion.setText(cliente.getObservacion());
        txtMatricula.setText(String.valueOf(cliente.getMatricula()));
        txtFechaFin.setText(String.valueOf(cliente.getFechaFin()));

        // Comprobar si la fechaFin es menor que la fecha actual
        LocalDate fechaFin = cliente.getFechaFin();
        LocalDate fechaActual = LocalDate.now();

        if (fechaFin.isBefore(fechaActual)) {
            txtFechaFin.setStyle("-fx-text-fill: red;");
            showWarning("Advertencia", "La fecha de fin es anterior a la fecha actual.");
        } else {
            txtFechaFin.setStyle(""); // Reset style if the date is valid
        }
    }

    private void showWarning(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Se ha producido un error");
        alert.setContentText(mensaje);

        // Apply CSS styles
        DialogPane dialogPane = alert.getDialogPane();
        URL cssResource = getClass().getResource("style.css");
        if (cssResource != null) {
            System.out.println("Resource found: " + cssResource);
            dialogPane.getStylesheets().add(cssResource.toExternalForm());
            dialogPane.getStyleClass().add("custom-alert");
        } else {
            System.err.println("Resource not found: /styles.css ahora");
        }

        alert.showAndWait();
    }







    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApelldos.clear();
        cbGenero.getSelectionModel().clearSelection();
        txtFechaInicio.clear();
        txtEdad.clear();
        txtAltura.clear();
        txtPeso.clear();
        txtObservacion.clear();
        txtMatricula.clear();
        txtFechaFin.clear();
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
    public void CCliente(ActionEvent actionEvent) throws IOException {
        Main.changeScene("CC-view.fxml","Crear Cliente");
    }
    public void Clientes(ActionEvent actionEvent) throws IOException {
        Main.changeScene("view-empleado.fxml","Clientes");
    }


    @javafx.fxml.FXML
    public void ECliente(ActionEvent actionEvent) throws IOException {
        Clientes cliente = Sesion.getCliente();
        if (cliente != null) {
            Main.changeScene("editarcliente-view.fxml", "Editar Cliente");
        } else {
            // Manejar el caso en que el cliente es nulo
            showAlert("Error", "No se ha seleccionado ningún cliente para editar.");
        }
    }
    @javafx.fxml.FXML
    public void RMatricula(ActionEvent actionEvent) throws IOException {
        Main.changeScene("RenuevaMatricula-view.fxml","Renovar Matricula");
    }

    @javafx.fxml.FXML
    public void ERutina(ActionEvent actionEvent) throws IOException {
            Clientes clientes = Sesion.getCliente();
            if (clientes != null){
                Main.changeScene("RutinaXcliente-view.fxml","Rutina del cliente");
            }else {
                // Manejar el caso en que el cliente es nulo
                showAlert("Error", "No se ha seleccionado ningún cliente para ver la rutina.");
            }
        }

    @javafx.fxml.FXML
    public void EDieta(ActionEvent actionEvent) throws IOException {
        Clientes cliente = Sesion.getCliente();
        if (cliente != null) {
            // Verificar si el cliente tiene una dieta predefinida o personalizada
            if (tieneDietaPredefinida(cliente)) {
                Main.changeScene("dietaPreXcliente-view.fxml", "Dietas Predefinidas del cliente");
            } else {
                Main.changeScene("dietaXcliente-view.fxml", "Dietas Personalizadas del cliente");
            }
        } else {
            // Manejar el caso en que el cliente es nulo
            showAlert("Error", "No se ha seleccionado ningún cliente para ver las dietas.");
        }
    }

    // Método para verificar si el cliente tiene una dieta predefinida
    private boolean tieneDietaPredefinida(Clientes cliente) {
        // Lógica para verificar si el cliente tiene una dieta predefinida
        // Esto puede implicar consultar la base de datos u otro método de verificación
        // Aquí asumimos que existe un método en la clase Dieta_Pre_AnadirDAO para verificarlo
        Dieta_Pre_AnadirDAO dietaPreAnadirDAO = new Dieta_Pre_AnadirDAO();
        List<Dieta_Pre_Anadir> dietasPredefinidas = dietaPreAnadirDAO.getByCliente(cliente);
        return !dietasPredefinidas.isEmpty(); // Devuelve verdadero si hay dietas predefinidas para el cliente
    }

}
