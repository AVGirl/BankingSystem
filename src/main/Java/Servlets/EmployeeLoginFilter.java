package Servlets;

import Qualifiers.AuthenticateI;
import Qualifiers.Login;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeLoginFilter implements Filter {
    @Inject
    @Login(value = Login.login.employee)
    private AuthenticateI authenticateI;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PrintWriter out = servletResponse.getWriter();
        servletResponse.setContentType("text/html");

        String e = servletRequest.getParameter("email");
        String p = servletRequest.getParameter("password");
        if (authenticateI.login(e, p)) {
            servletRequest.setAttribute("email", e);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            out.print("<html><body><p> username or password error! : <a href=\"EmployeeLogin.jsp\">Try Again</a> </p></body></html>");

        }
    }

    public void destroy() {

    }
}
