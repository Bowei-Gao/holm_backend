package com.example.holm_backend;

public class CustomerInput {
    private String[] names;
    private Double[] x;
    private Double[] y;
    private Integer[] demand;

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

    public Integer[] getDemand() {
        return demand;
    }

    public void setDemand(Integer[] demand) {
        this.demand = demand;
    }
}
