package com.example.clinic;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Check {

    public String all;

    @FXML
    private TextField client_name;

    @FXML
    private TextField client_phone;

    @FXML
    private TextField doctor_name;

    @FXML
    private TextField date_recording;

    @FXML
    private TextField time_recording;

    @FXML
    private ListView<String> doctorview;

    @FXML
    private ListView<String> dateview;

    @FXML
    private ListView<String> timeview;

    @FXML
    private Button delete;

    @FXML
    private Button check;

    @FXML
    private Button go_back;

    @FXML
    void initialize() {

        check.setOnAction(event -> {

            doctorview.getItems().clear();
            dateview.getItems().clear();
            timeview.getItems().clear();

            if (!(client_name.getText().trim().equals("")) && !(client_name.getText().length() > 150)){

                if(!(client_phone.getText().length() > 11) && !(client_phone.getText().length() < 11)){

                    try {
                        doctor_date_time();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    error();
                }
            }
            else{
                error();
            }

        });

        delete.setOnAction(event -> {

            try {
                check_all();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String tm = time_recording.getText() + ":00";
            if (tm.equals(all)) {

                try {
                    update();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                delete();

                doctor_name.setText("");
                date_recording.setText("");
                time_recording.setText("");

            }
            else {
                error();
            }

            doctorview.getItems().clear();
            dateview.getItems().clear();
            timeview.getItems().clear();

            try {
                doctor_date_time();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            all = "";
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

    private void doctor_date_time() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);

        Statement statement = connection.createStatement();
        String SQL = "SELECT doctor_name FROM doctor WHERE doctor_id IN ( SELECT doctor_id FROM time WHERE client_name = '" + client_name.getText()
                + "' AND client_phone = " + client_phone.getText() + " ) ORDER BY doctor_id ASC";
        ResultSet result = statement.executeQuery(SQL);

        while (result.next()){
            doctorview.getItems().addAll(String.valueOf(result.getString("doctor_name")));
        }

        SQL = "SELECT date_recording FROM time WHERE client_name = '" + client_name.getText()
                + "' AND client_phone = " + client_phone.getText() + "ORDER BY doctor_id ASC";
        result = statement.executeQuery(SQL);

        while (result.next()){
            dateview.getItems().addAll(String.valueOf(result.getString("date_recording")));
        }

        SQL = "SELECT time_recording FROM time WHERE client_name = '" + client_name.getText()
                + "' AND client_phone = " + client_phone.getText() + "ORDER BY doctor_id ASC";
        result = statement.executeQuery(SQL);

        while (result.next()){
            timeview.getItems().addAll(String.valueOf(result.getString("time_recording")));
        }

        connection.close();
    }

    private void check_all() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);

        Statement statement = connection.createStatement();
        String SQL = "SELECT time_recording FROM time WHERE client_name = '" + client_name.getText() + "' AND client_phone = "
                + client_phone.getText() + " AND date_recording = '" + date_recording.getText()
                + "' AND time_recording = '" + time_recording.getText()
                + "' AND doctor_id IN (SELECT doctor_id FROM doctor WHERE doctor_name = '" + doctor_name.getText() + "')";
        ResultSet result = statement.executeQuery(SQL);

        while (result.next()) {
            all = String.valueOf(result.getString("time_recording"));
        }

        connection.close();
    }

    private void update() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);

        Statement statement = connection.createStatement();
        String SQL = "UPDATE time SET client_name = NULL, client_phone = NULL WHERE date_recording = '" + date_recording.getText()
                + "' AND time_recording = '" + time_recording.getText()
                + "' AND doctor_id IN (SELECT doctor_id FROM doctor WHERE doctor_name = '" + doctor_name.getText() + "')";
        statement.executeUpdate(SQL);

        connection.close();
    }

    private void error(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Error.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 200, 100));
        stage.setResizable(false);
        stage.setTitle("Ошибка данных!");
        stage.getIcons().add(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/images/icon.png"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void delete(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Delete.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 200, 100));
        stage.setResizable(false);
        stage.setTitle("Успех!");
        stage.getIcons().add(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/images/icon.png"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
