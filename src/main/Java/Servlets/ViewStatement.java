package Servlets;

import Interfaces.CustomerI;
import Pojos.Account;
import Pojos.Statement;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "viewStatement")
public class ViewStatement extends Custom {
    @EJB
    CustomerI customerI;
    Account account = new Account();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("CustomerLogin");
        }
        RequestDispatcher rd = req.getRequestDispatcher("ViewStatement.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Statement> statementList;
        account.setAccount_number((get(req, "account_number")));
        statementList = customerI.viewStatement(account);
        customerI.saveStatement(statementList);
        HttpSession session = req.getSession();
        session.setAttribute("statements", statementList);
        resp.sendRedirect("ViewStatement.jsp");
    }
}
