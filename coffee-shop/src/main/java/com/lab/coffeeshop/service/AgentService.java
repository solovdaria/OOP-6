package com.lab.coffeeshop.service;

import com.lab.coffeeshop.model.Agent;
import com.lab.coffeeshop.repository.AgentRepository;
import com.lab.coffeeshop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentService {

    private final AgentRepository agentRepository;
    private final ShopRepository shopRepository;

    public void create(String name, Long agencyId) {
        Agent agent = new Agent();
        agent.setAgentName(name);
        agent.setShop(shopRepository.getOne(agencyId));
        agentRepository.save(agent);
    }

    public List<Agent> findAll() {
        return agentRepository.findAll();
    }

    public void deleteById(Long id) {
        if (agentRepository.existsById(id)) {
            agentRepository.deleteById(id);
        }
    }
}
