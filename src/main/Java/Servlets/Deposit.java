package Servlets;

import Interfaces.CustomerI;
import Interfaces.StatementI;
import Pojos.Account;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "deposit")
public class Deposit extends Custom {
    @EJB
    CustomerI customerI;
    Account account = new Account();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        account.setAccount_number(((get(req, "account_number"))));
        if (customerI.deposit(account, Float.parseFloat(get(req, "amount")))) {
            recordStatement(req, "deposit");
            out.print("<html><body><p> Successfully Deposited! : <a href=\"CustomerHome.jsp\">Back</a> </p></body></html>");
        } else {
            out.print("<html><body><p> Successfully Deposited! : <a href=\"CustomerHome.jsp\">Back</a> </p></body></html>");

        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("CustomerLogin");
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Deposit.jsp");
        requestDispatcher.forward(req, resp);
    }

}
