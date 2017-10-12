package Servlets;

import Events.ActivityEvent;
import Interfaces.ActivityI;
import Pojos.Activity;
import Pojos.Customer;
import Qualifiers.AuthenticateI;
import Qualifiers.Login;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "CustomerLogin")
public class LoginCustomer extends Custom {

    @Inject
    @Login(value = Login.login.customer)
    private AuthenticateI authenticateI;


    @EJB
    ActivityI activityI;

    @Inject
    private Event<ActivityEvent> activityEventEvent;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("CustomerLogin.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        if (authenticateI.login(get(req, "email"), get(req, "password"))) {
            recordActivity(req, "Customer");
            HttpSession session = req.getSession();
            session.setAttribute("mail", get(req, "email"));
            resp.sendRedirect("customerHome");
        } else {
            out.print("<html><body><p> username or password error! : <a href=\"CustomerLogin.jsp\">Try Again</a> </p></body></html>");
        }
    }


}
