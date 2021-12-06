package io.wollinger.ratpack;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class RatPack extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EggLauncher(this), this);
        LogManager.log("RatPack enabled!", Level.INFO);
    }

}
