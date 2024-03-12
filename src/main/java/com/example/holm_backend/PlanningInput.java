package com.example.holm_backend;

public class PlanningInput {
    private int loading_capacity;
    private Integer[] deliveryQuantities;
    private Integer[] pickUpQuantities;
    private Double[] x;
    private Double[] y;
    private Double[][] distances;

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

    public Double[][] getDistances() {
        return distances;
    }

    public void setDistances(Double[][] distances) {
        this.distances = distances;
    }

    public Integer[] getPickUpQuantities() {
        return pickUpQuantities;
    }

    public void setPickUpQuantities(Integer[] pickUpQuantities) {
        this.pickUpQuantities = pickUpQuantities;
    }
}
