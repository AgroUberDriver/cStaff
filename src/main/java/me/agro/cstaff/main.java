package me.agro.cstaff;

import me.agro.cstaff.commands.fly;
import me.agro.cstaff.commands.staff;
import me.agro.cstaff.commands.staffchat;
import me.agro.cstaff.commands.vanish;
import me.agro.cstaff.listeners.StaffHandler;
import me.agro.cstaff.listeners.chatevent;
import me.agro.cstaff.listeners.playerjoin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class main extends JavaPlugin {
    public ArrayList<Player> sc = new ArrayList<Player>();
    public  ArrayList<Player> staffMode = new ArrayList<Player>();
    public  ArrayList<Player> inVanish = new ArrayList<Player>();
    public ArrayList<Player> staffOnline = new ArrayList<Player>();





    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        saveDefaultConfig();
        getCommand("staff").setExecutor(new staff(this));
        getCommand("fly").setExecutor(new fly(this));
        getCommand("staffchat").setExecutor(new staffchat(this));
        getCommand("vanish").setExecutor(new vanish(this));

        PluginManager pm = getServer().getPluginManager();

        getServer().getPluginManager().registerEvents(new chatevent(this),this);
        getServer().getPluginManager().registerEvents(new playerjoin(this), this);
        getServer().getPluginManager().registerEvents(new StaffHandler(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @SuppressWarnings("deprecation")
    public void setVanished(Player player, boolean inVanish) {
        if(inVanish == true) {
            for(Player online : Bukkit.getOnlinePlayers()) {
                online.hidePlayer(player);

            }
            for(Player staff : this.staffOnline) {
                staff.canSee(player);
            }
            this.inVanish.add(player);
        } else {
            for(Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(player);
            }
            this.inVanish.remove(player);
        }
    }

    public boolean isVanished(Player player) {
        return inVanish.contains(player);
    }

    private List<String> compassLore() {
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Right CLICK &eto teleport through blocks."));
        return lore;
    }

    private List<String> wandLore() {
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Right CLICK &eto use world edit."));
        return lore;
    }

    private List<String> freezeLore() {
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Right CLICK &eto freeze."));
        return lore;
    }

    private List<String> inspectLore() {
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Right CLICK &eto inspect a player."));
        return lore;
    }
    private List<String> staffLore() {
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Right CLICK &eto see online staff."));
        return lore;
    }

    private List<String> randomtpLore() {
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Right CLICK &eto teleport to a random player online."));
        return lore;
    }

    private List<String> vanishLore() {
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Right CLICK &eto toggle your vanish."));
        return lore;
    }

    public void setStaffInventory(Player player) {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemStack wand = new ItemStack(Material.WOOD_AXE);
        ItemStack freeze = new ItemStack(Material.PACKED_ICE);
        ItemStack inspect = new ItemStack(Material.BOOK);
        ItemStack staff = new ItemStack(Material.SKULL);
        ItemStack randomtp = new ItemStack(Material.WATCH);
        ItemStack vanish = new ItemStack(Material.INK_SACK);


        ItemMeta compassMeta = compass.getItemMeta();

        compassMeta.setLore(compassLore());
        compassMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eTeleport"));
        compass.setItemMeta(compassMeta);

        ItemMeta wandMeta = wand.getItemMeta();

        wandMeta.setLore(wandLore());
        wandMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eWorld Edit Wand"));
        wand.setItemMeta(wandMeta);

        ItemMeta freezeMeta = freeze.getItemMeta();

        freezeMeta.setLore(freezeLore());
        freezeMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eFreeze"));
        freeze.setItemMeta(freezeMeta);

        ItemMeta inspectMeta = inspect.getItemMeta();

        inspectMeta.setLore(inspectLore());
        inspectMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eInspect"));
        inspect.setItemMeta(inspectMeta);

        ItemMeta staffMeta = staff.getItemMeta();

        staffMeta.setLore(staffLore());
        staffMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eOnline Staff"));
        staff.setItemMeta(staffMeta);

        ItemMeta randomtpMeta = randomtp.getItemMeta();

        randomtpMeta.setLore(randomtpLore());
        randomtpMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eRandom TP"));
        randomtp.setItemMeta(randomtpMeta);

        ItemMeta vanishMeta = randomtp.getItemMeta();

        vanishMeta.setLore(vanishLore());
        vanishMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eVanish"));
        vanish.setItemMeta(vanishMeta);


        boolean compassEnabled = this.getConfig().getBoolean("STAFFITEMS.COMPASS.ENABLED");
        boolean wandEnabled = this.getConfig().getBoolean("STAFFITEMS.WAND.ENABLED");
        boolean freezeEnabled = this.getConfig().getBoolean("STAFFITEMS.FREEZE.ENABLED");
        boolean inspectEnabled = this.getConfig().getBoolean("STAFFITEMS.INSPECT.ENABLED");
        boolean staffEnabled = this.getConfig().getBoolean("STAFFITEMS.STAFFONLINE.ENABLED");
        boolean vanishEnabled = this.getConfig().getBoolean("STAFFITEMS.VANISH.ENABLED");
        boolean randomtpEnabled = this.getConfig().getBoolean("STAFFITEMS.RANDOMTP.ENABLED");




        if(compassEnabled) {
            player.getInventory().setItem(this.getConfig().getInt("STAFFITEMS.COMPASS.SLOT"), compass);

        }
        if(wandEnabled) {
            player.getInventory().setItem(this.getConfig().getInt("STAFFITEMS.WAND.SLOT"), wand);
        }
        if(freezeEnabled) {
            player.getInventory().setItem(this.getConfig().getInt("STAFFITEMS.FREEZE.SLOT"), freeze);
        }
        if(inspectEnabled) {
            player.getInventory().setItem(this.getConfig().getInt("STAFFITEMS.INSPECT.SLOT"), inspect);
        }
        if(staffEnabled) {
            player.getInventory().setItem(this.getConfig().getInt("STAFFITEMS.STAFFONLINE.SLOT"), staff);
        }
        if(vanishEnabled) {
            player.getInventory().setItem(this.getConfig().getInt("STAFFITEMS.VANISH.SLOT"), vanish);
        }
        if(randomtpEnabled) {
            player.getInventory().setItem(this.getConfig().getInt("STAFFITEMS.RANDOMTP.SLOT"), randomtp);
        }







    }
}
