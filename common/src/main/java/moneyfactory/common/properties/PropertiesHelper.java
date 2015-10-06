package moneyfactory.common.properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * PropertiesHelper - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 17:28
 * @version $Id$
 */
public class PropertiesHelper implements MoneyFactoryProperties {

    private static final Logger LOGGER = Logger.getLogger(PropertiesHelper.class);

    private static final String PROPERTIES_FILE_PROP_NAME       = "properties";
    private static final String DEFAULT_PROPERTIES_FILE_NAME    = "moneyfactory.properties";

    private static Properties properties = new Properties();


    static {
        init();
    }

    private static void init() {
        File propertiesFile = new File(System.getProperty(PROPERTIES_FILE_PROP_NAME, DEFAULT_PROPERTIES_FILE_NAME));
        if (propertiesFile.exists() && propertiesFile.length() > 0) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Starting up from properties file " + propertiesFile.getAbsolutePath());
            }
            try {
                properties.load(new FileInputStream(propertiesFile));
            } catch (Exception e) {
                LOGGER.error("Could not startup from properties file " + propertiesFile.getAbsolutePath(), e);
            }
        }
    }

    public static String getPlateformThingRootName() {
        return (String) getProperty(EnumAvailableProperties.PLATEFORM_THING_ROOTNAME);
    }

    private static Object getProperty(EnumAvailableProperties propertyDefinition) {
        String property = properties.getProperty(propertyDefinition.getPropertyName());
        if (propertyDefinition.getType().equals(String.class)) {
            return StringUtils.isNotEmpty(property) ? property : propertyDefinition.getDefaultValue();
        } else if(propertyDefinition.getType().equals(Integer.class)) {
            if(StringUtils.isNotEmpty(property)) {
                try {
                    return Integer.valueOf(property);
                } catch(NumberFormatException e) {
                    LOGGER.warn("Could not convert " + propertyDefinition.getPropertyName() + " (" + property + ") defaulting to " + propertyDefinition.getDefaultValue());
                }
            }
        } else if(propertyDefinition.getType().equals(Float.class)) {
            try {
                return Float.valueOf(property);
            } catch(NumberFormatException e) {
                LOGGER.warn("Could not convert " + propertyDefinition.getPropertyName() + " (" + property + ") defaulting to " + propertyDefinition.getDefaultValue());
            }
        } else if(propertyDefinition.getType().equals(Boolean.class) && StringUtils.isNotEmpty(property)) {
            return Boolean.valueOf(property);
        }
        return propertyDefinition.getDefaultValue();
    }

}
