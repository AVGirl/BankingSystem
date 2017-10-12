package Operations;

import Interfaces.DBConnectI;
import Interfaces.StatementI;
import Pojos.Statement;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Local
@Stateless
public class StatementBean implements StatementI {
    @EJB
    DBConnectI dbConnectI;

    public boolean recordStatement(Statement statement) {
        String sql = "INSERT INTO statement(account_number,details,amount,balance,time) VALUES(?,?,?,?,?)";
        if (dbConnectI != null) {
            PreparedStatement ps = dbConnectI.createPreparedStatement(sql);
            try {
                ps.setString(1, statement.getAccount_number());
                ps.setString(2, statement.getDetails());
                ps.setFloat(3, statement.getAmount());
                ps.setFloat(4, statement.getBal());
                ps.setString(5, statement.getTime());
                return dbConnectI.dbWrite(ps);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
