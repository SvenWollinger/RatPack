package io.wollinger.ratpack.commands;

import io.wollinger.ratpack.LogManager;
import io.wollinger.ratpack.features.waypoints.Waypoint;
import io.wollinger.ratpack.features.waypoints.WaypointManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class WaypointCommand implements CommandBase, TabCompleter {
    private final static String USAGE = "Usage:";
    private final static String USAGE_CREATE =  "/waypoint create <id> <label>";
    private final static String USAGE_DELETE =  "/waypoint delete <id>";
    private final static String USAGE_EDIT =    "/waypoint edit <id> (label|id) <input>";
    private final static String USAGE_EXPORT =  "/waypoint export bluemap";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0 || args[0].equals("help") || args[0].equals("?")) {
            sender.sendMessage(USAGE, USAGE_CREATE, USAGE_DELETE, USAGE_EDIT, USAGE_EXPORT);
            return true;
        }


        switch(args[0]) {
            case "create":
                create(args, (Player)sender);
                break;
            case "edit":
                break;
            case "delete":
                break;
            case "export":
                break;

        }


        return true;
    }

    private void create(String[] args, Player player) {
        if(args.length <= 2) {
            player.sendMessage(USAGE, USAGE_CREATE);
            return;
        }
        String id = args[1];
        StringBuilder label = new StringBuilder();
        for(int i = 2; i < args.length; i++) {
            label.append(args[i]);
            if(i != args.length - 1)
                label.append(" ");
        }

        WaypointManager.addWaypoint(new Waypoint(id, label.toString(), player.getLocation()));
        player.sendMessage(String.format("Waypoint %s created!", label));
    }

    @Override
    public String getLabel() {
        return "waypoint";
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
