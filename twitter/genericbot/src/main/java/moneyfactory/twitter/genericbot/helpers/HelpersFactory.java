package moneyfactory.twitter.genericbot.helpers;

import moneyfactory.common.monitoring.model.Monitorable;
import moneyfactory.twitter.genericbot.exceptions.BotInitException;

/**
 * HelpersFactory - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 15:57
 * @version $Id$
 */
public class HelpersFactory {

    private static TwitterHelper twitterHelper = null;
    private static SourcesHelper sourcesHelper = null;
    private static PublishedTweetsHelper publishedTweetsHelper = null;

    public static SourcesHelper getSourcesHelper(Monitorable monitorable) throws BotInitException {
        if(sourcesHelper == null) {
            sourcesHelper = new SourcesHelper(monitorable);
        }
        return sourcesHelper;
    }

    public static PublishedTweetsHelper getPublishedTweetsHelper(Monitorable monitorable) throws BotInitException {
        if(publishedTweetsHelper == null) {
            publishedTweetsHelper = new PublishedTweetsHelper(monitorable);
        }
        return publishedTweetsHelper;
    }

    public static TwitterHelper getTwitterHelper(Monitorable monitorable, String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) throws BotInitException {
        if(twitterHelper == null) {
            twitterHelper = new TwitterHelper(monitorable, consumerKey, consumerSecret, accessToken, accessTokenSecret);
        }
        return twitterHelper;
    }

}
