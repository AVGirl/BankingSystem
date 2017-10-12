package Operations;

import Interfaces.DBConnectI;
import Qualifiers.AuthenticateI;
import Qualifiers.Login;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.sql.ResultSet;
import java.sql.SQLException;


@Login(value = Login.login.customer)
public class LoginCustomer implements AuthenticateI {
    @EJB
    DBConnectI dbConnectI;

    public boolean login(String email, String password) {
        String sql = "select * from customer where email='" + email + "' and password ='" + password + "'";
        if (dbConnectI != null) {
            ResultSet rs = dbConnectI.dbRead(sql);
            try {
                return rs != null && rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
