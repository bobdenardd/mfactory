package moneyfactory.twitter.genericbot;

import moneyfactory.twitter.genericbot.exceptions.BotInitException;
import moneyfactory.twitter.genericbot.properties.BotPropertiesHelper;
import org.apache.log4j.Logger;

/**
 * Main - Short description of the class
 *
 * @author Camille
 *         Last: 07/10/2015 16:05
 * @version $Id$
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Starting bot " + BotPropertiesHelper.getUniqueId());
        Bot bot;
        try {
            bot = new Bot(BotPropertiesHelper.getAccessToken(),
                    BotPropertiesHelper.getAccessTokenSecret(),
                    BotPropertiesHelper.getConsumerKey(),
                    BotPropertiesHelper.getConsumerSecret(),
                    BotPropertiesHelper.getUniqueId());
        } catch(BotInitException e) {
            LOGGER.error("Could not start bot " + BotPropertiesHelper.getUniqueId(), e);
            System.exit(1);
        }
    }

}
