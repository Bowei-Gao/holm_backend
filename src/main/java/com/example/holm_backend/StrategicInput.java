package com.example.holm_backend;

public class StrategicInput {
    private int variable_costs_per_km;
    private int[] fixed_costs;
    private int[][] distances;
    private double[] variable_cost_rates;
    private double[] weightings;

    public int getVariable_costs_per_km() {
        return variable_costs_per_km;
    }

    public void setVariable_costs_per_km(int variable_costs_per_km) {
        this.variable_costs_per_km = variable_costs_per_km;
    }

    public int[] getFixed_costs() {
        return fixed_costs;
    }

    public void setFixed_costs(int[] fixed_costs) {
        this.fixed_costs = fixed_costs;
    }

    public int[][] getDistances() {
        return distances;
    }

    public void setDistances(int[][] distances) {
        this.distances = distances;
    }

    public double[] getVariable_cost_rates() {
        return variable_cost_rates;
    }

    public void setVariable_cost_rates(double[] variable_cost_rates) {
        this.variable_cost_rates = variable_cost_rates;
    }

    public double[] getWeightings() {
        return weightings;
    }

    public void setWeightings(double[] weightings) {
        this.weightings = weightings;
    }
}
