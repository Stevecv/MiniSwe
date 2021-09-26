package com.stevecv.SWEP.SpeedJump;

import com.stevecv.SWEP.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityMountEvent;

public class Mount implements Listener {
    public Main main;
    public Mount(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onMount(EntityMountEvent e) {
        if (e.getMount().getType() != EntityType.HORSE) { return; }
        Entity horse = e.getMount();

        new Updater(main).updateSpeedJump(horse);
    }
}
