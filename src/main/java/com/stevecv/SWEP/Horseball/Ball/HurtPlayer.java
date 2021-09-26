package com.stevecv.SWEP.Horseball.Ball;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class HurtPlayer implements Listener {
    @EventHandler
    public void damagePlayer(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType() != EntityType.PLAYER) { return; }
        if (e.getEntity().getType() != EntityType.PLAYER) { return; }

        Player damager = (Player) e.getDamager();
        Player damaged = (Player) e.getEntity();

        if (!damaged.getInventory().contains(Material.SNOWBALL)) { return; }
        damaged.getInventory().remove(new ItemStack(Material.SNOWBALL, 1));
        damager.getInventory().addItem(new ItemStack(Material.SNOWBALL, 1));
    }
}
