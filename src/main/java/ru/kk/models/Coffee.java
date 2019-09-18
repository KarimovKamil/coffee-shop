package ru.kk.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.Digits;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "coffee_sort", unique = true)
    private String sort;
    @Column(name = "coffee_count")
    private long count;
    @Column(name = "coffee_cost")
    private int cost;
}
