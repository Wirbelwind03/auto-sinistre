package com.github.wirbelwind03.autosinistre.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "VEHICLES")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int year;
}
