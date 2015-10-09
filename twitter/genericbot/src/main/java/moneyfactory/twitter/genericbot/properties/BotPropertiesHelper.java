package moneyfactory.twitter.genericbot.properties;

import moneyfactory.common.properties.EnumAvailableProperties;
import moneyfactory.common.properties.PropertiesHelper;

/**
 * BotPropertiesHelper - Short description of the class
 *
 * @author Camille
 *         Last: 07/10/2015 14:50
 * @version $Id$
 */
public class BotPropertiesHelper extends PropertiesHelper {

    private static final String PROPERTIES_FILE_PROP_NAME       = "properties";
    private static final String DEFAULT_PROPERTIES_FILE_NAME    = "bot.properties";

    static {
        init(PROPERTIES_FILE_PROP_NAME, DEFAULT_PROPERTIES_FILE_NAME);
    }

    public static String getAccessToken() {
        return (String) getProperty(EnumAvailableProperties.BOT_ACCESS_TOKEN);
    }

    public static String getAccessTokenSecret() {
        return (String) getProperty(EnumAvailableProperties.BOT_ACCESS_TOKEN_SECRET);
    }

    public static String getConsumerKey() {
        return (String) getProperty(EnumAvailableProperties.BOT_CONSUMER_KEY);
    }

    public static String getConsumerSecret() {
        return (String) getProperty(EnumAvailableProperties.BOT_CONSUMER_SECRET);
    }

    public static String getUniqueId() {
        return (String) getProperty(EnumAvailableProperties.BOT_UNIQUE_ID);
    }

    public static String getPublishedTweetsDb() {
        return (String) getProperty(EnumAvailableProperties.BOT_PUBLISHED_TWEETS_DB);
    }

}
