package com.example.holm_backend;

import java.util.ArrayList;

public record CustomersOutput(long id, ArrayList<CustomerEntity> customers) {
}
