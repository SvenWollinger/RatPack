package io.wollinger.ratpack.commands;

import io.wollinger.ratpack.features.waypoints.Waypoint;
import io.wollinger.ratpack.features.waypoints.WaypointManager;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
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
    //Make ids clickable! Raw msgs!
    private final static String USAGE_LIST   =  "/waypoint list";
    private final static String USAGE_TRACK  =  "/waypoint track <id>";
    private final static String USAGE_CREATE =  "/waypoint create <id> <label>";
    private final static String USAGE_DELETE =  "/waypoint delete <id>";
    private final static String USAGE_EDIT =    "/waypoint edit <id> (label|id) <input>";
    private final static String USAGE_EXPORT =  "/waypoint export bluemap";

    private final static String CMD_LIST =      "list";
    private final static String CMD_TRACK =     "track";
    private final static String CMD_CREATE =    "create";
    private final static String CMD_EDIT =      "edit";
    private final static String CMD_DELETE =    "delete";
    private final static String CMD_EXPORT =    "export";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0 || args[0].equals("help") || args[0].equals("?")) {
            sender.sendMessage(USAGE, USAGE_LIST, USAGE_TRACK, USAGE_CREATE, USAGE_DELETE, USAGE_EDIT, USAGE_EXPORT);
            return true;
        }

        switch(args[0]) {
            case CMD_LIST   ->  list(args, (Player)sender);
            case CMD_TRACK  ->  track(args, (Player)sender);
            case CMD_CREATE ->  create(args, (Player)sender);
            case CMD_EDIT   ->  edit(args, (Player)sender);
            case CMD_DELETE ->  delete(args, (Player)sender);
            case CMD_EXPORT ->  export(args, (Player)sender);
        }
        return true;
    }

    private void list(String[] args, Player player) {
        TextComponent title = new TextComponent("Waypoints:");
        title.setUnderlined(true);
        player.spigot().sendMessage(title);

        for(Waypoint wp : WaypointManager.getWaypoints()) {
            int distance = (int) wp.getLocation().distance(player.getLocation());
            TextComponent text = new TextComponent(wp.getLabel());

            HoverEvent labelHover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(String.format("ID: %s", wp.getId())));
            text.setHoverEvent(labelHover);

            ClickEvent labelClick = new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("/waypoint track %s", wp.getId()));
            text.setClickEvent(labelClick);



            player.spigot().sendMessage(text);
        }
    }

    private void track(String[]args, Player player) {

    }

    private void create(String[] args, Player player) {
        if(args.length <= 2) {
            player.sendMessage(USAGE, USAGE_CREATE);
            return;
        }

        final String id = args[1];

        if(WaypointManager.hasWaypoint(id)) {
            player.sendMessage("Waypoint already exists! Try /waypoint edit ?");
            return;
        }

        StringBuilder label = new StringBuilder();
        for(int i = 2; i < args.length; i++) {
            label.append(args[i]);
            if(i != args.length - 1)
                label.append(" ");
        }

        WaypointManager.addWaypoint(new Waypoint(id, label.toString(), player.getLocation()));
        player.sendMessage(String.format("Waypoint %s created!", label));
    }

    private void edit(String[] args, Player player) {

    }

    private void delete(String[] args, Player player) {
        if(args.length <= 1) {
            player.sendMessage(USAGE, USAGE_DELETE);
            return;
        }
        final String id = args[1];
        if(WaypointManager.removeWaypoint(id))
            player.sendMessage("Waypoint deleted!");
        else
            player.sendMessage("Waypoint not found!");
    }

    private void export(String[] args, Player player) {

    }

    @Override
    public String getLabel() {
        return "waypoint";
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length <= 1) {
            ArrayList<String> allOptions = new ArrayList<>(Arrays.asList(CMD_CREATE, CMD_EDIT, CMD_DELETE, CMD_EXPORT));
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
