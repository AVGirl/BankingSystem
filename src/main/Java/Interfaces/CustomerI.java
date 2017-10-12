package Interfaces;

import Pojos.*;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CustomerI {

    boolean authenticate(Activity activity, String username, String password);

    boolean register(Customer customer);

    boolean deposit(Account account, float amount);

    boolean withdraw(Account account, float amount);

    Account checkBalance(Account account);

    List<Statement> viewStatement(Account account);

    void saveStatement(List list);

    boolean applyLoan(Loan loan);

}
