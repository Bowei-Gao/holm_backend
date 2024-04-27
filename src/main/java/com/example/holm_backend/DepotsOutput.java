package com.example.holm_backend;

import java.util.ArrayList;

public record DepotsOutput(long id, ArrayList<DepotEntity> depots) {}
