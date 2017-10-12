package Interfaces;

import Pojos.Statement;

import javax.ejb.Local;

@Local
public interface StatementI {
    boolean recordStatement(Statement statement);
}
