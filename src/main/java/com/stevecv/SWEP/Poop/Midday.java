package com.stevecv.SWEP.Poop;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;

public class Midday {
    public void startMidnightPoop(Plugin plugin) {
        final boolean[] alreadyRan = {false};
        new BukkitRunnable() {
            LocalDateTime historyDate = null;
            public void run() {
                LocalDateTime midDay = LocalDateTime.parse("12:00");

                if (LocalDateTime.now().isAfter(midDay) && !alreadyRan[0]) {
                    for(World w: Bukkit.getWorlds()) {
                        for(Entity e: w.getEntities()) {
                            new Poop().makePoop(e);
                        }
                    }
                    alreadyRan[0] = true;
                }
                historyDate = LocalDateTime.now();
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
