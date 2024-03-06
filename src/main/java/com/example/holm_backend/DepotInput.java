package com.example.holm_backend;

public class DepotInput {
    private String[] names;
    private Double[] x;
    private Double[] y;
    private Integer[] capacities;
    private Integer[] fixed_costs;

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
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

    public Integer[] getCapacities() {
        return capacities;
    }

    public void setCapacities(Integer[] capacities) {
        this.capacities = capacities;
    }

    public Integer[] getFixed_costs() {
        return fixed_costs;
    }

    public void setFixed_costs(Integer[] fixed_costs) {
        this.fixed_costs = fixed_costs;
    }
}
