package org.ahoma.web;

import org.ahoma.config.BeanFactory;
import org.ahoma.exception.ValidationException;
import org.ahoma.service.CoffeeService;
import org.ahoma.service.UserService;
import org.ahoma.util.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CoffeeServlet extends HttpServlet {
  private CoffeeService service = (CoffeeService) BeanFactory.getBean(CoffeeService.class);
  private UserService userService = (UserService) BeanFactory.getBean(UserService.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String delete = req.getParameter("delete");
    if (delete != null) {
      service.deleteById(Long.parseLong(delete));
    }
    req.setAttribute("tours", service.findAll());
    req.setAttribute("admin", userService.isAdmin(RequestUtils.getCookie(req, "username")));
    req.getRequestDispatcher("tour.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String name = req.getParameter("tour_name");
    String description = req.getParameter("tour_description");
    String sAgency = req.getParameter("tour_agency");
    String sCost = req.getParameter("tour_cost");

    if (!ValidationUtils.validateString(name)) {
      throw new ValidationException("Tour name is not valid");
    } else if (!ValidationUtils.validateString(description)) {
      throw new ValidationException("Tour description is not valid");
    } else if (!ValidationUtils.validateInt(sAgency)) {
      throw new ValidationException("Agency name is not valid");
    } else if (!ValidationUtils.validateInt(sCost)) {
      throw new ValidationException("Cost is not valid");
    } else {
      Long agency = Long.parseLong(sAgency);
      Long cost = Long.parseLong(sCost);
      service.createTour(description, name, agency, cost);
      req.setAttribute("tours", service.findAll());
      req.setAttribute("admin", userService.isAdmin(RequestUtils.getCookie(req, "username")));
      req.getRequestDispatcher("tour.jsp").forward(req, resp);
    }
  }
}