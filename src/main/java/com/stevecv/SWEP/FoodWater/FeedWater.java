package com.stevecv.SWEP.FoodWater;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.HorseInfo.Info;
import com.stevecv.SWEP.HorseInfo.Traits;
import com.stevecv.SWEP.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class FeedWater implements Listener {
    public Main main;
    public FeedWater(Main main) {
        this.main = main;
    }

    @EventHandler
    public void feedHorse(PlayerInteractEntityEvent e) {
        Entity ent = e.getRightClicked();
        Player p = e.getPlayer();

        if (ent.getType() != EntityType.HORSE) { return; }
        if (p.getItemInHand().getType() != Material.HAY_BLOCK) { return; }

        e.setCancelled(true);
        Info info = new Info(main);
        double foodLevel = info.getFoodLevel(ent);

        foodLevel++;

        DataHandling dh = new DataHandling(main);
        if (foodLevel <= 4.0) {
            e.setCancelled(true);

            Traits t = new Traits(main);
            String traits = t.getTraits(ent);

            if (!traits.contains("Foodie")) {
                dh.saveData(ent, PersistentDataType.DOUBLE, "food", foodLevel);
            }
            World world = p.getWorld();
            world.playSound(ent.getLocation(), Sound.ENTITY_HORSE_EAT, 4f, 4f);

            p.getItemInHand().add(-1);
        }
    }

    @EventHandler
    public void waterHorse(PlayerInteractEntityEvent e) {
        Entity ent = e.getRightClicked();
        Player p = e.getPlayer();

        if (ent.getType() != EntityType.HORSE) {
            return;
        }
        if (p.getItemInHand().getType() != Material.WATER_BUCKET) {
            return;
        }

        e.setCancelled(true);
        Info info = new Info(main);
        double waterLevel = info.getWaterLevel(ent);

        waterLevel++;

        DataHandling dh = new DataHandling(main);
        if (waterLevel <= 4.0) {
            e.setCancelled(true);

            dh.saveData(ent, PersistentDataType.DOUBLE, "water", waterLevel);
            World world = p.getWorld();
            world.playSound(ent.getLocation(), Sound.ITEM_BUCKET_EMPTY, 4f, 4f);

            p.getItemInHand().add(-1);
            p.getInventory().addItem(new ItemStack(Material.BUCKET));
        }
    }
}
