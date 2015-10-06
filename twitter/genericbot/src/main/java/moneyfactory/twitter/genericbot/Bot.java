package moneyfactory.twitter.genericbot;

import moneyfactory.common.monitoring.model.Monitorable;
import org.apache.log4j.Logger;

/**
 * Bot - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 16:58
 * @version $Id$
 */
public class Bot implements Monitorable{

    private static final Logger LOGGER = Logger.getLogger(Bot.class);

    @Override
    public String getThingUniqueName() {
        return null;
    }

}
