package com.stevecv.SWEP.Refusals;

import com.stevecv.SWEP.Methods.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.HorseJumpEvent;

public class Jump implements Listener {
    @EventHandler
    public void onJump(HorseJumpEvent e) {
        if (e.getEntity().getPassengers() == null) { return; }
        if (new Random().randomRound(100.0, 0.0) > 3) { return; }

        Player rider = (Player) e.getEntity().getPassengers().get(0);
        e.setCancelled(true);
        rider.sendActionBar(ChatColor.GREEN + "Refusal");
        if (new Random().randomRound(100.0, 0.0) > 15) { return; }
        rider.teleport(rider);
    }
}
