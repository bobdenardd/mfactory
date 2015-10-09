package moneyfactory.twitter.genericbot.helpers;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import moneyfactory.common.monitoring.model.Monitorable;
import moneyfactory.twitter.genericbot.exceptions.BotInitException;
import moneyfactory.twitter.genericbot.model.Tweet;
import moneyfactory.twitter.genericbot.properties.BotPropertiesHelper;
import org.apache.log4j.Logger;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * PublishedTweetsHelper - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 14:50
 * @version $Id$
 */
public class PublishedTweetsHelper extends BotHelper {

    private static final Logger LOGGER = Logger.getLogger(PublishedTweetsHelper.class);

    private static final String HELPER_NAME = "publishedTweetsHelper";

    private BloomFilter<String> tweetsFilter;

    public PublishedTweetsHelper(Monitorable bot) throws BotInitException {
        super(bot);
    }

    @Override
    public void init() {
        this.tweetsFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), 10000);
        Options options = new Options();
        options.createIfMissing(true);

        try (DB db = Iq80DBFactory.factory.open(new File(BotPropertiesHelper.getPublishedTweetsDb()), options)) {
            try (DBIterator iterator = db.iterator()) {
                for (iterator.seekToFirst(); iterator.hasNext(); iterator.next()) {
                    this.tweetsFilter.put(new String(iterator.peekNext().getKey()));
                }
            }
        } catch (IOException e) {

        }
    }

    @Override
    public String getHelperName() {
        return HELPER_NAME;
    }

    public void addPublishedTweet(Tweet tweet) {

    }

    public boolean isTweetAlreadyPublished(Tweet tweet) {
        return false;
    }

}
