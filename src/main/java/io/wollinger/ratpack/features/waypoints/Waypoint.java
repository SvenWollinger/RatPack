package io.wollinger.ratpack.features.waypoints;

import org.bukkit.Location;

public class Waypoint {
    private String id; //Allow changing of the id, storing the old and new in order to change it when saving
    private String label;
    private Location location;

    public Waypoint(String id, String label, Location location) {
        this.id = id;
        this.label = label;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Location getLocation() {
        return location;
    }
}
