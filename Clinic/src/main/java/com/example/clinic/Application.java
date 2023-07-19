package com.example.clinic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Home_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 505);
        stage.setTitle("Алгоритм здоровья");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/images/icon.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}