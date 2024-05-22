package com.example.maxfitvistaempleados;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage myStage ;
    @Override
    public void start(Stage stage) throws IOException {
        myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/maxfitvistaempleados/views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 585, 548);
        stage.setTitle("Inicio Sesion");
        stage.setScene(scene);
        stage.show();
    }
    public static void changeScene(String fxml, String titulo) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/maxfitvistaempleados/views/" + fxml));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        myStage.setTitle(titulo);
        myStage.setScene(scene);
        myStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}