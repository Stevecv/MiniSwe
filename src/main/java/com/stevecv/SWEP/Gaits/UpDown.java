package com.stevecv.SWEP.Gaits;

import com.stevecv.SWEP.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class UpDown implements Listener {
    public Main main;
    public UpDown(Main main) {
        this.main = main;
    }

    @EventHandler
    public void upDown(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (p.getVehicle() == null) { return; }
        if (p.getVehicle().getType() != EntityType.HORSE) { return; }

        Action action = e.getAction();
        Entity ent = p.getVehicle();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            p.sendActionBar(ChatColor.GREEN + new GaitChanges(main).upGait(ent));
        } else {
            p.sendActionBar(ChatColor.GREEN + new GaitChanges(main).downGait(ent));
        }
    }
}
