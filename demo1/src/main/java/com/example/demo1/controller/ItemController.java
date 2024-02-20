package com.example.demo1.controller;

import com.example.demo1.TO.Item;
import com.example.demo1.db.DBConnection;
import com.example.demo1.model.ItemModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemController implements Initializable{
    public TableView<Item> tblItemTable;
    public TableColumn colCode;
    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;



    @FXML
    void btnDelete(ActionEvent event) {

        String code = txtCode.getText();

        ItemModel itemModel = new ItemModel();
        int i = itemModel.DeleteItem(code);

        if (i > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Item Deleted.");
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Item Not Deleted.");
            alert.show();
        }

    }

    @FXML
    void btnSave(ActionEvent event) {

        String code = txtCode.getText();
        String name = txtName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());

        ItemModel itemModel = new ItemModel();
        int i = itemModel.saveItem(code, name, qty, price);


        if (i > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Item Saved.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Item Not Saved.");
            alert.show();
        }

    }

    @FXML
    void btnSearch(ActionEvent event) {

        String code = txtCode.getText();

        ItemModel itemModel = new ItemModel();
        Item item = itemModel.searchItem(code);

        txtName.setText(item.getName());
        txtQty.setText(String.valueOf(item.getQty()));
        txtPrice.setText(String.valueOf(item.getUnitPrice()));



    }

    @FXML
    void btnUpdate(ActionEvent event) {
        String code = txtCode.getText();
        String name = txtName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());

        ItemModel itemModel = new ItemModel();
        int i = itemModel.UpdateItem(code, name, qty, price);



        if (i>0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Item Updated.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Item Not Updated.");
            alert.show();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblItemTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("code"));
        tblItemTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("name"));
        tblItemTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("qty"));
        tblItemTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("unitPrice"));

        ArrayList<Item> items = load();
        System.out.println(items);
        tblItemTable.setItems(FXCollections.observableArrayList(items));
    }

    public ArrayList<Item> load() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop",
                    "root", "1234");

            PreparedStatement stm = connection.prepareStatement("select * from item");

            ResultSet rst = stm.executeQuery();

            System.out.println("rst");
            ArrayList<Item> items = new ArrayList<>();

            while (rst.next()) {
//                System.out.println(rst.getString(1)+rst.getString(2)+rst.getDouble(3)+rst.getDouble(4));
                Item id = new Item(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getDouble(4));
               // System.out.println(id.getQuantity());
                items.add(id);
            }

            return items;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}