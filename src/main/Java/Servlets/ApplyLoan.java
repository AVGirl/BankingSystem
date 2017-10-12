package Servlets;

import Interfaces.CustomerI;
import Pojos.Loan;
import Pojos.LoanType;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "applyLoan")
public class ApplyLoan extends Custom {
    @EJB
    CustomerI customerI;

    Loan loan = new Loan();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        loan.setAccount_number((get(req, "account_number")));
        loan.setAmount(Float.parseFloat(get(req, "amount")));
        loan.setCustomer_id(Integer.parseInt(get(req, "customer_id")));
        if (get(req, "loantype").equals("Long term")) {
            loan.setLoantype(get(req, String.valueOf(LoanType.Long_term)));
        } else if (get(req, "loantype").equals("Short term")) {
            loan.setLoantype(get(req, String.valueOf(LoanType.Short_term)));
        }
        if (customerI.applyLoan(loan)) {
            out.print("<html><body><p> Successfully Applied wait for Apploval! : <a href=\"CustomerHome.jsp\">Back</a> </p></body></html>");
        } else {
            doGet(req, resp);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("CustomerLogin");
        }
        RequestDispatcher rd = req.getRequestDispatcher("applyLoan.jsp");
        rd.forward(req, resp);
    }
}
