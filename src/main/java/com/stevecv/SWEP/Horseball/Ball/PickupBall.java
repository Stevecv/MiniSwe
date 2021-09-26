package com.stevecv.SWEP.Horseball.Ball;

import com.stevecv.SWEP.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;

public class PickupBall implements Listener {
    public Main main;
    public PickupBall(Main main) {
        this.main = main;
    }
    @EventHandler
    public void pickupBall(PlayerAttemptPickupItemEvent e) {
        if (e.getItem().getItemStack().getType() != Material.SNOWBALL) { return; }
    }
}
