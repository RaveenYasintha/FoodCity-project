package com.example.demo1.model;

import com.example.demo1.TO.Item;
import com.example.demo1.TO.Order;
import com.example.demo1.TO.OrderDetail;
import com.example.demo1.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class OrderModel {

    public static Item getItem(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement stm = connection.prepareStatement("select * from item where code=?");
            stm.setObject(1, id);

            ResultSet rst = stm.executeQuery();

            Item item = new Item();


            while (rst.next()) {
                item.setCode(rst.getString(1));
                item.setName(rst.getString(2));
                item.setQty(rst.getInt(3));
                item.setUnitPrice(rst.getDouble(4));
            }
            return item;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static ArrayList<String> loadItemIds() {
        try {

            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement stm = connection.prepareStatement("select code from item");


            ResultSet rst = stm.executeQuery();

            ArrayList<String> ids = new ArrayList<>();

            while (rst.next()) {
                ids.add(rst.getString(1));
            }
            return ids;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean placeOrder(Order order){
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            // Auto Commit disabled
            connection.setAutoCommit(false);

            PreparedStatement stm = connection.prepareStatement("insert into orders values(?,?)");
            stm.setObject(1,order.getOid());
            stm.setObject(2,order.getTotal());

            int inserted = stm.executeUpdate();

            if (0< inserted){
                ArrayList<OrderDetail> orderDetails = order.getOrderDetails();

                for (OrderDetail od:orderDetails) {
                    PreparedStatement stm2 = connection.prepareStatement("insert into orderDetails(orderID,code,qty,cur_total) values(?,?,?,?)");
                    stm2.setObject(1,order.getOid());
                    stm2.setObject(2,od.getIid());
                    stm2.setObject(3,od.getQty());
                    stm2.setObject(4,od.getTotal());

                    int inserted2 = stm2.executeUpdate();

                    // Item qty updated...
                    PreparedStatement stm3 = connection.prepareStatement("update item set qty = qty-? where code=?");
                    stm3.setObject(1,od.getQty());
                    stm3.setObject(2,od.getIid());


                    int inserted3 = stm3.executeUpdate();

                    connection.commit();
                    connection.setAutoCommit(true);

                }


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
