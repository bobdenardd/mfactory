package moneyfactory.common.monitoring.model;

import moneyfactory.common.json.Jsonable;
import org.json.JSONObject;

import java.util.Date;

/**
 * MonitoringAlert - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 17:09
 * @version $Id$
 */
public class MonitoringAlert implements Jsonable {

    private static final String KEY_THING       = "thing";
    private static final String KEY_ALERTDATE   = "alertDate";
    private static final String KEY_CONTENT     = "content";

    private Monitorable thing;
    private Date alertDate;
    private String content;

    public MonitoringAlert(Monitorable thing, String content) {
        this(thing, content, null);
    }

    public MonitoringAlert(Monitorable thing, String content, Date alertDate) {
        this.thing = thing;
        this.content = content;
        this.alertDate = alertDate != null ? alertDate : new Date();
    }

    @Override
    public String toJsonString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(KEY_THING, this.thing.getThingUniqueName());
        jsonObject.put(KEY_ALERTDATE, this.alertDate);
        jsonObject.put(KEY_CONTENT, this.content);
        return jsonObject.toString();
    }

}
