package Operations;

import Interfaces.ActivityI;
import Interfaces.CustomerI;
import Interfaces.DBConnectI;
import Pojos.*;
import Qualifiers.AuthenticateI;
import Qualifiers.Login;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Local
@Stateless
public class CustomerBean implements CustomerI {
    @EJB
    DBConnectI dbConnectI;

    @EJB
    ActivityI activityI;

    @Inject
    @Login(value = Login.login.customer)
    private AuthenticateI authenticateI;


    public boolean authenticate(Activity activity, String username, String password) {
        if (activityI.recordActivity(activity) && authenticateI.login(username, password)) {
            return true;
        }

        return false;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public boolean register(Customer customer) {
        if (dbConnectI != null) {
            String sql = "INSERT INTO customer(id,name,email,password,contact) VALUES(?,?,?,?,?)";
            PreparedStatement ps = dbConnectI.createPreparedStatement(sql);
            try {
                ps.setInt(1, customer.getId());
                ps.setString(2, customer.getName());
                ps.setString(3, customer.getEmail());
                ps.setString(4, customer.getPassword());
                ps.setString(5, customer.getContact());
                return dbConnectI.dbWrite(ps);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }
        return false;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public boolean deposit(Account account, float amount) {
        if (checkBalance(account) != null) {
            float bal = account.getBalance() + amount;
            String sql = "UPDATE account set amount='" + bal + "' where account_number='" + account.getAccount_number() + "'";
            if (dbConnectI != null) {
                PreparedStatement ps = dbConnectI.createPreparedStatement(sql);
                return dbConnectI.dbWrite(ps);
            }
        }
        return false;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public boolean withdraw(Account account, float amount) {
        if (checkBalance(account) != null && amount <= account.getBalance()) {
            float bal = account.getBalance() - amount;
            String sql = "UPDATE account set amount='" + bal + "' where account_number='" + account.getAccount_number() + "'";
            if (dbConnectI != null) {
                PreparedStatement ps = dbConnectI.createPreparedStatement(sql);
                return dbConnectI.dbWrite(ps);
            }
            return false;
        }
        return false;
    }

    public Account checkBalance(Account account) {
        String sql = "SELECT amount from account where account_number='" + account.getAccount_number() + "'";
        if (dbConnectI != null) {
            ResultSet rs = dbConnectI.dbRead(sql);
            try {
                if (rs != null && rs.next()) {
                    float amou = rs.getFloat("amount");
                    account.setBalance(amou);
                }
                return account;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    
    public List<Statement> viewStatement(Account account) {
        List<Statement> statementList = new ArrayList<Statement>();
        String sql = "SELECT * FROM statement WHERE account_number='" + account.getAccount_number() + "'";
        if (dbConnectI != null) {
            ResultSet rs = dbConnectI.dbRead(sql);
            try {
                while (rs != null && rs.next()) {
                    Statement statement = new Statement();
                    statement.setTime(rs.getString("time"));
                    statement.setDetails(rs.getString("details"));
                    statement.setAmount(rs.getFloat("amount"));
                    statement.setAccount_number(rs.getString("account_number"));
                    statement.setBal(rs.getFloat("balance"));
                    statementList.add(statement);
                }
                return statementList;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;

    }

    public void saveStatement(List list) {
        try {

            FileOutputStream fos = new FileOutputStream("C:\\Users\\ken\\Documents\\Statement.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(new Gson().toJson(list));
            objectOutputStream.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean applyLoan(Loan loan) {
        String sql = "INSERT INTO loan(customer_id,account_number,amount,loantype) VALUES(?,?,?,?)";
        if (dbConnectI != null) {
            PreparedStatement ps = dbConnectI.createPreparedStatement(sql);
            try {
                ps.setInt(1, loan.getCustomer_id());
                ps.setString(2, loan.getAccount_number());
                ps.setFloat(3, loan.getAmount());
                ps.setString(4, loan.getLoantype());
                return dbConnectI.dbWrite(ps);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}


