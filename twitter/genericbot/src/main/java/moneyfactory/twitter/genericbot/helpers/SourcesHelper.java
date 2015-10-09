package moneyfactory.twitter.genericbot.helpers;

import moneyfactory.common.monitoring.model.Monitorable;
import moneyfactory.twitter.genericbot.exceptions.BotInitException;
import moneyfactory.twitter.genericbot.model.Tweet;

/**
 * SourcesHelper - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 14:49
 * @version $Id$
 */
public class SourcesHelper extends BotHelper {

    private static final String HELPER_NAME = "sourcesHelper";

    public SourcesHelper(Monitorable bot) throws BotInitException {
        super(bot);
    }

    @Override
    public void init() throws BotInitException {

    }

    @Override
    public String getHelperName() {
        return HELPER_NAME;
    }

    public static Tweet getTweet() {
        return null;
    }

}
