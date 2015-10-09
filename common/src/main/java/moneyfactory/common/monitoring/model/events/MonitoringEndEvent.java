package moneyfactory.common.monitoring.model.events;

import moneyfactory.common.monitoring.model.Monitorable;
import org.json.JSONObject;

import java.util.Date;

/**
 * MonitoringEndEvent - Short description of the class
 *
 * @author Camille
 *         Last: 07/10/2015 17:17
 * @version $Id$
 */
public class MonitoringEndEvent extends MonitoringEvent {

    private static final String KEY_END_DATE = "endDate";

    private Date endDate;

    public MonitoringEndEvent(Monitorable thing, Date endDate) {
        super(thing, new Date());
        this.endDate = endDate;
    }

    public MonitoringEndEvent(String json) {
        super(json);
    }

    @Override
    public String toJsonString() {
        JSONObject jsonObject = getBaseJson(this.getClass());
        jsonObject.put(KEY_END_DATE, this.endDate);
        return jsonObject.toString();
    }

}
