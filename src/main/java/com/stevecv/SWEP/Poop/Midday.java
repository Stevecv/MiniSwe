package com.stevecv.SWEP.Poop;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Midday {
    public void startMidnightPoop(Plugin plugin) {
        final boolean[] alreadyRan = {false};
        new BukkitRunnable() {
            LocalDateTime historyDate = null;
            public void run() {
                LocalTime target = LocalTime.now();
                boolean targetInZone = (
                                target.isAfter( LocalTime.parse( "11:00:00" ) )
                                &&
                                target.isBefore( LocalTime.parse( "13:00:00" ) )
                );

                if (targetInZone && !alreadyRan[0]) {
                    for(World w: Bukkit.getWorlds()) {
                        for(Entity e: w.getEntities()) {
                            if (e.getType() == EntityType.HORSE) {
                                new Poop().makePoop(e);
                            }
                        }
                    }
                    alreadyRan[0] = true;
                }
                historyDate = LocalDateTime.now();
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
