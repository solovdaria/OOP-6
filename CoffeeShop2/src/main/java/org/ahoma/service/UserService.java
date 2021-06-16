package org.ahoma.service;

import org.ahoma.dao.UserDAO;
import org.ahoma.data.User;
import org.ahoma.exception.ValidationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserService {

  private static final List<String> ADMIN_USERNAMES =
          Collections.unmodifiableList(Arrays.asList("Admin", "admin"));

  private UserDAO dao;

  public UserService(UserDAO dao) {
    this.dao = dao;
  }

  public void createUser(String name, String password) {
    User user = new User();
    user.setUserName(name);
    user.setPassword(password);
    dao.save(user);
  }

  public User loginOrRegister(String name, String pwd) {
    User user = dao.findByName(name);
    if (user == null) {
      createUser(name, pwd);
      return dao.findByName(name);
    } else {
      if (pwd.isEmpty() || !pwd.equals(user.getPassword())) {
        throw new ValidationException("User password is not valid");
      }
      return user;
    }
  }

  public List<User> findUsers() {
    return dao.findAll();
  }

  public void deleteById(Long id) {
    dao.deleteById(id);
  }

  public boolean isAdmin(String username) {
    return ADMIN_USERNAMES.contains(username);
  }
}
