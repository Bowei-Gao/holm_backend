package com.example.holm_backend;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
// @CrossOrigin(origins = "http://localhost:5173")
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

    @PostMapping("/assignment")
    public AssignmentOutput assignment(@RequestBody AssignmentInput input) {
        Integer[][] distances = input.getDistances();
        Integer[] inventory = input.getInventory();
        Integer[] demand = input.getDemand();
        Algorithm algorithm = new Algorithm(distances, inventory, demand);
        List<LinkedList<Integer>> result_algorithm = algorithm.getAssignments();
        return new AssignmentOutput(counter.incrementAndGet(), result_algorithm);
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

    @PostMapping("/depotsCreate")
    public DepotOutput depotsCreate(@RequestBody DepotInput input) throws Exception {
        String[] names = input.getNames();
        Double[] x = input.getX();
        Double[] y = input.getY();
        Integer[] capacities = input.getCapacities();
        Integer[] fixed_costs = input.getFixed_costs();
        CreateDepots createDepots = new CreateDepots(names, x, y, capacities, fixed_costs);
        int result_depotsCreate = createDepots.UpdateDatabase();
        return new DepotOutput(counter.incrementAndGet(), result_depotsCreate);
    }

    @PostMapping("/customersCreate")
    public CustomerOutput customersCreate(@RequestBody CustomerInput input) throws Exception {
        String[] names = input.getNames();
        Double[] x = input.getX();
        Double[] y = input.getY();
        Integer[] demand = input.getDemand();
        CreateCustomers createCustomers = new CreateCustomers(names, x, y, demand);
        int result_customersCreate = createCustomers.UpdateDatabase();
        return new CustomerOutput(counter.incrementAndGet(), result_customersCreate);
    }

    @PostMapping("/depotsDelete")
    public DepotOutput depotsDelete(@RequestBody DepotInput input) throws Exception {
        String[] names = input.getNames();
        /*DeleteDepots deleteDepots = new DeleteDepots(names);
        List<LinkedList<Integer>> result_savings_algorithm = savingsAlgorithm.getRoutes(); */
        return new DepotOutput(counter.incrementAndGet(), 0);
    }

    @PostMapping("/customersDelete")
    public CustomerOutput customersDelete(@RequestBody CustomerInput input) throws Exception {
        String[] names = input.getNames();
        /* DeleteCustomers deleteCustomers = new DeleteCustomers(names);
        List<LinkedList<Integer>> result_savings_algorithm = savingsAlgorithm.getRoutes(); */
        return new CustomerOutput(counter.incrementAndGet(), 0);
    }
}
