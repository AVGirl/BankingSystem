package Operations;

import Interfaces.ActivityI;
import Interfaces.DBConnectI;
import Pojos.Activity;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Local
@Stateless
public class ActitivityBean implements ActivityI {
    @EJB
    DBConnectI dbConnectI;

    public boolean recordActivity(Activity activity) {
        String sql = " INSERT INTO  activity(userType,time,details,email) VALUES(?,?,?,?)";
        if (dbConnectI != null) {
            PreparedStatement ps = dbConnectI.createPreparedStatement(sql);
            try {
                ps.setString(1, activity.getUserType());
                ps.setString(2, activity.getTime());
                ps.setString(3, activity.getDetails());
                ps.setString(4, activity.getEmail());
                return dbConnectI.dbWrite(ps);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
