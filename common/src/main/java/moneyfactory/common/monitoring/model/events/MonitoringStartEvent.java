package moneyfactory.common.monitoring.model.events;

import moneyfactory.common.monitoring.model.Monitorable;
import org.json.JSONObject;

import java.util.Date;

/**
 * MonitoringStartEvent - Short description of the class
 *
 * @author Camille
 *         Last: 07/10/2015 17:17
 * @version $Id$
 */
public class MonitoringStartEvent extends MonitoringEvent {

    private static final String KEY_START_DATE = "startDate";

    private Date startDate;

    public MonitoringStartEvent(Monitorable thing, Date startDate) {
        super(thing, new Date());
        this.startDate = startDate;
    }

    public MonitoringStartEvent(String json) {
        super(json);
    }

    @Override
    public String toJsonString() {
        JSONObject jsonObject = getBaseJson(this.getClass());
        jsonObject.put(KEY_START_DATE, this.startDate);
        return jsonObject.toString();
    }

}
