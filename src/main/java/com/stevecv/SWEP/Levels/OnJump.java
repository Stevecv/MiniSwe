package com.stevecv.SWEP.Levels;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.persistence.PersistentDataType;

public class OnJump implements Listener {
    public Main main;
    public OnJump(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJump(HorseJumpEvent e) {
        Entity entity = e.getEntity();

        if (entity.getPassengers().get(0) == null) { return; }
        Player p = (Player) entity.getPassengers().get(0);
        DataHandling dh = new DataHandling(main);

        double currentXP = dh.readDataDouble(p, "levelXP", 0.0);
        currentXP = currentXP + 10;
        dh.saveData(p, PersistentDataType.DOUBLE, "levelXP", currentXP);
    }
}
