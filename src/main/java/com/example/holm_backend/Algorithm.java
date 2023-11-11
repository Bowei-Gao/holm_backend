package com.example.holm_backend;

import java.util.*;

public class Algorithm {
    private List<LinkedList<Integer>> assignment = new ArrayList<>();
    private List<Integer> inventories = new ArrayList<>();
    private List<Integer> demands = new ArrayList<>();
    private List<LinkedList<Double>> distances = new ArrayList<>();

    public Algorithm(Double[][] distances, Integer[] inventory, Integer[] demand) {
        for (Double[] distance : distances) {
            LinkedList<Double> d = new LinkedList<>();
            Collections.addAll(d, distance);
            this.distances.add(d);
        }
        Collections.addAll(this.inventories, inventory);
        Collections.addAll(this.demands, demand);
    }

    public List<LinkedList<Integer>> getAssignment() {
        return assignment;
    }
}
