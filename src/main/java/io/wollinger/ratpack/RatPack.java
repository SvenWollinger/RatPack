package io.wollinger.ratpack;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Level;

public class RatPack extends JavaPlugin implements Listener {
    private final ArrayList<String> crouchingPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new EggLauncher(this), this);
        LogManager.log("RatPack enabled!", Level.INFO);
    }

    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
        String id = event.getPlayer().getUniqueId().toString();
        if(event.isSneaking() && !crouchingPlayers.contains(id))
            crouchingPlayers.add(id);
        else
            crouchingPlayers.remove(id);
    }

    public boolean isPlayerCrouching(String UUID) {
        return crouchingPlayers.contains(UUID);
    }
}
