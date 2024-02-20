package com.example.demo1.controller;

import com.example.demo1.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnLogin(ActionEvent event) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user WHERE user_name=?  AND password=?;");
            pstm.setObject(1,txtUsername.getText());
            pstm.setObject(2,txtPassword.getText());

            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {

                try {


                    Stage stage = (Stage) this.root.getScene().getWindow();

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/dashboard-view.fxml"));

                    Scene scene = new Scene(fxmlLoader.load());

                    stage.setScene(scene);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                new Alert(Alert.AlertType.ERROR).show();
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }


    }
}
