package Servlets;

import Events.ActivityEvent;
import Events.StatementEvent;
import Interfaces.CustomerI;
import Interfaces.StatementI;
import Pojos.Account;
import Pojos.Activity;
import Pojos.Statement;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class Custom extends HttpServlet {
    @EJB
    CustomerI customerI;

    @Inject
    private Event<StatementEvent> event;
    @Inject
    private Event<ActivityEvent> activityEventEvent;

    public String get(HttpServletRequest req, String key) {
        return req.getParameter(key);
    }

    public void recordStatement(HttpServletRequest req, String detail) {
        Account account = new Account();
        Statement statement = new Statement();
        StatementEvent statementEvent = new StatementEvent();
        if (customerI.checkBalance(account) != null) {
            Date date = new Date();
            statement.setAccount_number(((get(req, "account_number"))));
            statement.setAmount(Float.parseFloat(get(req, "amount")));
            statement.setDetails(detail);
            statement.setTime(String.valueOf(date));
            statement.setBal(account.getBalance());
            statementEvent.setStatement(statement);
            event.fire(statementEvent);
        }
    }


    public void recordActivity(HttpServletRequest req, String userType) {
        Activity activity = new Activity();
        ActivityEvent activityEvent = new ActivityEvent();
        Date d = new Date();
        activity.setDetails("Login");
        activity.setEmail(get(req, "email"));
        activity.setTime(String.valueOf(d));
        activity.setUserType(userType);
        activityEvent.setActivity(activity);
        activityEventEvent.fire(activityEvent);
    }
}
