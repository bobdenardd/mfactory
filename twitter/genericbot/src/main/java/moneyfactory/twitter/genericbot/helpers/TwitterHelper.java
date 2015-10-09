package moneyfactory.twitter.genericbot.helpers;

import moneyfactory.common.monitoring.model.Monitorable;
import moneyfactory.twitter.genericbot.exceptions.BotInitException;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * TwitterHelper - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 16:30
 * @version $Id$
 */
public class TwitterHelper extends BotHelper {

    private static final String HELPER_NAME = "twitterHelper";

    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;
    private Twitter twitter;

    public TwitterHelper(Monitorable bot, String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) throws BotInitException {
        super(bot);
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
    }

    @Override
    void init() throws BotInitException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(this.consumerKey)
                .setOAuthConsumerSecret(this.consumerSecret)
                .setOAuthAccessToken(this.accessToken)
                .setOAuthAccessTokenSecret(this.accessTokenSecret);
        this.twitter = new TwitterFactory(cb.build()).getInstance();
    }

    @Override
    String getHelperName() {
        return HELPER_NAME;
    }

}
