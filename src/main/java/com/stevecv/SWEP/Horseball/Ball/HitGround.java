package com.stevecv.SWEP.Horseball.Ball;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class HitGround {
    public void ballHitGround(ProjectileHitEvent e) {
        Location blockLoc = e.getHitBlock().getLocation();
        Location dropLoc = blockLoc.add(new Vector(0, 1, 0));

        World world = dropLoc.getWorld();

        world.dropItem(dropLoc, new ItemStack(Material.SNOWBALL, 1));
    }
}
