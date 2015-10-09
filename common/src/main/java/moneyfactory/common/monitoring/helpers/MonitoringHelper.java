package moneyfactory.common.monitoring.helpers;

import dweet4j.client.DweetClientFactory;
import dweet4j.exception.DweetException;
import moneyfactory.common.monitoring.model.Monitorable;
import moneyfactory.common.monitoring.model.events.MonitoringAlertEvent;
import moneyfactory.common.monitoring.model.events.MonitoringBotHelperLoad;
import moneyfactory.common.monitoring.model.events.MonitoringEndEvent;
import moneyfactory.common.monitoring.model.events.MonitoringStartEvent;
import moneyfactory.common.properties.MoneyFactoryPropertiesHelper;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * MonitoringHelper - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 17:05
 * @version $Id$
 */
public class MonitoringHelper {

    private static final Logger LOGGER = Logger.getLogger(MonitoringHelper.class);

    public static void sendMonitoringAlert(Monitorable monitorable, String message) {
        String payLoad = new MonitoringAlertEvent(monitorable, message).toJsonString();
        sendMonitoringPayload(monitorable, payLoad);
    }

    public static void sendMonitoringStart(Monitorable monitorable, Date startDate) {
        String payLoad = new MonitoringStartEvent(monitorable, startDate).toJsonString();
        sendMonitoringPayload(monitorable, payLoad);
    }

    public static void sendMonitoringEnd(Monitorable monitorable, Date endDate) {
        String payLoad = new MonitoringEndEvent(monitorable, endDate).toJsonString();
        sendMonitoringPayload(monitorable, payLoad);
    }

    public static void sendMonitoringBotHelperLoad(Monitorable monitorable, String helperName, Long loadTime, boolean success) {
        String payLoad = new MonitoringBotHelperLoad(monitorable, helperName, loadTime, success).toJsonString();
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Dweeting " + payLoad);
        }
        sendMonitoringPayload(monitorable, payLoad);
    }

    private static void sendMonitoringPayload(Monitorable monitorable, String payload) {
        try {
            DweetClientFactory.getClient().createDweet(MoneyFactoryPropertiesHelper.getPlateformThingRootName(), payload);
        } catch(DweetException e) {
            LOGGER.error("Could not send monitoring payload", e);
        }
    }

}
