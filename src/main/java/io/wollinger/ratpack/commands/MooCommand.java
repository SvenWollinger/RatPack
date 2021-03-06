package io.wollinger.ratpack.commands;

import io.wollinger.ratpack.RatPack;
import io.wollinger.ratpack.Utils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MooCommand implements CommandBase{
    private final RatPack plugin;
    private final String[] moos = new String[] {"moo", "mooo", "moOO", "MOO"};

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
                i++;
            }
        }.runTaskTimer(plugin, 0, 20);

        StringBuilder mooMsg = new StringBuilder("<Cow>");
        for(int i = 0; i < amount; i++) {
            mooMsg.append(String.format(" %s ", moos[Utils.getRandomNumber(0, moos.length)]));
        }
        sender.sendMessage(mooMsg.toString());

        return true;
    }

    @Override
    public String getLabel() {
        return "moo";
    }
}
