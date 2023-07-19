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

public class Home_window {

    public int doc;
    public String date, time, rec, name, phone;

    @FXML
    private Button doctor1;

    @FXML
    private Button doctor2;

    @FXML
    private Button doctor3;

    @FXML
    private Button doctor4;

    @FXML
    private Button doctor5;

    @FXML
    private Button doctor6;

    @FXML
    private Button doctor7;

    @FXML
    private Button doctor8;

    @FXML
    private Button help;

    @FXML
    private Button recording;

    @FXML
    private Button check;

    @FXML
    private ListView<String> dateview;

    @FXML
    private ListView<String> timeview;

    @FXML
    private TextField client_name;

    @FXML
    private TextField client_phone;

    @FXML
    private TextField date_recording;

    @FXML
    private TextField time_recording;

    @FXML
    void initialize() {

        doctor1.setOnAction(event -> {
            doc = 1;

            dateview.getItems().clear();
            timeview.getItems().clear();

            try {
                date_and_time_recording();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        doctor2.setOnAction(event -> {
            doc = 2;

            dateview.getItems().clear();
            timeview.getItems().clear();

            try {
                date_and_time_recording();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        doctor3.setOnAction(event -> {
            doc = 3;

            dateview.getItems().clear();
            timeview.getItems().clear();

            try {
                date_and_time_recording();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        doctor4.setOnAction(event -> {
            doc = 4;

            dateview.getItems().clear();
            timeview.getItems().clear();

            try {
                date_and_time_recording();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        doctor5.setOnAction(event -> {
            doc = 5;

            dateview.getItems().clear();
            timeview.getItems().clear();

            try {
                date_and_time_recording();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        doctor6.setOnAction(event -> {
            doc = 6;

            dateview.getItems().clear();
            timeview.getItems().clear();

            try {
                date_and_time_recording();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        doctor7.setOnAction(event -> {
            doc = 7;

            dateview.getItems().clear();
            timeview.getItems().clear();

            try {
                date_and_time_recording();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        doctor8.setOnAction(event -> {
            doc = 8;

            dateview.getItems().clear();
            timeview.getItems().clear();

            try {
                date_and_time_recording();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        recording.setOnAction(event -> {

            if(!(client_name.getText().trim().equals("")) && !(client_name.getText().length() > 150)){

                if(!(client_phone.getText().length() > 11) && !(client_phone.getText().length() < 11)){

                    try {
                        date_time_rec_name();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    if ((client_phone.getText().equals(phone) && client_name.getText().trim().equals(name)) || phone == null) {

                        if (!client_name.getText().trim().equals(rec)){

                            if (!(date_recording.getText().trim().equals("")) && date_recording.getText().trim().equals(date)) {

                                String tm = time_recording.getText() + ":00";
                                if (!(time_recording.getText().trim().equals("")) && tm.equals(time)) {

                                    try {
                                        update();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }

                                    success();

                                    client_name.setText("");
                                    client_phone.setText("");
                                    date_recording.setText("");
                                    time_recording.setText("");
                                }
                                else {
                                    error();
                                }
                            }
                            else {
                                error();
                            }
                        }
                        else {
                            error2();
                        }
                    }
                    else {
                        error3();
                    }
                }
                else {
                    error();
                }
            }
            else {
                error();
            }

            date = "";
            time = "";
            rec = "";
            name = null;
            phone = null;
            dateview.getItems().clear();
            timeview.getItems().clear();

            try {
                date_and_time_recording();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        help.setOnAction(event -> {
            help.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Help.fxml"));

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

        check.setOnAction(event -> {
            check.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Check.fxml"));

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

    private void date_and_time_recording() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);

        Statement statement = connection.createStatement();
        String SQL = "SELECT date_recording FROM time WHERE client_name IS NULL AND doctor_id = " + doc
                + "ORDER BY date_recording ASC, time_recording ASC LIMIT 10";
        ResultSet result = statement.executeQuery(SQL);

        while (result.next()) {
            dateview.getItems().addAll(String.valueOf(result.getString("date_recording")));
        }

        SQL = "SELECT time_recording FROM time WHERE client_name IS NULL AND doctor_id = " + doc
                + "ORDER BY date_recording ASC, time_recording ASC LIMIT 10";
        result = statement.executeQuery(SQL);

        while (result.next()){
            timeview.getItems().addAll(String.valueOf(result.getString("time_recording")));
        }

        connection.close();
    }

    private void date_time_rec_name() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
        Statement statement = connection.createStatement();
        String SQL = "SELECT date_recording FROM time WHERE doctor_id = " + doc
                + " AND client_name IS NULL AND date_recording = '" + date_recording.getText() + "' LIMIT 1;";
        ResultSet result = statement.executeQuery(SQL);
        while (result.next()){
            date = String.valueOf(result.getString("date_recording"));
        }
        SQL = "SELECT time_recording FROM time WHERE doctor_id = " + doc
                + " AND client_name IS NULL AND date_recording = '" + date_recording.getText()
                + "' AND time_recording = '" + time_recording.getText() + "' LIMIT 1;";
        result = statement.executeQuery(SQL);
        while (result.next()){
            time = String.valueOf(result.getString("time_recording"));
        }
        SQL = "SELECT client_name FROM time WHERE doctor_id = " + doc + " AND client_name = '" + client_name.getText()
                + "' AND client_phone = " + client_phone.getText();
        result = statement.executeQuery(SQL);
        while (result.next()){
            rec = String.valueOf(result.getString("client_name"));
        }
        SQL = "SELECT client_name FROM time WHERE client_name = '" + client_name.getText()
                + "' AND client_phone = " + client_phone.getText() + " LIMIT 1";
        result = statement.executeQuery(SQL);
        while (result.next()){
            name = String.valueOf(result.getString("client_name"));
        }
        SQL = "SELECT client_phone FROM time WHERE client_phone = " + client_phone.getText() + " LIMIT 1";
        result = statement.executeQuery(SQL);
        while (result.next()){
            phone = String.valueOf(result.getString("client_phone"));
        }
        connection.close();
    }

    private void update() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);

        Statement statement = connection.createStatement();
        String SQL = "UPDATE time SET client_name = '" + client_name.getText() + "', client_phone = " + client_phone.getText()
                + "WHERE doctor_id = " + doc + " AND date_recording = '" + date_recording.getText()
                + "' AND time_recording = '" + time_recording.getText() + "'";
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

    private void error2(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Error2.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 200, 100));
        stage.setResizable(false);
        stage.setTitle("Ошибка записи!");
        stage.getIcons().add(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/images/icon.png"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void error3(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Error3.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 200, 100));
        stage.setResizable(false);
        stage.setTitle("Ошибка записи!");
        stage.getIcons().add(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/images/icon.png"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void success(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Success.fxml"));

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
