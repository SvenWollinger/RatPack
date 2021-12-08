package io.wollinger.ratpack;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class NoCreeperBlockDamage implements Listener {
    @EventHandler
    public void onCreeperExplosion(final EntityExplodeEvent e) {
        if (e.getEntityType().equals(EntityType.CREEPER)) {
            e.setCancelled(true);
        }
    }
}
