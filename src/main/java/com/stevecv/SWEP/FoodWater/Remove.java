package com.stevecv.SWEP.FoodWater;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.HorseInfo.Info;
import com.stevecv.SWEP.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Remove {
    public Main main;
    public Remove(Main main) {
        this.main = main;
    }

    public void removeFoodWater(Plugin plugin) {
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
                    for (World world : Bukkit.getWorlds()) {
                        for (Entity e : world.getEntities()) {
                            if (e.getType() == EntityType.HORSE) {

                                Info info = new Info(main);
                                double water = info.getWaterLevel(e);
                                double food = info.getFoodLevel(e);

                                double newWater = water - 1;
                                double newFood = food - 1;

                                DataHandling dh = new DataHandling(main);
                                if (newWater >= 0.0) {
                                    dh.saveData(e, PersistentDataType.DOUBLE, "water", newWater);
                                }
                                if (newFood >= 0.0) {
                                    dh.saveData(e, PersistentDataType.DOUBLE, "food", newFood);
                                }
                            }
                        }
                    }
                    run[0] = true;
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
