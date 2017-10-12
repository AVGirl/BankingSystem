package Servlets;

import Interfaces.CustomerI;
import Pojos.Account;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "checkBalance")
public class CheckBalance extends Custom {

    @EJB
    CustomerI customerI;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = new Account();
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        account.setAccount_number((get(req, "account_number")));
        if (customerI.checkBalance(account) != null) {
            HttpSession session = req.getSession();
            session.setAttribute("balance", account.getBalance());
            resp.sendRedirect("balance.jsp");
        } else {
            out.print("<html><body><p> Incorrect amount or Account Number! : <a href=\"checkBalance.jsp\">Back</a> </p></body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("CustomerLogin");
        }
        RequestDispatcher rd = req.getRequestDispatcher("checkBalance.jsp");
        rd.forward(req, resp);
    }
}
