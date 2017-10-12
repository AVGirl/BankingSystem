package Servlets;

import Interfaces.EmployeeI;
import Pojos.Account;
import Pojos.AccountType;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "openAccount")
public class OpenAccount extends Custom {
    Account account = new Account();
    @EJB
    EmployeeI employeeI;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        account.setBalance(Float.parseFloat(get(req, "amount")));
        account.setAccount_number((get(req, "account_number")));
        account.setCustomer_id(Integer.parseInt(get(req, "customer_id")));
        if (get(req, "accountType").equals("Saving")) {
            account.setAccountType(AccountType.SAVING);
        } else if (get(req, "accountType").equals("Fixed")) {
            account.setAccountType(AccountType.FIXED);
        } else if (get(req, "accountType").equals("Checked")) {
            account.setAccountType(AccountType.CHECKING);
        }
        if (employeeI.openAccount(account)) {
            out.print("<html><body><p> Successfully Added! : <a href=\"EmployeeHome.jsp\">Back</a> </p></body></html>");

        } else {
            out.print("<html><body><p> Not Added Successfully! : <a href=\"OpenAccount.jsp\">Try Again</a> </p></body></html>");

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("OpenAccount.jsp");
        requestDispatcher.forward(req, resp);
    }
}
