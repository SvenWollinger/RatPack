package io.wollinger.ratpack.features.waypoints;

import io.wollinger.ratpack.LogManager;
import io.wollinger.ratpack.RatPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class WaypointManager {
    private static HashMap<String, Waypoint> waypoints = new HashMap<>();

    public static void init(RatPack plugin) {
        LogManager.log(plugin.getDataFolder().getAbsolutePath(), Level.INFO);
    }

    public static void addWaypoint(Waypoint waypoint) {
        waypoints.put(waypoint.getId(), waypoint);
    }

    public static void removeWaypoint(Waypoint waypoint) {
        waypoints.remove(waypoint.getId());
    }

    public static void removeWaypoint(String id) {
        waypoints.remove(id);
    }

    public static ArrayList<Waypoint> getWaypoints() {
        ArrayList<Waypoint> wps = new ArrayList<>();
        for(String key : waypoints.keySet())
            wps.add(waypoints.get(key));
        return wps;
    }
}
