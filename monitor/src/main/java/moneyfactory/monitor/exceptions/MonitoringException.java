package moneyfactory.monitor.exceptions;

/**
 * MonitoringException - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 22:10
 * @version $Id$
 */
public class MonitoringException extends Exception {

    public MonitoringException(String message, Exception e) {
        super(message, e);
    }

}
