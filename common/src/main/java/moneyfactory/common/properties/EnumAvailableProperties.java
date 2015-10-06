package moneyfactory.common.properties;

/**
 * EnumAvailableProperties - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 17:31
 * @version $Id$
 */
public enum EnumAvailableProperties {

    PLATEFORM_THING_ROOTNAME("plateformThingRootName", String.class, "moneyfactory");

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
