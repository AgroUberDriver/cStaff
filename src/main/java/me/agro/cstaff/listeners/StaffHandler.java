package me.agro.cstaff.listeners;

import me.agro.cstaff.main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class StaffHandler implements Listener {

    main plugin;

    public StaffHandler(main plugin) {
        this.plugin = plugin;

    }

    //Block Break//

    @EventHandler

    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(plugin.staffMode.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.ACTION-DENY")));
        }
    }

    //Block Place//
    @EventHandler

    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(plugin.staffMode.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.ACTION-DENY")));
        }
    }
    @EventHandler


    //Item Drop//
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if(plugin.staffMode.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.ACTION-DENY")));
        }
    }


    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (plugin.staffMode.contains(p)) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.ACTION-DENY")));
            }
        }
    }
    @EventHandler
    //Item Move//
    public void onMove(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(plugin.staffMode.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MESSAGES.ACTION-DENY")));
        }
    }

    @EventHandler
    //Item Pickup//
    public void onPickup(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        if(plugin.staffMode.contains(p)) {
            e.setCancelled(true);
        }
    }

}
