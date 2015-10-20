package moneyfactory.common.monitoring.model.events;

import moneyfactory.common.json.Jsonable;
import moneyfactory.common.monitoring.model.Monitorable;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * MonitoringEvent - Short description of the class
 *
 * @author Camille
 *         Last: 07/10/2015 16:51
 * @version $Id$
 */
public abstract class MonitoringEvent implements Jsonable {

    private static final Logger LOGGER = Logger.getLogger(MonitoringEvent.class);

    public static final DateFormat FORMAT = new SimpleDateFormat("ddMMyyyy-HHmm");

    private static final String KEY_THING       = "thing";
    private static final String KEY_ALERTDATE   = "eventDate";
    public static final String KEY_EVENT_TYPE   = "eventType";
    private static final String KEY_UUID        = "id";

    protected Monitorable thing;
    protected Date eventDate;
    protected String uuid;
    protected JSONObject jsonObject = null;

    protected MonitoringEvent(Monitorable thing, Date eventDate) {
        this.thing = thing;
        this.eventDate = eventDate;
    }

    protected MonitoringEvent(String json) {
        try {
            this.jsonObject = new JSONObject(json);
            this.thing = new Monitorable() {
                @Override
                public String getThingUniqueName() {
                    return jsonObject.optString(KEY_THING);
                }
            };
            this.uuid = this.jsonObject.optString(KEY_UUID);
            if(StringUtils.isNotEmpty(this.jsonObject.optString(KEY_ALERTDATE))) {
                try {
                    this.eventDate = FORMAT.parse(this.jsonObject.optString(KEY_ALERTDATE));
                } catch(ParseException e) {
                    LOGGER.error("Could not parse event date: " + this.jsonObject.optString(KEY_ALERTDATE));
                }
            }
        } catch(JSONException e) {
            LOGGER.error("Could not build monitoring event from json " + json, e);
            throw new JSONException(e);
        }
    }

    public Monitorable getThing() {
        return this.thing;
    }

    public String getId() {
        return this.uuid;
    }

    public Date getEventDate() {
        return this.eventDate;
    }

    protected JSONObject getBaseJson(Class type) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_THING, this.thing.getThingUniqueName());
        jsonObject.put(KEY_ALERTDATE, this.eventDate != null ? FORMAT.format(this.eventDate) : StringUtils.EMPTY);
        jsonObject.put(KEY_EVENT_TYPE, type.getName());
        jsonObject.put(KEY_UUID, UUID.randomUUID().toString());
        return jsonObject;
    }

}
