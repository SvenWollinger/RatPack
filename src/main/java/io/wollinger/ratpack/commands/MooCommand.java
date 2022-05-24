package io.wollinger.ratpack.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MooCommand implements CommandBase{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    @Override
    public String getLabel() {
        return "moo";
    }
}
