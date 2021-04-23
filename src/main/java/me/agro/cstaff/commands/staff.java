package me.agro.cstaff.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.agro.cstaff.main;
import org.bukkit.inventory.Inventory;


public class staff implements CommandExecutor {
    main plugin;

    public staff(main plugin) {
        this.plugin = plugin;

    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("staff")) {
            if (sender.hasPermission("cstaff.use")) {
                Player p = (Player) sender;

                if (!plugin.staffMode.contains(sender)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.STAFF-ENABLED")));
                    p.setGameMode(GameMode.CREATIVE);
                    plugin.setStaffInventory(p);
                    plugin.staffMode.add(p);
                    return true;
                }
                if (plugin.staffMode.contains(sender)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.STAFF-DISABLED")));
                    p.setGameMode(GameMode.SURVIVAL);
                    Inventory inv = p.getInventory();
                    inv.clear();


                    plugin.staffMode.remove(p);
                    return true;
                }
                if (!sender.hasPermission("cstaff.use")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.NOPERMISSION")));
                }

                return false;
            }
        }
        return false;
    }
}

