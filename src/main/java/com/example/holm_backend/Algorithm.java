package com.example.holm_backend;

import java.util.*;

public class Algorithm {
    private List<LinkedList<Integer>> assignments = new ArrayList<>();
    private List<Integer> inventories = new ArrayList<>();
    private List<Integer> demands = new ArrayList<>();
    private List<LinkedList<Integer>> distances = new ArrayList<>();
    private List<Integer> differencesOfColumns = new ArrayList<>();
    private List<Integer> differencesOfRows = new ArrayList<>();

    public Algorithm(Integer[][] distances, Integer[] inventory, Integer[] demand) {
        for (Integer[] distance : distances) {
            LinkedList<Integer> d = new LinkedList<>();
            Collections.addAll(d, distance);
            this.distances.add(d);
        }
        Collections.addAll(this.inventories, inventory);
        Collections.addAll(this.demands, demand);

        for (int i = 0; i < this.inventories.size(); i++) {
            this.differencesOfRows.add(0);
        }

        for (int i = 0; i < this.demands.size(); i++) {
            this.differencesOfColumns.add(0);
        }
    }

    public int argmin(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return -1;
        }

        Integer minValue = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < minValue) {
                minValue = list.get(i);
                minIndex = i;
            }
        }

        return minIndex;
    }

    public int[] findIndicesOfLowestElements(List<Integer> lst) {

        int minIndex = this.argmin(lst);
        int secondMinIndex;
        if (lst.size() > 1) {
            List<Integer> list = new ArrayList<>(lst);
            list.remove(minIndex);
            secondMinIndex = this.argmin(list);
            if (secondMinIndex >= minIndex) {
                secondMinIndex += 1;
            }
        } else {
            secondMinIndex = minIndex;
        }

        return new int[]{minIndex, secondMinIndex};
    }

    private void eraseColumn(int columnIdx) {
        this.demands.remove(columnIdx);
        this.differencesOfColumns.remove(columnIdx);
        for (List<Integer> row : this.distances) {
            row.remove(columnIdx);
        }
    }

    private void eraseRow(int rowIdx) {
        this.inventories.remove(rowIdx);
        this.differencesOfRows.remove(rowIdx);
        this.distances.remove(rowIdx);
    }

    private void updateDifferenceOfRows() {
        for (int i = 0; i < this.inventories.size(); i++) {
            List<Integer> row = this.distances.get(i);
            int[] indices = this.findIndicesOfLowestElements(row);
            this.differencesOfRows.set(i, row.get(indices[1]) - row.get(indices[0]));
        }
    }

    private void updateDifferenceOfColumns() {
        for (int i = 0; i < this.demands.size(); i++) {
            List<Integer> column = new ArrayList<>();
            for (List<Integer> row : distances) {
                column.add(row.get(i));
            }
            int[] indices = findIndicesOfLowestElements(column);
            differencesOfColumns.set(i, column.get(indices[1]) - column.get(indices[0]));
        }
    }

    private int[] findIndicesOfMinValueRow(int maxDifferencesOfRows) {
        int minVal = Integer.MAX_VALUE;
        int columnIdx = -1;
        int rowIdx = -1;
        for (int i = 0; i < this.differencesOfRows.size(); i++) {
            if (this.differencesOfRows.get(i).equals(maxDifferencesOfRows)) {
                for (int j = 0; j < this.distances.get(i).size(); j++) {
                    if (this.distances.get(i).get(j) < minVal) {
                        minVal = this.distances.get(i).get(j);
                        columnIdx = j;
                        rowIdx = i;
                    }
                }
            }
        }
        return new int[]{columnIdx, rowIdx};
    }

    private int[] findIndicesOfMinValueColumn(int maxDifferencesOfColumns) {
        int minVal = Integer.MAX_VALUE;
        int columnIdx = -1;
        int rowIdx = -1;
        for (int i = 0; i < this.differencesOfColumns.size(); i++) {
            if (this.differencesOfColumns.get(i).equals(maxDifferencesOfColumns)) {
                for (int j = 0; j < this.distances.size(); j++) {
                    if (this.distances.get(j).get(i) < minVal) {
                        minVal = this.distances.get(j).get(i);
                        columnIdx = i;
                        rowIdx = j;
                    }
                }
            }
        }
        return new int[]{columnIdx, rowIdx};
    }

    private int[] maxIndices() {
        int maxDifferencesOfColumns = this.differencesOfColumns.stream().max(Integer::compareTo).orElse(0);
        int maxDifferencesOfRows = this.differencesOfRows.stream().max(Integer::compareTo).orElse(0);
        if (maxDifferencesOfColumns > maxDifferencesOfRows) {
            return findIndicesOfMinValueColumn(maxDifferencesOfColumns);
        } else if (maxDifferencesOfColumns < maxDifferencesOfRows) {
            return findIndicesOfMinValueRow(maxDifferencesOfRows);
        } else {
            int[] indicesColumn = findIndicesOfMinValueColumn(maxDifferencesOfColumns);
            int[] indicesRow = findIndicesOfMinValueRow(maxDifferencesOfRows);
            if (this.distances.get(indicesRow[1]).get(indicesRow[0]) < this.distances.get(indicesColumn[1]).get(indicesColumn[0])) {
                return indicesRow;
            } else {
                return indicesColumn;
            }
        }
    }

    public List<LinkedList<Integer>> getAssignments() {
        while (this.inventories.size() > 0) {
            this.updateDifferenceOfRows();
            this.updateDifferenceOfColumns();

            /*for (Integer d: this.differencesOfRows) {
                System.out.print(d);
                System.out.print(' ');
            }
            System.out.println();*/

            int[] indices = maxIndices();
            int columnIdx = indices[0];
            int rowIdx = indices[1];

            LinkedList<Integer> assignment = new LinkedList<>();
            assignment.add(rowIdx);
            assignment.add(columnIdx);
            this.assignments.add(assignment);
            // assignments1.add(Math.min(this.inventories.get(rowIdx), this.demands.get(columnIdx)));

            if (this.inventories.get(rowIdx) < this.demands.get(columnIdx)) {
                this.demands.set(columnIdx, this.demands.get(columnIdx) - this.inventories.get(rowIdx));
                eraseRow(rowIdx);
            } else if (this.inventories.get(rowIdx) > this.demands.get(columnIdx)) {
                this.inventories.set(rowIdx, this.inventories.get(rowIdx) - this.demands.get(columnIdx));
                eraseColumn(columnIdx);
            } else {
                eraseRow(rowIdx);
                eraseColumn(columnIdx);
            }
        }

        return this.assignments;
    }
}
