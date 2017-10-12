package Qualifiers;


import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Named;

@Local
public interface AuthenticateI {
    public boolean login(String email, String password);
}
