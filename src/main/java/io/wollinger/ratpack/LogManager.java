package io.wollinger.ratpack;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class LogManager {
    public static void log(String message, Level level) {
        Bukkit.getLogger().log(level, "[RatPack] " + message);
    }

    public static void log(Boolean bool, Level level) {
        Bukkit.getLogger().log(level, "[RatPack] " + bool);
    }
}
