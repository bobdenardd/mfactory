package moneyfactory.monitor;

import moneyfactory.common.monitoring.model.events.MonitoringEvent;
import moneyfactory.monitor.exceptions.MonitoringException;

import java.util.List;

/**
 * Monitor - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 22:08
 * @version $Id$
 */
public interface Monitor {

    void processAllMonitoringEvents() throws MonitoringException;

    List<String> getAllExistingEventIds();

    void persistEvent(MonitoringEvent event);

}
