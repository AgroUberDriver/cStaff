package me.agro.cstaff.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.agro.cstaff.main;
import org.bukkit.inventory.Inventory;

public class staffchat implements CommandExecutor {
    main plugin;

    public staffchat(main plugin) {
        this.plugin = plugin;

    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("staffchat")) {
            if (sender.hasPermission("cstaff.staffchat")) {
                Player p = (Player) sender;

                if (plugin.sc.contains(p)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.STAFFCHAT-DISABLED")));
                    plugin.sc.remove(p);

                    return true;
                }

                if (!plugin.sc.contains(p)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.STAFFCHAT-ENABLED")));
                    plugin.sc.add(p);
                }
                if (!sender.hasPermission("cstaff.fly")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.NOPERMISSION")));
                }
                return true;
            }
            return false;

        }
        return false;
    }
    }

