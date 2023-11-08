package com.example.holm_backend;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class ApiController {
    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/strategic")
    public Greeting strategic(@RequestBody StrategicInput input) {
        int variable_costs_per_km = input.getVariable_costs_per_km();
        int[] fixed_costs = input.getFixed_costs();
        int[][] distances = input.getDistances();
        AddAlgorithm addAlgorithm = new AddAlgorithm(fixed_costs ,distances);
        int[] result_add_algorithm = addAlgorithm.getStrategic();
        return new Greeting(counter.incrementAndGet(), result_add_algorithm);
    }
}
