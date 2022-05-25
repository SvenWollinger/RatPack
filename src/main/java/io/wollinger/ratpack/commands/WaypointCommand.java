package io.wollinger.ratpack.commands;

import io.wollinger.ratpack.features.waypoints.Waypoint;
import io.wollinger.ratpack.features.waypoints.WaypointManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WaypointCommand implements CommandBase, TabCompleter {
    private final static String USAGE = "Usage:";
    private final static String USAGE_CREATE =  "/waypoint create <id> <label>";
    private final static String USAGE_DELETE =  "/waypoint delete <id>";
    private final static String USAGE_EDIT =    "/waypoint edit <id> (label|id) <input>";
    private final static String USAGE_EXPORT =  "/waypoint export bluemap";

    private final static String CREATE_CMD = "create";
    private final static String EDIT_CMD = "edit";
    private final static String DELETE_CMD = "delete";
    private final static String EXPORT_CMD = "export";


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0 || args[0].equals("help") || args[0].equals("?")) {
            sender.sendMessage(USAGE, USAGE_CREATE, USAGE_DELETE, USAGE_EDIT, USAGE_EXPORT);
            return true;
        }


        switch(args[0]) {
            case CREATE_CMD:
                create(args, (Player)sender);
                break;
            case EDIT_CMD:
                break;
            case DELETE_CMD:
                break;
            case EXPORT_CMD:
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
        if(args.length <= 1) {
            ArrayList<String> allOptions = new ArrayList<>(Arrays.asList(CREATE_CMD, EDIT_CMD, DELETE_CMD, EXPORT_CMD));
            if(args[0].isEmpty())
                return allOptions;

            ArrayList<String> specificOptions = new ArrayList<>();
            for(String str : allOptions)
                if(str.startsWith(args[0]))
                    specificOptions.add(str);
            return specificOptions;
        }

        switch(args[0]) {
            case "create":
                return new ArrayList<>();
            case "edit":
                if(args.length <= 2)
                    return getWaypointIDS();
            case "delete":
                if(args.length <= 2)
                    return getWaypointIDS();
                else
                    return new ArrayList<>();
            case "export":
                if(args.length <= 2)
                    return Collections.singletonList("bluemap");
                else
                    return new ArrayList<>();

        }

        return null;
    }

    private static ArrayList<String> getWaypointIDS() {
        ArrayList<String> wpIds = new ArrayList<>();
        for(Waypoint wp : WaypointManager.getWaypoints())
            wpIds.add(wp.getId());
        return wpIds;
    }
}
