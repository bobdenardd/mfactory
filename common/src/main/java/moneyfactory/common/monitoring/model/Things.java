package moneyfactory.common.monitoring.model;

/**
 * Things - Short description of the class
 *
 * @author Camille
 *         Last: 07/10/2015 16:47
 * @version $Id$
 */
public enum Things {

    BOT;

    public static String getThingOf(Things thing, String thingId) {
        return thing + "-" + thingId;
    }

}
