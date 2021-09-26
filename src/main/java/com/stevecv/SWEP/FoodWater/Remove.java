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

public class Remove {
    public Main main;
    public Remove(Main main) {
        this.main = main;
    }

    public void removeFoodWater(Plugin plugin) {
        new BukkitRunnable() {
            LocalDateTime historyDate = null;
            public void run() {

                LocalDateTime now = LocalDateTime.now();

                if (historyDate == null || now.isAfter(historyDate)) {
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
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
