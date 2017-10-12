package Operations;

import Interfaces.DBConnectI;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.sql.*;

@Local
@Stateless
public class DbConnectBean implements DBConnectI {
    private Connection con;
    private Statement st;


    @PostConstruct
    public void connect() {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
//            System.out.println("Connecting to database...");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bankingsystem", "root", "");
            //Intialize Statement
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void closeResource() {
        try {
            if (con != null) {
                con.close();
            }

            if (st != null) {
                st.close();
            }

        } catch (SQLException e) {
        }
    }

    public PreparedStatement createPreparedStatement(String s) {
        try {
            return con.prepareStatement(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean dbWrite(PreparedStatement ps) {
        try {
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet dbRead(String s) {
        try {
            return st.executeQuery(s);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public int delete(PreparedStatement ps) {
        try {
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
