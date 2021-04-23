package me.agro.cstaff.commands;

import me.agro.cstaff.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.agro.cstaff.main;


public class fly implements CommandExecutor {
    main plugin;

    public fly(main plugin) {
        this.plugin = plugin;

    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (sender.hasPermission("cstaff.fly")) {
                Player p = (Player) sender;
                if (p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.FLY-DISABLED")));
                    return true;
                }
                p.setAllowFlight(true);
                p.setFlying(true);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.FLY-ENABLED")));

                return true;


            }
            if (!sender.hasPermission("cstaff.fly")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.NOPERMISSION")));
            }

        }
            return false;

    }
}
