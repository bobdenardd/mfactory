package moneyfactory.twitter.genericbot.helpers;

import moneyfactory.common.monitoring.helpers.MonitoringHelper;
import moneyfactory.common.monitoring.model.Monitorable;
import moneyfactory.twitter.genericbot.exceptions.BotInitException;

/**
 * BotHelper - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 15:58
 * @version $Id$
 */
public abstract class BotHelper {

    public BotHelper(Monitorable bot) throws BotInitException {
        try {
            long startTime = System.currentTimeMillis();
            init();
            long loadTime = System.currentTimeMillis() - startTime;
            MonitoringHelper.sendMonitoringBotHelperLoad(bot, getHelperName(), loadTime, true);
        } catch(BotInitException e) {
            MonitoringHelper.sendMonitoringBotHelperLoad(bot, getHelperName(), null, false);
            throw e;
        }
    }

    abstract void init() throws BotInitException ;

    abstract String getHelperName();

}
