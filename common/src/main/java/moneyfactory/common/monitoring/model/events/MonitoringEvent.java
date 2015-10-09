package moneyfactory.common.monitoring.model.events;

import moneyfactory.common.json.Jsonable;
import moneyfactory.common.monitoring.model.Monitorable;
import org.json.JSONObject;

import java.util.Date;

/**
 * MonitoringEvent - Short description of the class
 *
 * @author Camille
 *         Last: 07/10/2015 16:51
 * @version $Id$
 */
public abstract class MonitoringEvent implements Jsonable {

    protected static final String KEY_THING       = "thing";
    protected static final String KEY_ALERTDATE   = "eventDate";
    protected static final String KEY_EVENT_TYPE  = "eventType";

    protected Monitorable thing;
    protected Date eventDate;

    protected MonitoringEvent(Monitorable thing, Date eventDate) {
        this.thing = thing;
        this.eventDate = eventDate;
    }

    protected JSONObject getBaseJson(Class type) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_THING, this.thing.getThingUniqueName());
        jsonObject.put(KEY_ALERTDATE, this.eventDate);
        jsonObject.put(KEY_EVENT_TYPE, type);
        return jsonObject;
    }

}
