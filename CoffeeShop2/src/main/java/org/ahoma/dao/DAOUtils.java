package org.ahoma.dao;

public class DAOUtils {
  public static final String FIND_BY_ID_QUERY = "select * from %s where id = ?";
  public static final String FIND_ALL_QUERY = "select * from %s";
  public static final String DELETE_BY_ID_QUERY = "delete from %s where id = ?";
}
