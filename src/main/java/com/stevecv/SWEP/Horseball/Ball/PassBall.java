package com.stevecv.SWEP.Horseball.Ball;

import com.stevecv.SWEP.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class PassBall {
    public void throwSnowball(ProjectileHitEvent e, Main main) {
        if (!(e.getEntity() instanceof Snowball)) { return; }
        if (e.getHitEntity() instanceof Player) {
            Player p1 = (Player) e.getHitEntity();

            p1.getInventory().addItem(new ItemStack(Material.SNOWBALL, 1));

        } else {
            new HitGround().ballHitGround(e);
        }
    }
}
