package Interfaces;

import Pojos.Activity;

import javax.ejb.EJB;
import javax.ejb.Local;

@Local
public interface ActivityI {
    boolean recordActivity(Activity activity);
}
