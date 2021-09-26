package com.stevecv.SWEP.Grooming;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.Main;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataType;

public class OpenGui implements Listener {
    public Main main;
    public OpenGui(Main main) {
        this.main = main;
    }

    @EventHandler
    public void openGUI(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType() != EntityType.PLAYER) { return; }

        Player p = (Player) e.getDamager();
        Entity ent = e.getEntity();

        if (p.getItemInHand().getType() != Material.SPONGE) { return; }
        if (ent.getType() != EntityType.HORSE) { return; }

        new DataHandling(main).saveData(p, PersistentDataType.STRING, "selectedEntity", ent.getUniqueId().toString());
        new GUI(main).showGUI(p, ent);
    }
}
