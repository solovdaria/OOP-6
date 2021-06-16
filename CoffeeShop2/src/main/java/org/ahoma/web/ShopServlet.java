package org.ahoma.web;

import org.ahoma.config.BeanFactory;
import org.ahoma.exception.ValidationException;
import org.ahoma.service.ShopService;
import org.ahoma.service.UserService;
import org.ahoma.util.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShopServlet extends HttpServlet {
  private ShopService service = (ShopService) BeanFactory.getBean(ShopService.class);
  private UserService userService = (UserService) BeanFactory.getBean(UserService.class);

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String delete = req.getParameter("delete");
    if (delete != null) {
      service.deleteById(Long.parseLong(delete));
    }
    req.setAttribute("agencies", service.findAllAgencies());
    req.setAttribute("admin", userService.isAdmin(RequestUtils.getCookie(req, "username")));
    req.getRequestDispatcher("shop.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String name = req.getParameter("agency_name");
    if (!ValidationUtils.validateString(name)) {
      throw new ValidationException("Agency name is not valid");

    } else {
      service.createAgency(name);
      req.setAttribute("agencies", service.findAllAgencies());
      req.setAttribute("admin", userService.isAdmin(RequestUtils.getCookie(req, "username")));
      req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }
  }

  @Override
  public void destroy() {
    super.destroy();
  }
}
