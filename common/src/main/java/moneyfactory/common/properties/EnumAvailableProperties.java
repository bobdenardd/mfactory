package moneyfactory.common.properties;

/**
 * EnumAvailableProperties - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 17:31
 * @version $Id$
 */
public enum EnumAvailableProperties {

    // Plateform properties
    PLATEFORM_THING_ROOTNAME("plateformThingRootName", String.class, "moneyfactory"),

    // Bot properties
    BOT_CONSUMER_KEY("botConsumerKey", String.class, null),
    BOT_CONSUMER_SECRET("botConsumerSecret", String.class, null),
    BOT_ACCESS_TOKEN("botAccessToken", String.class, null),
    BOT_ACCESS_TOKEN_SECRET("botAccessTokenSecret", String.class, null),
    BOT_UNIQUE_ID("botUniqueId", String.class, null),
    BOT_PUBLISHED_TWEETS_DB("publishedDb", String.class, "publishedTweets.dat"),

    // Monitoring properties
    MONITORING_TYPE("monitoringType", String.class, "dweet"),
    MONITORING_EMPTY_DB("emptyDb", Boolean.class, Boolean.FALSE);

    private String propertyName;
    private Class type;
    private Object defaultValue;

    private EnumAvailableProperties(String propertyName, Class type, Object defaultValue) {
        this.propertyName = propertyName;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Class getType() {
        return type;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

}
