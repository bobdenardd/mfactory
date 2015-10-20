package moneyfactory.monitor.properties;

import moneyfactory.common.properties.EnumAvailableProperties;
import moneyfactory.common.properties.PropertiesHelper;

/**
 * MonitorPropertiesHelper - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 22:15
 * @version $Id$
 */
public class MonitorPropertiesHelper extends PropertiesHelper {

    private static final String PROPERTIES_FILE_PROP_NAME       = "properties";
    private static final String DEFAULT_PROPERTIES_FILE_NAME    = "monitor.properties";

    static {
        init(PROPERTIES_FILE_PROP_NAME, DEFAULT_PROPERTIES_FILE_NAME);
    }

    public static String getMonitoringType() {
        return (String) getProperty(EnumAvailableProperties.MONITORING_TYPE);
    }

    public static boolean getMonitoringEmptyDB() {
        return (Boolean) getProperty(EnumAvailableProperties.MONITORING_EMPTY_DB);
    }

}
