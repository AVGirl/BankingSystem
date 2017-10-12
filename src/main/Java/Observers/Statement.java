package Observers;

import Events.StatementEvent;
import Interfaces.StatementI;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;

public class Statement {
    @EJB
    StatementI statementI;

    public void message(@Observes StatementEvent event) {
        statementI.recordStatement(event.getStatement());
    }
}
