package com.lab.coffeeshop.repository;

import com.lab.coffeeshop.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}
