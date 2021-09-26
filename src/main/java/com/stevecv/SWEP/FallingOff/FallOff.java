package com.stevecv.SWEP.FallingOff;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FallOff {
    public void fallOff(Player p) {
        if (p.getVehicle() == null) { return; }

        p.sendActionBar(ChatColor.GREEN + "You fell off!");
        p.teleport(p);
    }
}
