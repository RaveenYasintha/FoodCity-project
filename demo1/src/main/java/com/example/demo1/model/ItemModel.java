package com.example.demo1.model;

import com.example.demo1.TO.Item;
import com.example.demo1.db.DBConnection;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemModel {
    public int saveItem(String code, String name, int qty, double price) {
        try {

            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement stm = connection.prepareStatement("insert into item values (?,?,?,?)");

            stm.setObject(1, code);
            stm.setObject(2, name);
            stm.setObject(3, qty);
            stm.setObject(4, price);

            int i = stm.executeUpdate();

            return i;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Item searchItem(String code) {
        try {
        Connection connection =DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM item WHERE code = ?");

        stm.setObject(1,code);

            ResultSet resultSet = stm.executeQuery();

            Item item = null;
            if (resultSet.next()){
                item = new Item(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4));

            }
            return item;



        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public int UpdateItem(String code, String name, int qty, double price){
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement stm = connection.prepareStatement("update item set name=?,qty=?,unitPrice=? where code=?");

            stm.setObject(1,name);
            stm.setObject(2,qty);
            stm.setObject(3,price);
            stm.setObject(4,code);

            int i = stm.executeUpdate();

            return i;




        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int DeleteItem(String code){
        try {
           Connection connection = DBConnection.getInstance().getConnection();
           PreparedStatement stm = connection.prepareStatement("DELETE FROM item WHERE code = ?");

           stm.setObject(1,code);

            int i = stm.executeUpdate();

            return i;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
