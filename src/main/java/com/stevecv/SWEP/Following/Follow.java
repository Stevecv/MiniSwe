package com.stevecv.SWEP.Following;

import com.destroystokyo.paper.entity.Pathfinder;
import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.HorseInfo.Info;
import com.stevecv.SWEP.HorseInfo.Traits;
import com.stevecv.SWEP.Main;
import com.stevecv.SWEP.Methods.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalTime;

public class Follow {
    public Main main;
    public Follow(Main main) {
        this.main = main;
    }

    public void followStart(Plugin plugin) {
        new BukkitRunnable() {
            public void run() {
                for(World world: Bukkit.getWorlds()) {
                    for(Entity e: world.getEntities()) {
                        if (e.getType() == EntityType.HORSE) {
                            Traits t = new Traits(main);
                            String traits = t.getTraits(e);
                            if (traits.contains("Playful")) {
                                for (Entity p : e.getNearbyEntities(5, 5, 5)) {
                                    if (p.getType() == EntityType.PLAYER) {
                                        Random r = new Random();
                                        if ((int) (Math.random() * (100 - 0)) + 0 < 15) {
                                            Mob mob = (Mob) e;
                                            Pathfinder pathfinder = mob.getPathfinder();

                                            pathfinder.moveTo(p.getLocation());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
