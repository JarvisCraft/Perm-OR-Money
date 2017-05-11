package ru.progrm_jarvis.permormoney.console;

import org.bukkit.ChatColor;
import ru.progrm_jarvis.permormoney.PermOrMoney;

import java.util.logging.Logger;

/**
 * Used for sending data to server-console.
 */
public final class Tracer {
    private static Logger logger;
    public static void setLogger(Logger logger) {
        Tracer.logger = logger;
    }
    public static Logger getLogger() {
        return logger;
    }
    //Tracing
    public static void msg (String message) {
        logger.info(message);
    }
    public static void warn (String message) {
        logger.warning(ChatColor.YELLOW + message);
    }
    public static void err (String message) {
        logger.warning(ChatColor.DARK_RED + message);
    }
}
