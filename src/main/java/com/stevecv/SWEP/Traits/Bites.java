package com.stevecv.SWEP.Traits;

import com.stevecv.SWEP.HorseInfo.Traits;
import com.stevecv.SWEP.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Bites {
    public Main main;
    public Bites(Main main) {
        this.main = main;
    }


    public void startBites(Plugin plugin) {
        new BukkitRunnable() {
            public void run() {
                List<World> worlds = Bukkit.getWorlds();
                for (World world: worlds) {
                    for (Entity e: world.getEntities()) {
                        String traits = new Traits(main).getTraits(e);
                        if (traits.contains("Mouthy")) {
                            if (e.getType() == EntityType.HORSE) {
                                for (Entity ent : e.getNearbyEntities(5, 5, 5)) {
                                    if (ent.getType() == EntityType.PLAYER) {
                                        ent.sendMessage(ChatColor.GREEN + "You got bit!");
                                        LivingEntity p = (LivingEntity) ent;
                                        p.damage(1d);
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
