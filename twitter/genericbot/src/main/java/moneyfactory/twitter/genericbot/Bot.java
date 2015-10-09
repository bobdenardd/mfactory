package moneyfactory.twitter.genericbot;

import moneyfactory.common.monitoring.model.Monitorable;
import moneyfactory.common.monitoring.model.Things;
import moneyfactory.twitter.genericbot.exceptions.BotInitException;
import moneyfactory.twitter.genericbot.helpers.HelpersFactory;
import moneyfactory.twitter.genericbot.helpers.PublishedTweetsHelper;
import moneyfactory.twitter.genericbot.helpers.SourcesHelper;
import moneyfactory.twitter.genericbot.helpers.TwitterHelper;
import org.apache.log4j.Logger;

/**
 * Bot - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 16:58
 * @version $Id$
 */
public class Bot implements Monitorable {

    private static final Logger LOGGER = Logger.getLogger(Bot.class);

    private TwitterHelper twitterHelper;
    private PublishedTweetsHelper publishedTweetsHelper;
    private SourcesHelper sourcesHelper;
    private String botUniqueId;

    public Bot(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret, String botUniqueId) throws BotInitException {
        this.botUniqueId = botUniqueId;

        // Initializing helpers
        this.twitterHelper = HelpersFactory.getTwitterHelper(this, consumerKey, consumerSecret, accessToken, accessTokenSecret);
        this.publishedTweetsHelper = HelpersFactory.getPublishedTweetsHelper(this);
        this.sourcesHelper = HelpersFactory.getSourcesHelper(this);
    }

    @Override
    public String getThingUniqueName() {
        return Things.getThingOf(Things.BOT, this.botUniqueId);
    }

}
