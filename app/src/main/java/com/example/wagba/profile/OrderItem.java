package com.example.wagba.profile;

import java.util.ArrayList;

public class OrderItem {
    ArrayList<SingleOrderItem> itemList;
    String orderState;
    String orderPrice;
    String orderGate;
    String orderTime;
    String orderID;
    String date;
    String orderRestaurant;

    public OrderItem(ArrayList<SingleOrderItem> itemList, String orderState, String orderPrice, String orderGate, String orderTime, String orderID, String date, String orderRestaurant) {
        this.itemList = itemList;
        this.orderState = orderState;
        this.orderPrice = orderPrice;
        this.orderGate = orderGate;
        this.orderTime = orderTime;
        this.orderID = orderID;
        this.date = date;
        this.orderRestaurant = orderRestaurant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<SingleOrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<SingleOrderItem> itemList) {
        this.itemList = itemList;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderGate() {
        return orderGate;
    }

    public void setOrderGate(String orderGate) {
        this.orderGate = orderGate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderRestaurant() {
        return orderRestaurant;
    }

    public void setOrderRestaurant(String orderRestaurant) {
        this.orderRestaurant = orderRestaurant;
    }
}
