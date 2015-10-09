package moneyfactory.common.properties;

/**
 * MoneyFactoryPropertiesHelper - Short description of the class
 *
 * @author Camille
 *         Last: 07/10/2015 14:51
 * @version $Id$
 */
public class MoneyFactoryPropertiesHelper extends PropertiesHelper {

    protected static final String PROPERTIES_FILE_PROP_NAME       = "properties";
    protected static final String DEFAULT_PROPERTIES_FILE_NAME    = "moneyfactory.properties";

    static {
        init(PROPERTIES_FILE_PROP_NAME, DEFAULT_PROPERTIES_FILE_NAME);
    }

    public static String getPlateformThingRootName() {
        return (String) getProperty(EnumAvailableProperties.PLATEFORM_THING_ROOTNAME);
    }

}
