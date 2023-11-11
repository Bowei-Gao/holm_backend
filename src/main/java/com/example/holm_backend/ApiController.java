package com.example.holm_backend;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class ApiController {
    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/strategic")
    public StrategicOutput strategic(@RequestBody StrategicInput input) {
        int variable_costs_per_km = input.getVariable_costs_per_km();
        int[] fixed_costs = input.getFixed_costs();
        int[][] distances = input.getDistances();
        AddAlgorithm addAlgorithm = new AddAlgorithm(fixed_costs ,distances);
        int[] result_add_algorithm = addAlgorithm.getStrategic();
        return new StrategicOutput(counter.incrementAndGet(), result_add_algorithm);
    }

    @PostMapping("/planning")
    public PlanningOutput planning(@RequestBody PlanningInput input) {
        int loading_capacity = input.getLoading_capacity();
        Integer[] delivery_quantities = input.getDeliveryQuantities();
        Double[] x = input.getX();
        Double[] y = input.getY();
        SavingsAlgorithm savingsAlgorithm = new SavingsAlgorithm(loading_capacity, delivery_quantities, x, y);
        List<LinkedList<Integer>> result_savings_algorithm = savingsAlgorithm.getRoutes();
        return new PlanningOutput(counter.incrementAndGet(), result_savings_algorithm);
    }
}
