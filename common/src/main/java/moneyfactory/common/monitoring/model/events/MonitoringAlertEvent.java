package moneyfactory.common.monitoring.model.events;

import moneyfactory.common.monitoring.model.Monitorable;
import org.json.JSONObject;

import java.util.Date;

/**
 * MonitoringAlertEvent - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 17:09
 * @version $Id$
 */
public class MonitoringAlertEvent extends MonitoringEvent {

    private static final String KEY_CONTENT     = "content";

    private String content;

    public MonitoringAlertEvent(Monitorable thing, String content) {
        this(thing, content, null);
    }

    public MonitoringAlertEvent(Monitorable thing, String content, Date alertDate) {
        super(thing, alertDate != null ? alertDate : new Date());
        this.content = content;
    }

    @Override
    public String toJsonString() {
        JSONObject jsonObject = getBaseJson(this.getClass());
        jsonObject.put(KEY_CONTENT, this.content);
        return jsonObject.toString();
    }

}
