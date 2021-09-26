package com.stevecv.SWEP.Horseball.Ball;

import com.stevecv.SWEP.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class BallHit implements Listener {
    public Main main;
    public BallHit(Main main) {
        this.main = main;
    }

    @EventHandler
    public void ballHit(ProjectileHitEvent e) {
        if (!(e.getEntity().getType() == EntityType.SNOWBALL)) { return; }

        Block b = e.getHitBlock();
        if (b != null) {
            if (b.getType() == Material.OAK_TRAPDOOR) {
                new Score().score(b.getWorld(), (Player) e.getEntity().getShooter());
            }

            new HitGround().ballHitGround(e);
        }

        if (e.getHitEntity() != null) {
            new PassBall().throwSnowball(e, main);
        }
    }
}