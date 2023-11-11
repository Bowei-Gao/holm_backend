package com.example.holm_backend;

import java.util.LinkedList;
import java.util.List;

public record PlanningOutput(long id, List<LinkedList<Integer>> content) { }
