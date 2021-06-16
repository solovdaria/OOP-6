package org.ahoma.config;

import org.ahoma.dao.*;
import org.ahoma.service.*;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
  private static Map<Class<?>, Object> beans = new HashMap<>();

  static {
    AgentDAO dao = new AgentDAO();
    AgentService service = new AgentService(dao);
    beans.put(AgentDAO.class, dao);
    beans.put(AgentService.class, service);

    ShopDAO agencyDAO = new ShopDAO();
    ShopService agencyService = new ShopService(agencyDAO);
    beans.put(ShopDAO.class, agencyDAO);
    beans.put(ShopService.class, agencyService);

    UserDAO userDAO = new UserDAO();
    UserService userService = new UserService(userDAO);
    beans.put(UserDAO.class, userDAO);
    beans.put(UserService.class, userService);

    CoffeeDAO tourDAO = new CoffeeDAO();
    CoffeeService tourService = new CoffeeService(tourDAO);
    beans.put(CoffeeDAO.class, dao);
    beans.put(CoffeeService.class, tourService);

    OrderDAO orderDAO = new OrderDAO();
    OrderService orderService = new OrderService(orderDAO);
    beans.put(OrderDAO.class, orderDAO);
    beans.put(OrderService.class, orderService);
  }

  public static Object getBean(Class<?> beanClass) {
    return beans.get(beanClass);
  }
}
