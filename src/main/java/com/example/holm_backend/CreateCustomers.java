package com.example.holm_backend;

public class CreateCustomers {
    private String[] names = new String[]{};
    private Double[] x = new Double[]{};

    private Double[] y = new Double[]{};

    private Integer[] demand = new Integer[]{};

    public CreateCustomers(String[] names, Double[] x, Double[] y, Integer[] demand) {
        this.names = names;
        this.x = x;
        this.y = y;
        this.demand = demand;
    }
}
