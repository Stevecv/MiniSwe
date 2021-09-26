package com.stevecv.SWEP.HorseInfo;

import com.stevecv.SWEP.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OpenGUI implements Listener {
    public Main main;
    public OpenGUI(Main main) {
        this.main = main;
    }
    @EventHandler
    public void openGUI(EntityDamageByEntityEvent e) {
        if (e.getEntity().getType() != EntityType.HORSE) { return; }
        Player p = (Player) e.getDamager();
        if (!p.isSneaking()) { return; }
        new InfoGUI(main).checkHorsesInfo(e.getEntity(), p);

        e.setCancelled(true);
    }
}
