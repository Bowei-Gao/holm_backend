package com.example.holm_backend;

public class PlanningInput {
    private int loading_capacity;
    private Integer[] deliveryQuantities;
    private Double[] x;
    private Double[] y;

    public int getLoading_capacity() {
        return loading_capacity;
    }

    public void setLoading_capacity(int loading_capacity) {
        this.loading_capacity = loading_capacity;
    }

    public Integer[] getDeliveryQuantities() {
        return deliveryQuantities;
    }

    public void setDeliveryQuantities(Integer[] deliveryQuantities) {
        this.deliveryQuantities = deliveryQuantities;
    }

    public Double[] getX() {
        return x;
    }

    public void setX(Double[] x) {
        this.x = x;
    }

    public Double[] getY() {
        return y;
    }

    public void setY(Double[] y) {
        this.y = y;
    }
}
