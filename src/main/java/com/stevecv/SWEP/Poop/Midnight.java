package com.stevecv.SWEP.Poop;

import com.stevecv.SWEP.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;

public class Midnight {
    public Main main;
    public Midnight(Main main) {
        this.main = main;
    }

    public void startMidnightPoop(Plugin plugin) {
        new BukkitRunnable() {
            LocalDateTime historyDate = null;
            public void run() {

                LocalDateTime now = LocalDateTime.now();

                if (historyDate == null || now.isAfter(historyDate)) {
                    for(World w: Bukkit.getWorlds()) {
                        for(Entity e: w.getEntities()) {
                            new Poop().makePoop(e);
                        }
                    }
                }
                historyDate = LocalDateTime.now();
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
