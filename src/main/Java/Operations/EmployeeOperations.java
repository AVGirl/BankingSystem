package Operations;

import Interfaces.DBConnectI;
import Interfaces.EmployeeI;
import Pojos.Account;
import Pojos.Employee;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Local
@Stateless
public class EmployeeOperations implements EmployeeI {
    @EJB
    DBConnectI dbConnectI;

    public boolean register(Employee employee) {
        if (dbConnectI != null) {
            String sql = "INSERT INTO Employee(id,name,email,password) VALUES(?,?,?,?)";
            PreparedStatement ps = dbConnectI.createPreparedStatement(sql);
            try {
                ps.setInt(1, employee.getId());
                ps.setString(2, employee.getName());
                ps.setString(3, employee.getEmail());
                ps.setString(4, employee.getPassword());
                return dbConnectI.dbWrite(ps);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }
        return false;
    }

    public boolean openAccount(Account account) {
        if (dbConnectI != null) {
            String sql = "INSERT INTO account(customer_id,account_number,account_type,amount) VALUES(?,?,?,?)";
            PreparedStatement ps = dbConnectI.createPreparedStatement(sql);
            try {
                ps.setInt(1, account.getCustomer_id());
                ps.setString(2, account.getAccount_number());
                ps.setString(3, String.valueOf(account.getAccountType()));
                ps.setFloat(4, account.getBalance());
                return dbConnectI.dbWrite(ps);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }
}
