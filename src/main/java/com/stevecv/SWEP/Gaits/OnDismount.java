package com.stevecv.SWEP.Gaits;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.Main;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import org.spigotmc.event.entity.EntityDismountEvent;

public class OnDismount implements Listener {
    public Main main;
    public OnDismount(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onDismount(EntityDismountEvent e) {
        Entity ent = e.getEntity();

        DataHandling dh = new DataHandling(main);
        dh.saveData(ent, PersistentDataType.STRING, "gait", null);
    }
}
