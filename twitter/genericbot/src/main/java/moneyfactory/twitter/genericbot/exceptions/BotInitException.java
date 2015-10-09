package moneyfactory.twitter.genericbot.exceptions;

/**
 * BotInitException - Short description of the class
 *
 * @author Camille
 *         Last: 09/10/2015 16:05
 * @version $Id$
 */
public class BotInitException extends Exception {

    public BotInitException(String message) {
        super(message);
    }

    public BotInitException(String message, Exception e) {
        super(message, e);
    }

}
