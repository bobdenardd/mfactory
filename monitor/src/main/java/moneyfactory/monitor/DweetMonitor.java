package moneyfactory.monitor;

import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import dweet4j.client.DweetClientFactory;
import dweet4j.exception.DweetException;
import dweet4j.model.Dweet;
import moneyfactory.common.monitoring.model.events.MonitoringAlertEvent;
import moneyfactory.common.monitoring.model.events.MonitoringBotHelperLoad;
import moneyfactory.common.monitoring.model.events.MonitoringEvent;
import moneyfactory.common.properties.MoneyFactoryPropertiesHelper;
import moneyfactory.monitor.exceptions.MonitoringException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * DweetMonitor - Intended for freeboard.io
 *
 * @author Camille
 *         Last: 09/10/2015 17:28
 * @version $Id$
 */
public class DweetMonitor implements Monitor {

    private static final Logger LOGGER = Logger.getLogger(DweetMonitor.class);

    private static final List<Class<? extends MonitoringEvent>> EVENTS = Lists.newArrayList(MonitoringAlertEvent.class, MonitoringBotHelperLoad.class);
    private static String DB_URL = "jdbc:derby:events_db;create=true";

    private Connection connection = null;
    private BloomFilter<String> uuidFilter;

    public DweetMonitor() throws MonitoringException {
        initDatabase();
        this.uuidFilter = prepareUuidFilter();
    }

    private BloomFilter<String> prepareUuidFilter() throws MonitoringException {
        BloomFilter<String> filter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), 10000);
        for(String dweetId : getAllExistingEventIds()) {
            filter.put(dweetId);
        }
        return filter;
    }

    @Override
    public void processAllMonitoringEvents() throws MonitoringException {
        List<Dweet> dweets;
        try {
            dweets = DweetClientFactory.getClient().getAllDweets(MoneyFactoryPropertiesHelper.getPlateformThingRootName());
        } catch(DweetException e) {
            throw new MonitoringException("Could not fetch monitoring dweets", e);
        }

        // Selecting if dweets shall be persisted
        for(Dweet dweet : dweets) {
            MonitoringEvent monitoringEvent = getFromDweet(dweet);
            if(monitoringEvent != null) {
                // do something from the monitoring event
                if(!this.uuidFilter.mightContain(monitoringEvent.getId())) {
                    persistEvent(monitoringEvent);
                }
            }
        }

        // Computing json files for freeboard.io dashboard
    }

    private MonitoringEvent getFromDweet(Dweet dweet) {
        try {
            JSONObject jsonObject = new JSONObject(dweet.getContent());
            if(!jsonObject.isNull(MonitoringEvent.KEY_EVENT_TYPE)) {
                for(Class clazz : EVENTS) {
                    if(clazz.getName().equals(jsonObject.getString(MonitoringEvent.KEY_EVENT_TYPE))) {
                        return getFromDweet(dweet, clazz);
                    }
                }
            }
        } catch(JSONException e) {
            e.printStackTrace(); // todo better logging
        }
        return null;
    }

    private MonitoringEvent getFromDweet(Dweet dweet, Class clazz) {
        try {
            Constructor constructor = clazz.getConstructor(String.class);
            return (MonitoringEvent) constructor.newInstance(dweet.getContent());
        } catch(Exception e) {
            e.printStackTrace(); // todo better logging
        }
        return null;
    }

    private void initDatabase() throws MonitoringException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            this.connection = DriverManager.getConnection(DB_URL);

            // Trying to create table if non existing
            try (Statement stmt = this.connection.createStatement()){
                stmt.execute("create table events (id varchar(255), thing varchar(255), content varchar(255), eventdate DATE)");
                stmt.close();
            } catch (SQLException e) {
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Could not force create table", e);
                }
            }
        } catch (Exception e) {
            throw new MonitoringException("Could not connect to the dweets database", e);
        }
    }

    @Override
    public void persistEvent(MonitoringEvent event) {  // todo date handling
        try (Statement stmt = this.connection.createStatement()){
            stmt.execute("insert into events values ('" + event.getId() + "','" + event.getThing().getThingUniqueName() + "','" + event.toJsonString() +"', NULL)");
            stmt.close();
        } catch (SQLException e) {
            LOGGER.error("Could not persist event " + event.toJsonString(), e);
        }
    }

    @Override
    public List<String> getAllExistingEventIds() { // todo less crap
        List<String> result = Lists.newArrayList();
        try (Statement stmt = this.connection.createStatement()){
            ResultSet results = stmt.executeQuery("select * from events");
            ResultSetMetaData rsmd = results.getMetaData();
           // int numberCols = rsmd.getColumnCount();
          /*  for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");
            }*/

           // System.out.println("\n-------------------------------------------------");

            while(results.next()) {
                result.add(results.getString(1));
                /*String id = results.getString(1);
                String restName = results.getString(2);
                String cityName = results.getString(3);
                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);*/
            }
            results.close();
        }
        catch (SQLException e) {
            LOGGER.error("Could not load existing dweets ids", e);
        }
        return result;
    }

}
