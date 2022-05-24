package io.wollinger.ratpack.commands;

import org.bukkit.command.CommandExecutor;

public interface CommandBase extends CommandExecutor {
    String getLabel();
}
