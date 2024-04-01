package com.example.Java_Project_Modul_1.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Measurement_Current")
public class Measurement_Current {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private double Time;
    private double ia;
    private double ib;
    private double ic;
}