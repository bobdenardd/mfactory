package moneyfactory.common.monitoring.model.events;

import moneyfactory.common.monitoring.model.Monitorable;
import org.json.JSONObject;

import java.util.Date;

/**
 * MonitoringBotHelperLoad - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 16:17
 * @version $Id$
 */
public class MonitoringBotHelperLoad extends MonitoringEvent {

    private static final String KEY_LOAD_TIME   = "loadTime";
    private static final String KEY_SUCESS      = "succes";
    private static final String KEY_HELPER_NAME = "helperName";

    private Long loadTime;
    private boolean success;
    private String helperName;

    public MonitoringBotHelperLoad(Monitorable monitorable, String helperName, Long loadTime, boolean success) {
        super(monitorable, new Date());
        this.loadTime = loadTime;
        this.success = success;
        this.helperName = helperName;
    }

    public MonitoringBotHelperLoad(String json) {
        super(json);
        this.loadTime = this.jsonObject.optLong(KEY_LOAD_TIME);
        this.success = this.jsonObject.optBoolean(KEY_SUCESS);
        this.helperName = this.jsonObject.optString(KEY_HELPER_NAME);
    }

    public Long getLoadTime() {
        return loadTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getHelperName() {
        return helperName;
    }

    @Override
    public String toJsonString() {
        JSONObject jsonObject = getBaseJson(this.getClass());
        jsonObject.put(KEY_LOAD_TIME, this.loadTime);
        jsonObject.put(KEY_SUCESS, this.success);
        jsonObject.put(KEY_HELPER_NAME, this.helperName);
        return jsonObject.toString();
    }

}
