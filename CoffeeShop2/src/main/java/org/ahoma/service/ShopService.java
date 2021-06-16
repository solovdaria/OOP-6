package org.ahoma.service;

import org.ahoma.dao.ShopDAO;
import org.ahoma.data.Shop;

import java.util.List;

public class ShopService {
  private ShopDAO dao;

  public ShopService(ShopDAO dao) {
    this.dao = dao;
  }

  public Shop createAgency(String name) {
    Shop agency = new Shop();
    agency.setAgencyName(name);
    dao.save(agency);
    return agency;
  }

  public List<Shop> findAllAgencies() {
    return dao.findAll();
  }

  public void deleteById(Long id) {
    dao.deleteById(id);
  }
}