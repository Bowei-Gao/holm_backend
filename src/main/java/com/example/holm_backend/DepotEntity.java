package com.example.holm_backend;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class DepotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    int gender;
    int grade;
    int score;
}
