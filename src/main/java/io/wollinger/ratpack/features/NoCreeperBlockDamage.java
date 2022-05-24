package io.wollinger.ratpack.features;

import io.wollinger.ratpack.RatPack;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class NoCreeperBlockDamage implements Listener {
    private final RatPack ratPack;

    public NoCreeperBlockDamage(RatPack ratPack) {
        this.ratPack = ratPack;
    }

    @EventHandler
    public void onCreeperExplosion(final EntityExplodeEvent event) {
        if(!ratPack.getConfig().getBoolean("preventCreeperBlockDamage"))
            return;

        if (event.getEntityType().equals(EntityType.CREEPER)) {
            event.setCancelled(true);
            Entity entity = event.getEntity();
            World world = entity.getWorld();
            Location location = entity.getLocation();
            world.playSound(location, "entity.generic.explode", 1F, 1F);
            world.spawnParticle(Particle.SMOKE_LARGE, location, 1);
        }
    }
}
