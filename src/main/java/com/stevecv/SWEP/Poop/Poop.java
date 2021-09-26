package com.stevecv.SWEP.Poop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class Poop {
    public void makePoop(Entity e) {
        Location loc = e.getLocation();
        Block block = loc.getBlock();
        if (block.getType() == Material.AIR) {
            block.setType(Material.BROWN_CARPET);
        }
    }
}
