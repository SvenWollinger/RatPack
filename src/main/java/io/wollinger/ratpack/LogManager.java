package io.wollinger.ratpack;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogManager {
    private static Logger logger;

    public static void init(RatPack plugin) {
        logger = plugin.getLogger();
    }

    public static void log(Object message, Level level) {
        logger.log(level, message.toString());
    }
}
