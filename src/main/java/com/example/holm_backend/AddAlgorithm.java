package com.example.holm_backend;

import java.util.HashSet;
import java.util.Set;

public class AddAlgorithm {
    Set<Integer> solutions = new HashSet<>();
    Set<Integer> warehouses = new HashSet<>();
    int[][] costMatrix;
    int[] assignments;

    private int[] fixedCosts;
    private int[][] distances;
    private int number_of_locations;
    private double[] variable_cost_rates;
    private double[] weightings;

    public AddAlgorithm(int[] fixedCosts,int[][] distances, double[] variable_cost_rates, double[] weightings) {
        this.fixedCosts = fixedCosts;
        this.distances = distances;
        this.number_of_locations = fixedCosts.length;
        this.variable_cost_rates = variable_cost_rates;
        this.weightings = weightings;
        initialize();
    }

    public int[] getStrategic() {
        while (!warehouses.isEmpty()) {
            int[] costSavings = getCostSavings();
            eliminateLocations(costSavings);
            if (!warehouses.isEmpty()) {
                int currentIteration = getMaxIndex(costSavings);
                solutions.add(currentIteration);
                warehouses.remove(currentIteration);
                updateAssignment(currentIteration);
            }
        }

        int[] result_add_algorithm = solutions.stream().mapToInt(Integer::intValue).toArray();

        return result_add_algorithm;
    }

    void initialize() {
        for (int i = 0; i < number_of_locations; i++) {
            warehouses.add(i);
        }

        for (int i = 0; i < distances.length; i ++) {
            for (int j = 0; j < distances[0].length; j ++) {
                this.distances[i][j] = (int)(this.distances[i][j] * this.variable_cost_rates[j] * this.weightings[j]);
            }
        }

        costMatrix = getCostMatrix(distances);

        assignments = new int[number_of_locations];
        int[] costs = new int[number_of_locations];
        for (int i = 0; i < 5; i++) {
            costs[i] = sum(costMatrix[i]) + fixedCosts[i];
        }
        int firstIteration = getMinIndex(costs);
        assignments = new int[]{firstIteration, firstIteration, firstIteration, firstIteration, firstIteration};
        solutions.add(firstIteration);
        warehouses.remove(firstIteration);
    }

    int[][] getCostMatrix(int[][] distances) {
        int I = distances[0].length;
        int J = distances.length;
        int[][] costMatrix = new int[I][J];
        for (int i = 0; i < I; i++) {
            for (int j = 0; j < J; j++) {
                costMatrix[i][j] = getTransportationCost(distances[i][j]);
            }
        }
        return costMatrix;
    }

    int getTransportationCost(int distance) {
        return distance <= 555 ? 50000 : (int) (distance * 90);
    }

    int[] getCostSavings() {
        int I = costMatrix[0].length;
        int J = costMatrix.length;
        int[] costSavings = new int[I];
        for (int i : warehouses) {
            for (int j = 0; j < J; j++) {
                int currentCost = costMatrix[assignments[j]][j];
                int warehouseCost = costMatrix[i][j];
                costSavings[i] += currentCost > warehouseCost ? (currentCost - warehouseCost) : 0;
            }
            costSavings[i] -= fixedCosts[i];
        }
        return costSavings;
    }

    void eliminateLocations(int[] costSavings) {
        Set<Integer> eliminatedLocations = new HashSet<>();
        for (int i : warehouses) {
            if (costSavings[i] < 0) {
                eliminatedLocations.add(i);
            }
        }
        warehouses.removeAll(eliminatedLocations);
    }

    void updateAssignment(int i) {
        for (int j = 0; j < assignments.length; j++) {
            int currentCost = costMatrix[assignments[j]][j];
            int warehouseCost = costMatrix[i][j];
            assignments[j] = currentCost > warehouseCost ? i : assignments[j];
        }
    }

    int sum(int[] array) {
        int total = 0;
        for (int num : array) {
            total += num;
        }
        return total;
    }

    int getMinIndex(int[] array) {
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    int getMaxIndex(int[] array) {
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
