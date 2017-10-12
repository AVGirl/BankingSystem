package Observers;

import Events.ActivityEvent;
import Interfaces.ActivityI;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;

public class Activity {
    @EJB
    ActivityI activityI;

    public void message(@Observes ActivityEvent activityEvent) {

        activityI.recordActivity(activityEvent.getActivity());
    }
}
