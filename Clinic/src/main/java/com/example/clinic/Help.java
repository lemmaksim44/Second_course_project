package com.example.clinic;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Help {

    public String symptom;
    @FXML
    private Label doctor_id;
    @FXML
    private Button go_back;
    @FXML
    private ListView<String> symptomsview;
    @FXML

    void initialize() throws SQLException, ClassNotFoundException {

        sympt();

        symptomsview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                symptom = symptomsview.getSelectionModel().getSelectedItem();

                try {
                    doctor();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                doctor_id.setText(symptom);
            }
        });

        go_back.setOnAction(event -> {
            go_back.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Home_window.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Алгоритм здоровья");
            stage.getIcons().add(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/images/icon.png"))));
            stage.show();
        });

    }

    private static final String DB_URL = "jdbc:postgresql://localhost:8008/Clinica";
    private static final String DB_User = "postgres";
    private static final String DB_Pass = "123456";

    private void sympt() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);

        Statement statement = connection.createStatement();
        String SQL = "SELECT symptoms_name FROM symptoms";
        ResultSet result = statement.executeQuery(SQL);

        while (result.next()){
            symptomsview.getItems().addAll(String.valueOf(result.getString("symptoms_name")));
        }

        connection.close();
    }

    private void doctor() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);

        Statement statement = connection.createStatement();
        String SQL = "SELECT doctor_name FROM doctor WHERE doctor_id = (SELECT doctor_id FROM symptoms WHERE symptoms_name = '" + symptom + "');";
        ResultSet result = statement.executeQuery(SQL);

        while (result.next()){
            symptom = String.valueOf(result.getString("doctor_name"));
        }

        connection.close();
    }
}
