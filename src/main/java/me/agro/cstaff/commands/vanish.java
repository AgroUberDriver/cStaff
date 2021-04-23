package me.agro.cstaff.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.agro.cstaff.main;


public class vanish implements CommandExecutor {
    main plugin;

    public vanish(main plugin) {
        this.plugin = plugin;

    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("vanish")) {
            if (sender.hasPermission("cstaff.vanish")) {
                Player p = (Player) sender;
                if (!plugin.isVanished(p)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.VANISH-ENABLED")));
                    plugin.setVanished(p, true);
                    return true;
                }
                if (plugin.isVanished(p)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.VANISH-DISABLED")));
                    plugin.setVanished(p, false);
                    return true;
                }
                if (!sender.hasPermission("cstaff.fly")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.NOPERMISSION")));
                }

                return false;
            }
            return true;
        }
        return false;
    }

        }


