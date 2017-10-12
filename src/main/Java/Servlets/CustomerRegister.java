package Servlets;

import Interfaces.CustomerI;
import Pojos.Account;
import Pojos.Customer;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "addCustomer")
public class CustomerRegister extends Custom {
    @EJB
    CustomerI customerI;


    Account account = new Account();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("customerRegister.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer c = recordCustomer(req);
        if (customerI.register(c)) {
            resp.sendRedirect("customerLogin");
        } else {
            doGet(req, resp);
        }

    }

    private Customer recordCustomer(HttpServletRequest req) {
        Customer c = new Customer();
        c.setId(Integer.parseInt(get(req, "id")));
        c.setPassword(get(req, "password"));
        c.setEmail(get(req, "email"));
        c.setContact(get(req, "contact"));
        c.setId(Integer.parseInt(get(req, "id")));
        c.setName(get(req, "name"));
        return c;
    }
}
