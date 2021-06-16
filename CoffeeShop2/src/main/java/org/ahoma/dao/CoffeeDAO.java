package org.ahoma.dao;

import org.ahoma.config.DBConnectionPool;
import org.ahoma.data.Shop;
import org.ahoma.data.Coffee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoffeeDAO implements DAO<Coffee, Long> {
  private DBConnectionPool connectionPool;

  public CoffeeDAO() {
    try {
      this.connectionPool = DBConnectionPool.getInstance();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Coffee save(Coffee tour) {
    Connection connection = connectionPool.getConnection();
    try {
      String query =
              String.format(
                      "insert into %s (%s, %s, %s, %s) values (?, ?, ?, ?)",
                      Coffee.DB_NAME,
                      Coffee.Columns.NAME,
                      Coffee.Columns.DESCRIPTION,
                      Coffee.Columns.AGENCY_ID,
                      Coffee.Columns.COST);
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, tour.getName());
      statement.setString(2, tour.getDescription());
      statement.setLong(3, tour.getAgency());
      statement.setLong(4, tour.getCost());
      statement.executeUpdate();
      statement.close();
      return tour;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public Optional<Coffee> findById(Long aLong) {
    Connection connection = connectionPool.getConnection();
    String query = String.format(DAOUtils.FIND_BY_ID_QUERY, Coffee.DB_NAME);
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setLong(1, aLong);
      try (ResultSet res = statement.executeQuery()) {
        if (res.next()) {
          return Optional.of(map(res));
        }
        return Optional.empty();
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  @Override
  public List<Coffee> findAll() {
    List<Coffee> tours = new ArrayList<>();
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.FIND_ALL_QUERY, Coffee.DB_NAME);
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet res = statement.executeQuery();
      while (res.next()) {
        tours.add(map(res));
      }
      statement.close();
      return tours;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      connectionPool.releaseConnection(connection);
    }
  }

  private Coffee map(ResultSet resultSet) throws SQLException {
    if (resultSet == null) {
      return null;
    }
    Coffee tour = new Coffee();
    tour.setId(resultSet.getLong(Coffee.Columns.ID));
    tour.setAgency(resultSet.getLong(Coffee.Columns.AGENCY_ID));
    tour.setCost(resultSet.getLong(Coffee.Columns.COST));
    tour.setName(resultSet.getString(Coffee.Columns.NAME));
    tour.setDescription(resultSet.getString(Coffee.Columns.DESCRIPTION));
    return tour;
  }

  @Override
  public void deleteById(Long aLong) {
    Connection connection = connectionPool.getConnection();
    try {
      String query = String.format(DAOUtils.DELETE_BY_ID_QUERY, Coffee.DB_NAME);
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