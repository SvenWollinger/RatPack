package io.wollinger.ratpack.features;

import io.wollinger.ratpack.RatPack;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public class EggLauncher implements Listener {
    private final RatPack ratPack;

    public EggLauncher(RatPack ratPack) {
        this.ratPack = ratPack;
    }

    @EventHandler
    public void onThrow(ProjectileLaunchEvent event) {
        if(!ratPack.getConfig().getBoolean("throwAllEggs"))
            return;
        if(event.getEntity().getShooter() instanceof Player) {
            Player thrower = (Player) event.getEntity().getShooter();
            ItemStack itemStack = thrower.getInventory().getItemInMainHand();
            if(itemStack.getType() == Material.EGG && thrower.isSneaking()) {
                event.setCancelled(true);
                thrower.getInventory().setItemInMainHand(null);
                for(int i = 0; i < itemStack.getAmount(); i++) {
                    thrower.launchProjectile(Egg.class);
                }
            }
        }

    }
}
