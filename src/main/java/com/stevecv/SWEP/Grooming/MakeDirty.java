package com.stevecv.SWEP.Grooming;

import com.stevecv.SWEP.Main;
import com.stevecv.SWEP.Methods.Random;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;

public class MakeDirty {
    public Main main;
    public MakeDirty(Main main) {
        this.main = main;
    }

    public void makeDirty(Plugin plugin) {
        new BukkitRunnable() {
            boolean run = false;
            public void run() {

                LocalDateTime time = LocalDateTime.now();
                int hour = time.getHour();

                if (hour > 3 && hour < 6) {
                    if (run == false) {
                        for (World world : Bukkit.getWorlds()) {
                            for (Entity e : world.getEntities()) {
                                if (e.getType() == EntityType.HORSE) {
                                    int amount = new Random().randomRound(30, 5);
                                    for (int i = 0; i < amount; i++) {
                                        new GUI(main).makeDirty(e);
                                    }
                                }
                            }
                        }
                        run = true;
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
