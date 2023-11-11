package com.example.holm_backend;

import java.util.ArrayList;
import java.util.LinkedList;

public class AssignmentInput {
    private Double[][] distances;
    private Integer[] inventory;
    private Integer[] demand;

    public Double[][] getDistances() {
        return distances;
    }

    public void setDistances(Double[][] distances) {
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
