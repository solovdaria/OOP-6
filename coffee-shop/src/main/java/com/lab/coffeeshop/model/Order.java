package com.lab.coffeeshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cs_order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cost;

    @ManyToOne
    private Coffee coffee;

    @ManyToOne
    private User user;

    @ManyToOne
    private Agent agent;
}
