package io.wollinger.ratpack.commands;

import io.wollinger.ratpack.RatPack;
import io.wollinger.ratpack.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MooCommand implements CommandBase{
    private RatPack plugin;

    public MooCommand(RatPack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean isConsole = sender instanceof ConsoleCommandSender;

        int amount = 1;

        if(args.length > 0) {
            if(!Utils.isInteger(args[0])) {
                sender.sendMessage("Amount must be a number!");
                return true;
            }

            amount = Integer.parseInt(args[0]);

            if(amount < 1) {
                sender.sendMessage("Amount must be greater then 0, obviously.");
                return true;
            }
        }

        final int finalAmount = amount;
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if(i >= finalAmount)
                    cancel();

                if(!isConsole) {
                    Player p = (Player) sender;
                    p.playSound(p.getLocation(), Sound.ENTITY_COW_AMBIENT, 1F, 1F);
                }
                sender.sendMessage("<Cow> Moo...");
                i++;
            }
        }.runTaskTimer(plugin, 0, 20);
        return true;
    }

    @Override
    public String getLabel() {
        return "moo";
    }
}
