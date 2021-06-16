package com.lab.coffeeshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cs_agency")
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agencyName;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agent> agents = new ArrayList<>();

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coffee> coffees = new ArrayList<>();

    public void addAgent(Agent agent) {
        agents.add(agent);
        agent.setShop(this);
    }

    public void addCoffee(Coffee coffee) {
        coffees.add(coffee);
        coffee.setShop(this);
    }
}
