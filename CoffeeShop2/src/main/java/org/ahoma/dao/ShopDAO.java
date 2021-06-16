package org.ahoma.dao;

import org.ahoma.config.DBConnectionPool;
import org.ahoma.data.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShopDAO implements DAO<Shop, Long> {
  private DBConnectionPool connectionPool;

  public ShopDAO() {
    try {
      this.connectionPool = DBConnectionPool.getInstance();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Shop save(Shop agency) {
    Connection connection = connectionPool.getConnection();
    try {
      String query =
              String.format("insert into %s (%s) value (?)", Shop.DB_NAME, Shop.Columns.NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, agency.getAgencyName());
      statement.executeUpdate();
      statement.close();
      return agency;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  private Shop map(ResultSet resultSet) throws SQLException {
    if (resultSet == null) {
      return null;
    }
    Shop agency = new Shop();
    agency.setId(resultSet.getLong(Shop.Columns.ID));
    agency.setAgencyName(resultSet.getString(Shop.Columns.NAME));
    System.out.println(agency.getAgencyName());
    return agency;
  }

  @Override
  public Optional<Shop> findById(Long aLong) {
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.FIND_BY_ID_QUERY, Shop.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, aLong);
      ResultSet res = statement.executeQuery();
      res.next();
      Shop agency = map(res);
      statement.close();
      return Optional.ofNullable(agency);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public List<Shop> findAll() {
    List<Shop> agencies = new ArrayList<>();
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.FIND_ALL_QUERY, Shop.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet res = statement.executeQuery();
      while (res.next()) {
        agencies.add(map(res));
      }
      statement.close();
      System.out.println(agencies);
      return agencies;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public void deleteById(Long aLong) {
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.DELETE_BY_ID_QUERY, Shop.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, aLong);
      statement.executeUpdate();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }
}