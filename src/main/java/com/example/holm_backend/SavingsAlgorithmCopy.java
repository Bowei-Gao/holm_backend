package com.example.holm_backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SavingsAlgorithmCopy {
    private List<Integer> deliveryQuantities;
    private List<Integer> pick_up_quantities;
    private List<Integer> max_quantities;
    private int n;
    private List<LinkedList<Integer>> routes;
    private List<Double> dists;
    private List<LinkedList<Double>> distances;
    private List<LinkedList<Double>> savings;
    private int maxListIndex;
    private int maxElementIndex;
    private int loading_capacity;

    public SavingsAlgorithmCopy(int loading_capacity, Integer[] delivery_quantities, Integer[] pick_up_quantities, Double[][] distances) {
        this.loading_capacity = loading_capacity;

        this.deliveryQuantities = new ArrayList<>();
        this.pick_up_quantities = new ArrayList<>();
        this.max_quantities = new ArrayList<>();

        this.deliveryQuantities.addAll(Arrays.asList(delivery_quantities));

        this.pick_up_quantities.addAll(Arrays.asList(pick_up_quantities));

        for (int i = 0; i < deliveryQuantities.size(); i++) {
            this.max_quantities.add(Math.max(this.deliveryQuantities.get(i), this.pick_up_quantities.get(i)));
        }

        this.dists = new ArrayList<>();
        for (int i = 1; i < distances[0].length; i++) {
            this.dists.add(distances[0][i]);
        }

        this.distances = new ArrayList<>();
        for (int i = 1; i < distances.length; i++) {
            this.distances.add(new LinkedList<>());
            for (int j = 1; j < distances.length; j++) {
                if (i <= j) {
                    this.distances.get(i - 1).add(distances[i][j]);
                }
                else {
                    this.distances.get(i - 1).add(distances[j][i]);
                }
            }
        }
    }

    private void initRoutes() {
        this.routes = new ArrayList<>();
        for (int i = 1; i <= this.n; i++) {
            LinkedList<Integer> route = new LinkedList<>();
            route.add(i);
            this.routes.add(route);
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

    public void updateMaxQuantities() {
        int head_max = this.max_quantities.get(this.maxListIndex) + this.deliveryQuantities.get(this.maxElementIndex);
        int tail_max = this.max_quantities.get(this.maxElementIndex) + this.pick_up_quantities.get(this.maxListIndex);
        this.max_quantities.add(Math.max(head_max, tail_max));

        if (this.maxListIndex > this.maxElementIndex) {
            this.max_quantities.remove(this.maxListIndex);
            this.max_quantities.remove(this.maxElementIndex);
        } else {
            this.max_quantities.remove(this.maxElementIndex);
            this.max_quantities.remove(this.maxListIndex);
        }
    }

    public int getMaxQuantities() {
        int head_max = this.max_quantities.get(this.maxListIndex) + this.deliveryQuantities.get(this.maxElementIndex);
        int tail_max = this.max_quantities.get(this.maxElementIndex) + this.pick_up_quantities.get(this.maxListIndex);
        return Math.max(head_max, tail_max);
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

    public void updatePickUpQuantities() {
        this.pick_up_quantities.add(this.pick_up_quantities.get(this.maxListIndex) + this.pick_up_quantities.get(this.maxElementIndex));

        if (this.maxListIndex > this.maxElementIndex) {
            this.pick_up_quantities.remove(this.maxListIndex);
            this.pick_up_quantities.remove(this.maxElementIndex);
        } else {
            this.pick_up_quantities.remove(this.maxElementIndex);
            this.pick_up_quantities.remove(this.maxListIndex);
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

        this.getSavings();
        this.argmax(this.savings);

        while (this.savings.get(this.maxListIndex).get(this.maxElementIndex) >= 0) {
            this.updateRoutes();
            this.updateMaxQuantities();
            this.updateDeliveryQuantities();
            this.updatePickUpQuantities();
            this.updateSavings();

            this.argmax(this.savings);

            while (this.savings.get(this.maxListIndex).get(this.maxElementIndex) >= 0 && this.getMaxQuantities() > this.loading_capacity) {
                this.savings.get(this.maxListIndex).set(this.maxElementIndex, Double.NEGATIVE_INFINITY);
                this.savings.get(this.maxElementIndex).set(this.maxListIndex, Double.NEGATIVE_INFINITY);
                this.argmax(this.savings);
            }
        }

        return this.routes;
    }
}