package io.wollinger.ratpack;

import io.wollinger.ratpack.commands.CommandBase;
import io.wollinger.ratpack.commands.MarkerCommand;
import io.wollinger.ratpack.commands.MooCommand;
import io.wollinger.ratpack.commands.WaypointCommand;
import io.wollinger.ratpack.features.EggLauncher;
import io.wollinger.ratpack.features.NoCreeperBlockDamage;
import io.wollinger.ratpack.features.waypoints.WaypointManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class RatPack extends JavaPlugin {

    @Override
    public void onEnable() {
        LogManager.init(this);

        saveDefaultConfig();

        LogManager.log("Initialising...", Level.INFO);
        Utils.init(this);
        WaypointManager.init(this);

        //Init events
        getServer().getPluginManager().registerEvents(new EggLauncher(this), this);
        getServer().getPluginManager().registerEvents(new NoCreeperBlockDamage(this), this);

        //Init commands
        registerCommand(new MooCommand(this));
        registerCommand(new WaypointCommand());
        registerCommand(new MarkerCommand());

        LogManager.log("Done! Hello world!", Level.INFO);
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
