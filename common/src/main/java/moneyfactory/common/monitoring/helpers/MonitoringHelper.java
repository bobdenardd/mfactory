package moneyfactory.common.monitoring.helpers;

import dweet4j.client.DweetClientFactory;
import dweet4j.exception.DweetException;
import moneyfactory.common.monitoring.model.Monitorable;
import moneyfactory.common.monitoring.model.MonitoringAlert;
import moneyfactory.common.properties.PropertiesHelper;
import org.apache.log4j.Logger;

/**
 * MonitoringHelper - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 17:05
 * @version $Id$
 */
public class MonitoringHelper {

    private static final Logger LOGGER = Logger.getLogger(MonitoringHelper.class);

    public void sendMonitoringAlert(Monitorable monitorable, String message) {
        try {
            DweetClientFactory.getClient().createDweet(PropertiesHelper.getPlateformThingRootName(), new MonitoringAlert(monitorable, message).toJsonString());
        } catch(DweetException e) {
            LOGGER.error("Could not send monitoring alert", e);
        }
    }

}
