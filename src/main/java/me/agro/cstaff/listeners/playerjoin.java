package me.agro.cstaff.listeners;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import me.agro.cstaff.main;



public class playerjoin implements Listener {
    main plugin;

    public playerjoin(main plugin) {
        this.plugin = plugin;

    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(p.hasPermission("cstaff.staff")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.STAFF-ENABLED")));
            p.setGameMode(GameMode.CREATIVE);
            plugin.setStaffInventory(p);
            plugin.staffMode.add(p);
            plugin.staffOnline.add(p);

            plugin.setVanished(p, true);

            for (Player staffchat : plugin.staffOnline) {
                String Raw = plugin.getConfig().getString("MESSAGES.STAFFJOIN");

              String Final = Raw.replace("%player%", p.getName());
              String FinalFinal = Final.replace("%server%", plugin.getConfig().getString("SERVER-NAME"));

                staffchat.sendMessage(ChatColor.translateAlternateColorCodes('&',  plugin.getConfig().getString("MESSAGES.NOTIFICATION-PREFIX") + " " + FinalFinal));
            }
        } else {
            for(Player User : plugin.inVanish) {
                e.getPlayer().hidePlayer(User);
            }
         }
        }







        }
