package com.stevecv.SWEP.Poop;

import com.stevecv.SWEP.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Midnight {
    public Main main;
    public Midnight(Main main) {
        this.main = main;
    }

    public void startMidnightPoop(Plugin plugin) {
        final boolean[] run = {false};
        new BukkitRunnable() {
            public void run() {
                LocalTime target = LocalTime.now();
                boolean targetInZone = (
                        target.isAfter( LocalTime.parse( "23:00:00" ) )
                                &&
                                target.isBefore( LocalTime.parse( "1:00:00" ) )
                );

                if (!run[0] && targetInZone) {
                    for(World w: Bukkit.getWorlds()) {
                        for(Entity e: w.getEntities()) {
                            if (e.getType() == EntityType.HORSE) {
                                new Poop().makePoop(e);
                            }
                        }
                    }
                    run[0] = true;
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
