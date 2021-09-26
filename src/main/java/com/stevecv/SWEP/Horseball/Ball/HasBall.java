package com.stevecv.SWEP.Horseball.Ball;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class HasBall {
    public void snowball(Plugin plugin) {
        new BukkitRunnable() {
            public void run() {
                if (Bukkit.getWorld("Horseball") == null) { return; }

                World world = Bukkit.getWorld("Horseball");
                for(Player player: world.getPlayers()) {
                    PlayerInventory inv = player.getInventory();
                    if (inv.contains(Material.SNOWBALL)) {
                        inv.setHelmet(new ItemStack(Material.GOLDEN_HELMET));
                    } else {
                        if (inv.getHelmet() == null) { return; }

                        if (inv.getHelmet().getType() == Material.GOLDEN_HELMET) {
                            inv.setHelmet(new ItemStack(Material.AIR));
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 1);
    }
}
