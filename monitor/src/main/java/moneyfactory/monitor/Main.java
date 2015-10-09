package moneyfactory.monitor;

import moneyfactory.monitor.properties.MonitorPropertiesHelper;

/**
 * Main - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 17:18
 * @version $Id$
 */
public class Main {

    private static final String MONITOR_TYPE_DWEET = "dweet";

    public static void main(String[] args) throws Exception {
        Monitor monitor;

        // Selecting the monitor type
        switch (MonitorPropertiesHelper.getMonitoringType()) {
            case MONITOR_TYPE_DWEET:
                monitor = new DweetMonitor();
                break;
            default:
                throw new IllegalStateException("Unknown monitor type: " + MonitorPropertiesHelper.getMonitoringType());
        }

        // Handling all monitoring events
        monitor.processAllMonitoringEvents();
    }

}
