package moneyfactory.common.monitoring.model;

import com.google.common.collect.Maps;
import moneyfactory.common.json.Jsonable;

import java.util.Map;

/**
 * Stats - Short description of the class
 *
 * @author Camille
 *         Last: 07/10/2015 14:37
 * @version $Id$
 */
public class Stats implements Jsonable {

    private Map<String, Object> stats = Maps.newHashMap();

    public Stats() {

    }

    public void addStatsItem(String key, Object value) {

    }

    @Override
    public String toJsonString() {
        return null;
    }

}
