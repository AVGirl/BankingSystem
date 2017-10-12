package Operations;

import Interfaces.DBConnectI;
import Qualifiers.AuthenticateI;
import Qualifiers.Login;

import javax.ejb.EJB;
import java.sql.ResultSet;
import java.sql.SQLException;

@Login(value = Login.login.employee)
public class EmployeeLogin implements AuthenticateI {
    @EJB
    DBConnectI dbConnectI;

    public boolean login(String email, String password) {
        String sql = "select * from Employee where email='" + email + "' and password ='" + password + "'";
        if (dbConnectI != null) {
            ResultSet rs = dbConnectI.dbRead(sql);
            try {
                return rs != null && rs.next();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }
}

