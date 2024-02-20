package com.example.demo1.TO;

public class OrderDetail {
private String iid;
private int qty;
private double total;

    public OrderDetail(String iid, int qty, double total) {
        this.iid = iid;
        this.qty = qty;
        this.total = total;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
