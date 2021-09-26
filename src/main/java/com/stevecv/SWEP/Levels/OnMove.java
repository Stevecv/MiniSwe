package com.stevecv.SWEP.Levels;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.Main;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataType;

public class OnMove implements Listener {
    public Main main;
    public OnMove(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.hasChangedBlock() == false) { return; }
        if (e.getPlayer().getVehicle() == null) { return; }

        DataHandling dh = new DataHandling(main);
        Player p = e.getPlayer();

        double currentXP = dh.readDataDouble(p, "levelXP", 0.0);
        currentXP++;
        dh.saveData(p, PersistentDataType.DOUBLE, "levelXP", currentXP);
    }

    @EventHandler
    public void onHorseMove(EntityMoveEvent e) {
        if (e.hasChangedBlock() == false) { return; }
        try {
            if (e.getEntity().getLeashHolder() == null) {
                return;
            }

            DataHandling dh = new DataHandling(main);
            Player p = (Player) e.getEntity().getLeashHolder();

            double currentXP = dh.readDataDouble(p, "levelXP", 0.0);
            currentXP++;
            dh.saveData(p, PersistentDataType.DOUBLE, "levelXP", currentXP);
        } catch (Exception exception) {

        }
    }
}
