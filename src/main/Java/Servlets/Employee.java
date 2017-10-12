package Servlets;

import Events.ActivityEvent;
import Interfaces.ActivityI;
import Pojos.Activity;
import Qualifiers.AuthenticateI;
import Qualifiers.Login;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "EmployeeLogin")
public class Employee extends Custom {
    @Inject
    @Login(value = Login.login.employee)
    private AuthenticateI authenticateI;


    @EJB
    ActivityI activityI;

    @Inject
    private Event<ActivityEvent> activityEventEvent;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String sessionEmail = String.valueOf(req.getAttribute("email"));
        HttpSession session = req.getSession();
        session.setAttribute("mail", sessionEmail);
        recordActivity(req, "Employee");
        resp.sendRedirect("EmployeeHome.jsp");
        out.close();
    }


}
