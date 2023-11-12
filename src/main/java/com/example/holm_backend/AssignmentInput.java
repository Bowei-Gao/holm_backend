package com.example.holm_backend;

public class AssignmentInput {
    private Integer[][] distances;
    private Integer[] inventory;
    private Integer[] demand;

    public Integer[][] getDistances() {
        return distances;
    }

    public void setDistances(Integer[][] distances) {
        this.distances = distances;
    }

    public Integer[] getInventory() {
        return inventory;
    }

    public void setInventory(Integer[] inventory) {
        this.inventory = inventory;
    }

    public Integer[] getDemand() {
        return demand;
    }

    public void setDemand(Integer[] demand) {
        this.demand = demand;
    }
}
