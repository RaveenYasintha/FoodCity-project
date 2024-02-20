package com.example.demo1.controller;

import com.example.demo1.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private AnchorPane secondpane;
    private AnchorPane root;

    @FXML
    void btnDashboard(ActionEvent event) {
        try {

            Pane load = FXMLLoader.load(HelloApplication.class.getResource("view/dashboard-show-view.fxml"));
            secondpane.getChildren().setAll(load);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnItem(ActionEvent event) {
        try {

            Pane load = FXMLLoader.load(HelloApplication.class.getResource("view/item-view.fxml"));
            secondpane.getChildren().setAll(load);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnLogout(ActionEvent event) {
        try {


            Stage stage = (Stage) this.secondpane.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login-view.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void btnOrder(ActionEvent event) {
        try {

            Pane load = FXMLLoader.load(HelloApplication.class.getResource("view/order-view.fxml"));
            secondpane.getChildren().setAll(load);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
