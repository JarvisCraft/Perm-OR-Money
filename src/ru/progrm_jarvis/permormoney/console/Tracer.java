package ru.progrm_jarvis.permormoney.console;

import org.bukkit.ChatColor;
import ru.progrm_jarvis.permormoney.PermOrMoney;

/**
 * Used for sending data to server-console.
 */
public final class Tracer {
    private final static String prefix = "[PermORMoney] ";
    //Tracing
    public static void msg (String message) {
        PermOrMoney.getInstance().getLogger().info(prefix + message);
    }
    public static void err (String message) {
        PermOrMoney.getInstance().getLogger().warning(prefix + ChatColor.DARK_RED + message);
    }
}
