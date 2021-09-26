package com.stevecv.SWEP.Lunge;

import com.stevecv.SWEP.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.UUID;

public class Lunge {
    public void lungeHorse(Entity en, Player p, Main plugin) {
        UUID euid = en.getUniqueId();
        World world = p.getWorld();
        for (Entity e : world.getEntities()) {
            if (e instanceof Horse) {
                UUID h = e.getUniqueId();
                if (euid.equals(h)) {
                    if (((Horse) e).getLeashHolder() instanceof Player) {
                        new BukkitRunnable() {
                            int tick = 0;
                            int timer = 0;
                            int timer2 = 0;

                            public void run() {
                                final float radius = 4.0f;
                                final float radPerSec = (float) (2 * Math.PI / 4);
                                final float radPerTick = radPerSec * tick / 20f;
                                final Location center = p.getLocation();

                                ++tick;

                                double y = 0.0;
                                Location locRaycast = e.getLocation();
                                while (locRaycast.add(0, -1, 0).getBlock().getType() != Material.AIR) {
                                    locRaycast.add(0, -1, 0);

                                    y = locRaycast.getY();
                                }
                                Location loc = getLocAroundCircle(center, radius, radPerTick, y);

                                //Raycast down to get 0
                                Block below = (loc.add(new Vector(0, -1, 0)).getBlock());
                                if (below.getType() == Material.AIR) {
                                    while (below.getType() == Material.AIR) {
                                        below = loc.add(new Vector(0, -1, 0)).getBlock();
                                    }

                                    loc = below.getLocation();
                                }
                                e.teleport(loc);
                                ++timer;
                                ++timer2;

                                if (timer2 == 20) {
                                    timer2 = 0;
                                }
                                if (timer > 250) {
                                    this.cancel();
                                }
                            }

                        }.runTaskTimer(plugin, 0L, 1L);
                    }
                }
            }
        }
    }


    public Location getLocAroundCircle(Location center, double radius, double angleRad, double y) {
        double x = center.getX() + radius * Math.cos(angleRad);
        double z = center.getZ() + radius * Math.sin(angleRad);

        Location loc = new Location(center.getWorld(), x, y, z);
        Vector difference = center.toVector().clone().subtract(loc.toVector());
        Vector lookDir = new Vector(difference.getZ(), 0.0, -difference.getX());
        loc.setDirection(lookDir);

        return loc;
    }

}
