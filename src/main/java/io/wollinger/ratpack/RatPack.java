package io.wollinger.ratpack;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class RatPack extends JavaPlugin {

    @Override
    public void onEnable() {
        LogManager.log("Starting...", Level.INFO);
        saveDefaultConfig();
        LogManager.log("Registering events...", Level.INFO);
        getServer().getPluginManager().registerEvents(new EggLauncher(this), this);
        getServer().getPluginManager().registerEvents(new NoCreeperBlockDamage(this), this);
        LogManager.log("Enabled! Hello world!", Level.INFO);
    }

}
