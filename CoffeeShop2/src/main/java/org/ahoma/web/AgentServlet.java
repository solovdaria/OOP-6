package org.ahoma.web;

import org.ahoma.config.BeanFactory;
import org.ahoma.exception.ValidationException;
import org.ahoma.service.AgentService;
import org.ahoma.service.UserService;
import org.ahoma.util.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AgentServlet extends HttpServlet {
  private AgentService service = (AgentService) BeanFactory.getBean(AgentService.class);
  private UserService userService = (UserService) BeanFactory.getBean(UserService.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String delete = req.getParameter("delete");
    if (delete != null) {
      service.deleteById(Long.parseLong(delete));
    }
    req.setAttribute("agents", service.findAllAgents());
    req.setAttribute("admin", userService.isAdmin(RequestUtils.getCookie(req, "username")));
    req.getRequestDispatcher("agent.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String agentName = req.getParameter("agent_name");
    String sagencyId = req.getParameter("agency_id");

    if (!ValidationUtils.validateInt(sagencyId)) {
      throw new ValidationException("agency id is not valid");
    } else if (!ValidationUtils.validateString(agentName)) {
      throw new ValidationException("Agent name is not valid");
    } else {
      Long agencyId = Long.parseLong(sagencyId);
      service.createAgent(agentName, agencyId);
      req.setAttribute("agents", service.findAllAgents());
      req.setAttribute("admin", userService.isAdmin(RequestUtils.getCookie(req, "username")));
      req.getRequestDispatcher("agent.jsp").forward(req, resp);
    }
  }
}
