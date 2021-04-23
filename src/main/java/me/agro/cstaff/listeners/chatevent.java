package me.agro.cstaff.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.agro.cstaff.main;

public class chatevent implements Listener {
    main plugin;

    public chatevent(main plugin) {
        this.plugin = plugin;

    }



    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
    if(plugin.sc.contains(p)) {
        e.setCancelled(true);
        for (Player staffchat : plugin.staffOnline) {
            staffchat.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.STAFFCHAT-PREFIX") + ChatColor.YELLOW + " " + p.getName() + ": " + ChatColor.YELLOW + e.getMessage()));

        }
        return;
    }




    }
}
