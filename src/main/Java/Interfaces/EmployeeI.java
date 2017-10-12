package Interfaces;

import Pojos.Account;
import Pojos.Employee;

import javax.ejb.Local;

@Local
public interface EmployeeI {
    boolean register(Employee employee);

    boolean openAccount(Account account);


}
