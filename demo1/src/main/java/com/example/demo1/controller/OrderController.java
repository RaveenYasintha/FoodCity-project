package com.example.demo1.controller;


import com.example.demo1.TM.OrderTM;
import com.example.demo1.TO.Item;
import com.example.demo1.TO.Order;
import com.example.demo1.TO.OrderDetail;
import com.example.demo1.model.OrderModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtQuantity;

    @FXML
    private Label lblTotalPrice;

    @FXML
    private ComboBox<String> comboCode;

    @FXML
    private TextField txtNameOrder;

    @FXML
    private TextField txtQuantityOnHandOrder;
    @FXML
    private TextField txtPriceOrder;

    @FXML
    private TableView<OrderTM> tblOrderTable;

    ArrayList<OrderDetail> orderDetails;

    @FXML
    void btnAddToCart(ActionEvent event) {

        double total = Double.parseDouble(txtPriceOrder.getText()) * Integer.parseInt(txtQuantity.getText());
        OrderTM orderTM = new OrderTM(comboCode.getSelectionModel().getSelectedItem(), txtNameOrder.getText(), Integer.parseInt(txtQuantity.getText()), total);
        System.out.println(orderTM);
        tblOrderTable.getItems().add(orderTM);


            double subTotal =Double.parseDouble(lblTotalPrice.getText())+total;
            lblTotalPrice.setText(String.valueOf(subTotal));

            orderDetails.add(new OrderDetail(comboCode.getSelectionModel().getSelectedItem(),Integer.parseInt(txtQuantity.getText()),total));
    }

    @FXML
    void btnOrder(ActionEvent event) {
        OrderModel.placeOrder(new Order(txtOrderId.getText(),Double.parseDouble(lblTotalPrice.getText()),orderDetails));

       }
    @FXML
    void selectItem(ActionEvent event) {

        Item item = OrderModel.getItem(comboCode.getSelectionModel().getSelectedItem());
        txtNameOrder.setText(item.getName());
        txtQuantityOnHandOrder.setText(String.valueOf(item.getQty()));
        txtPriceOrder.setText(String.valueOf(item.getUnitPrice()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<String> strings = OrderModel.loadItemIds();
        comboCode.setItems(FXCollections.observableArrayList(strings));

        tblOrderTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblOrderTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblOrderTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblOrderTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));

        orderDetails = new ArrayList<OrderDetail>();
    }
}
