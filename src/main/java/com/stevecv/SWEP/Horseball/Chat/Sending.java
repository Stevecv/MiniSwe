package com.stevecv.SWEP.Horseball.Chat;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Sending {
    public void sendToAllInWorld(String txt) {
        World world = Bukkit.getWorld("Horseball");

        for(Player p: world.getPlayers()) {
            p.sendMessage("§a" + txt);
        }
    }
}
