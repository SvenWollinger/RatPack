package io.wollinger.ratpack.features.waypoints;

import io.wollinger.ratpack.LogManager;
import io.wollinger.ratpack.RatPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class WaypointManager {
    public static final int MAX_LABEL_LENGTH = 20;

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

    public static boolean hasWaypoint(String id) {
        return waypoints.containsKey(id);
    }

    public static boolean removeWaypoint(String id) {
        if(!waypoints.containsKey(id))
            return false;
        waypoints.remove(id);
        return true;
    }

    public static ArrayList<Waypoint> getWaypoints() {
        ArrayList<Waypoint> wps = new ArrayList<>();
        for(String key : waypoints.keySet())
            wps.add(waypoints.get(key));
        return wps;
    }
}
