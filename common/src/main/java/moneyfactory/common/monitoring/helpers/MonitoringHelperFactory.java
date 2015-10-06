package moneyfactory.common.monitoring.helpers;

/**
 * MonitoringHelperFactory - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 17:05
 * @version $Id$
 */
public class MonitoringHelperFactory {

    private static MonitoringHelper monitoringHelper;

    public static MonitoringHelper getMonitoringHelper() {
        if(monitoringHelper == null) {
            monitoringHelper = new MonitoringHelper();
        }
        return monitoringHelper;
    }

}
