package io.wollinger.ratpack;

import io.wollinger.ratpack.commands.CommandBase;
import io.wollinger.ratpack.commands.MarkerCommand;
import io.wollinger.ratpack.commands.MooCommand;
import io.wollinger.ratpack.commands.WaypointCommand;
import io.wollinger.ratpack.features.EggLauncher;
import io.wollinger.ratpack.features.NoCreeperBlockDamage;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class RatPack extends JavaPlugin {

    @Override
    public void onEnable() {
        LogManager.init(this);

        LogManager.log("Saving config...", Level.INFO);
        saveDefaultConfig();

        LogManager.log("Registering events...", Level.INFO);
        getServer().getPluginManager().registerEvents(new EggLauncher(this), this);
        getServer().getPluginManager().registerEvents(new NoCreeperBlockDamage(this), this);

        LogManager.log("Registering commands...", Level.INFO);
        registerCommand(new MooCommand());
        registerCommand(new WaypointCommand());
        registerCommand(new MarkerCommand());

        LogManager.log("Enabled! Hello world!", Level.INFO);
    }

    public void registerCommand(CommandBase command) {
        PluginCommand cmd = getCommand(command.getLabel());

        if(cmd == null) {
            LogManager.log("Error registering command! getCommand for %s is null!", Level.SEVERE, command.getLabel());
            return;
        }
        cmd.setExecutor(command);
    }

}
