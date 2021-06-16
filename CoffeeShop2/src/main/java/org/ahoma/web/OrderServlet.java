package org.ahoma.web;

import org.ahoma.config.BeanFactory;
import org.ahoma.data.Coffee;
import org.ahoma.exception.ValidationException;
import org.ahoma.service.OrderService;
import org.ahoma.service.CoffeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends HttpServlet {
  private OrderService service = (OrderService) BeanFactory.getBean(OrderService.class);
  private CoffeeService tourService = (CoffeeService) BeanFactory.getBean(CoffeeService.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String delete = req.getParameter("delete");
    if (delete != null) {
      service.deleteById(Long.parseLong(delete));
    }
    req.setAttribute("orders", service.findAll());
    req.getRequestDispatcher("order.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String sUserId = req.getParameter("user_id");
    String sAgentId = req.getParameter("agent_id");
    String sTourId = req.getParameter("tour_id");
    String sCost = req.getParameter("cost");

    if (!ValidationUtils.validateInt(sUserId)) {
      throw new ValidationException("User id is not valid");
    } else if (!ValidationUtils.validateInt(sAgentId)) {
      throw new ValidationException("agency id is not valid");
    } else if (!ValidationUtils.validateInt(sTourId)) {
      throw new ValidationException("Tour id is not valid");
    } else if (!ValidationUtils.validateInt(sCost)) {
      throw new ValidationException("Cost id is not valid");
    } else {
      Long userId = Long.parseLong(sUserId);
      Long agentId = Long.parseLong(sAgentId);
      Long tourId = Long.parseLong(sTourId);
      Long cost = Long.parseLong(sCost);

      Coffee tour = tourService.findById(tourId).orElse(null);
      if (tour == null) {
        throw new ValidationException("Coffee not found");
      } else if (cost < tour.getCost()) {
        throw new ValidationException("Coffee cost is lower");
      }

      service.createOrder(userId, agentId, tourId, cost);

      req.setAttribute("orders", service.findAll());
      req.getRequestDispatcher("order.jsp").forward(req, resp);
    }
  }
}
