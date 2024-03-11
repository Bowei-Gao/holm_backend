package com.example.holm_backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SavingsAlgorithm {

    static class Coordinate {
        Double x;
        Double y;

        Coordinate(Double x, Double y) {
            this.x = x;
            this.y = y;
        }
    }

    private List<Coordinate> coordinates;
    private List<Integer> deliveryQuantities;
    private int n;
    private List<LinkedList<Integer>> routes;
    private List<Double> dists;
    private List<LinkedList<Double>> distances;
    private List<LinkedList<Double>> savings;
    private int maxListIndex;
    private int maxElementIndex;
    private int loading_capacity;
    private Double[][] ds;

    public SavingsAlgorithm(int loading_capacity, Integer[] delivery_quantities, Double[] x, Double[] y, Double[][] distances) {
        this.loading_capacity = loading_capacity;

        this.coordinates = new ArrayList<>();
        this.deliveryQuantities = new ArrayList<>();

        this.coordinates.add(null); // padding to start index from 1
        for (int i=0; i<x.length; i++) {
            this.coordinates.add(new Coordinate(x[i], y[i]));
        }

        for (int i=0; i<delivery_quantities.length; i++) {
            this.deliveryQuantities.add(delivery_quantities[i]);
        }

        this.ds = distances;
    }

    private void initRoutes() {
        this.routes = new ArrayList<>();
        for (int i = 1; i <= this.n; i++) {
            LinkedList<Integer> route = new LinkedList<>();
            route.add(i);
            this.routes.add(route);
        }
    }

    public double getDistance(Coordinate c0, Coordinate c1) {
        return Math.sqrt(Math.pow(c0.x - c1.x, 2) + Math.pow(c0.y - c1.y, 2));
    }

    public void getDists() {
        this.dists = new ArrayList<>();
        for (int i = 1; i <= this.n; i++) {
            this.dists.add(this.getDistance(this.coordinates.get(i), new Coordinate(0.0, 0.0)));
        }
    }

    public void getDistances() {
        this.distances = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            this.distances.add(new LinkedList<>());
            for (int j = 0; j < n; j++) {
                this.distances.get(i).add(this.getDistance(this.coordinates.get(this.routes.get(i).get(this.routes.get(i).size()-1)), this.coordinates.get(this.routes.get(j).get(0))));
            }
        }
    }

    public void getSavings() {
        this.savings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            this.savings.add(new LinkedList<>());
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    this.savings.get(i).add(Double.NEGATIVE_INFINITY);
                } else {
                    this.savings.get(i).add(this.dists.get(i) + this.dists.get(j) - this.distances.get(i).get(j));
                }
            }
        }
    }

    public void argmax(List<LinkedList<Double>> l) {
        this.maxListIndex = -1;
        this.maxElementIndex = -1;
        Double maxElement = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < l.size(); i++) {
            LinkedList<Double> currentList = l.get(i);
            for (int j = 0; j < currentList.size(); j++) {
                Double currentElement = currentList.get(j);
                if (currentElement > maxElement) {
                    maxElement = currentElement;
                    this.maxListIndex = i;
                    this.maxElementIndex = j;
                }
            }
        }

        if (this.maxListIndex == -1 || this.maxElementIndex == -1) {
            this.maxListIndex = 0;
            this.maxElementIndex = 0;
        }
    }

    public void updateRoutes() {
        LinkedList<Integer> newRoute = new LinkedList<>(this.routes.get(this.maxListIndex));
        newRoute.addAll(this.routes.get(this.maxElementIndex));
        this.routes.add(newRoute);

        if (this.maxListIndex > this.maxElementIndex) {
            this.routes.remove(this.maxListIndex);
            this.routes.remove(this.maxElementIndex);
        } else {
            this.routes.remove(this.maxElementIndex);
            this.routes.remove(this.maxListIndex);
        }
    }

    public void updateDeliveryQuantities() {
        this.deliveryQuantities.add(this.deliveryQuantities.get(this.maxListIndex) + this.deliveryQuantities.get(this.maxElementIndex));

        if (this.maxListIndex > this.maxElementIndex) {
            this.deliveryQuantities.remove(this.maxListIndex);
            this.deliveryQuantities.remove(this.maxElementIndex);
        } else {
            this.deliveryQuantities.remove(this.maxElementIndex);
            this.deliveryQuantities.remove(this.maxListIndex);
        }
    }

    public void updateSavings() {
        this.savings.add(new LinkedList<>(this.savings.get(this.maxElementIndex)));

        for (LinkedList<Double> s : this.savings) {
            s.add(s.get(this.maxListIndex));
        }

        this.savings.get(this.savings.size()-1).set(this.savings.get(0).size()-1, Double.NEGATIVE_INFINITY);

        if (this.maxListIndex > this.maxElementIndex) {
            this.savings.remove(this.maxListIndex);
            this.savings.remove(this.maxElementIndex);
            for (LinkedList<Double> s : this.savings) {
                s.remove(this.maxListIndex);
                s.remove(this.maxElementIndex);
            }
        } else {
            this.savings.remove(this.maxElementIndex);
            this.savings.remove(this.maxListIndex);
            for (LinkedList<Double> s : this.savings) {
                s.remove(this.maxElementIndex);
                s.remove(this.maxListIndex);
            }
        }
    }

    public List<LinkedList<Integer>> getRoutes() {
        this.n = this.deliveryQuantities.size();
        initRoutes();

        this.getDists();
        this.getDistances();
        this.getSavings();
        this.argmax(this.savings);

        while (this.savings.get(this.maxListIndex).get(this.maxElementIndex) >= 0) {
            this.updateRoutes();
            this.updateDeliveryQuantities();
            this.updateSavings();

            this.argmax(this.savings);

            while (this.savings.get(this.maxListIndex).get(this.maxElementIndex) >= 0 && this.deliveryQuantities.get(this.maxListIndex) + this.deliveryQuantities.get(this.maxElementIndex) > this.loading_capacity) {
                this.savings.get(this.maxListIndex).set(this.maxElementIndex, Double.NEGATIVE_INFINITY);
                this.savings.get(this.maxElementIndex).set(this.maxListIndex, Double.NEGATIVE_INFINITY);
                this.argmax(this.savings);
            }
        }

        return this.routes;
    }
}