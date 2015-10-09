package moneyfactory.twitter.genericbot.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Tweet - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 14:46
 * @version $Id$
 */
public class Tweet {

    public String rootTweetContent;
    public String extraPrefixingContent;

    public Tweet(String rootTweetContent, String extraPrefixingContent) {
        this.rootTweetContent = rootTweetContent;
        this.extraPrefixingContent = extraPrefixingContent;
    }

    public String getRootTweetContent() {
        return rootTweetContent;
    }

    public String getExtraPrefixingContent() {
        return extraPrefixingContent;
    }

    public String getWholeContent() {
        return (StringUtils.isNotEmpty(this.rootTweetContent) ? this.rootTweetContent + " " : StringUtils.EMPTY) + this.extraPrefixingContent;
    }

}
