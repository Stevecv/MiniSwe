package com.stevecv.SWEP.Horseball.Ball;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ThrowBall implements Listener {
    @EventHandler
    public void throwBall(ProjectileLaunchEvent e) {
        if (e.getEntityType() != EntityType.SNOWBALL) { return; }

        Projectile ball = e.getEntity();
        World world = ball.getWorld();
        if (!world.getName().contains("Horseball")) { return; }

        double high = 1.5;
        double low = 0.5;
        double vel = ((Math.random() * (high - low)) + low);
        ball.setVelocity(ball.getVelocity().multiply(vel));
    }
}
