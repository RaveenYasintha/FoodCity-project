package com.example.demo1.TO;

import java.util.ArrayList;

public class Order {
    private String oid;
    private double total;
    private ArrayList<OrderDetail> orderDetails;

    public Order(String oid, double total, ArrayList<OrderDetail> orderDetails) {
        this.oid = oid;
        this.total = total;
        this.orderDetails = orderDetails;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
