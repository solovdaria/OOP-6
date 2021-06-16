package org.ahoma.service;

import org.ahoma.dao.CoffeeDAO;
import org.ahoma.data.Coffee;

import java.util.List;
import java.util.Optional;

public class CoffeeService {
  private CoffeeDAO dao;

  public CoffeeService(CoffeeDAO dao) {
    this.dao = dao;
  }

  public void createTour(String description, String name, Long agencyId, Long cost) {
    Coffee tour = new Coffee();
    tour.setDescription(description);
    tour.setAgency(agencyId);
    tour.setName(name);
    tour.setCost(cost);
    dao.save(tour);
  }

  public List<Coffee> findAll() {
    return dao.findAll();
  }

  public Optional<Coffee> findById(Long id) {
    return dao.findById(id);
  }

  public void deleteById(Long id) {
    dao.deleteById(id);
  }
}