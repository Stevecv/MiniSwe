package com.stevecv.SWEP.Damage;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamage implements Listener {
    @EventHandler
    public void damageHorse(EntityDamageEvent e) {
        if (e.getEntity().getType() != EntityType.HORSE) { return; }
        e.setCancelled(true);
    }
}
