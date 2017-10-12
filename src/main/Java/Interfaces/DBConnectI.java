package Interfaces;

import javax.ejb.Local;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Local
public interface DBConnectI {
    void connect();

    void closeResource();

    PreparedStatement createPreparedStatement(String s);

    boolean dbWrite(PreparedStatement ps);

    ResultSet dbRead(String s);

    int delete(PreparedStatement ps);
}
