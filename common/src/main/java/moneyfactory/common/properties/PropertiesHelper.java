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
public abstract class PropertiesHelper {

    private static final Logger LOGGER = Logger.getLogger(PropertiesHelper.class);

    protected static Properties properties = new Properties();

    protected static void init(String propertiesFileNameProperty, String defaultPropertiesFileName) {
        File propertiesFile = new File(System.getProperty(propertiesFileNameProperty, defaultPropertiesFileName));
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

    protected static Object getProperty(EnumAvailableProperties propertyDefinition) {
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
