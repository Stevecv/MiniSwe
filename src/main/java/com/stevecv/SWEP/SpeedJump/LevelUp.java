package com.stevecv.SWEP.SpeedJump;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.HorseInfo.Info;
import com.stevecv.SWEP.Main;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.persistence.PersistentDataType;

public class LevelUp implements Listener {
    public Main main;
    public LevelUp(Main main) {
        this.main = main;
    }

    public void levelUpSpeed(Entity e) {
        Info info = new Info(main);

        double currentSpeedLvl = info.getSpeedLevel(e);
        try {
            if (currentSpeedLvl < 5) {
                double nextLvl = currentSpeedLvl + 1;

                DataHandling dh = new DataHandling(main);
                dh.saveData(e, PersistentDataType.DOUBLE, "speed", nextLvl);
            }

            Updater updater = new Updater(main);
            updater.updateSpeedJump(e);
        } catch (Exception exception) { }
    }

    public void levelUpJump(Entity e) {
        Info info = new Info(main);

        double currentJumpLvl = info.getJumpLevel(e);
        if (currentJumpLvl < 5) {
            double nextLvl = currentJumpLvl + 1;

            DataHandling dh = new DataHandling(main);
            dh.saveData(e, PersistentDataType.DOUBLE, "jump", nextLvl);
        }

        Updater updater = new Updater(main);
        updater.updateSpeedJump(e);
    }


    @EventHandler
    public void travelBlock(EntityMoveEvent e) {
        if (e.getEntity().getType() != EntityType.PLAYER) { return; }
        try {
            if (e.getEntity().getVehicle().getType() != EntityType.HORSE) {
                return;
            }

            Entity ent = e.getEntity().getVehicle();

            DataHandling dh = new DataHandling(main);
            double currentBlocks = dh.readDataDouble(ent, "blocksTravelled", 0.0);
            dh.saveData(ent, PersistentDataType.DOUBLE, "blocksTravelled", currentBlocks + 1);

            double bt = 750;
            if (currentBlocks + 1 >= bt) {
                levelUpSpeed(ent);
                double newCurrentBlocks = dh.readDataDouble(ent, "blocksTravelled", 0.0);
                dh.saveData(ent, PersistentDataType.DOUBLE, "blocksTravelled", newCurrentBlocks - bt);
            }
            bt = 1500;
            if (currentBlocks + 1 >= bt) {
                levelUpSpeed(ent);
                double newCurrentBlocks = dh.readDataDouble(ent, "blocksTravelled", 0.0);
                dh.saveData(ent, PersistentDataType.DOUBLE, "blocksTravelled", newCurrentBlocks - bt);
            }
            bt = 3500;
            if (currentBlocks + 1 >= bt) {
                levelUpSpeed(ent);
                double newCurrentBlocks = dh.readDataDouble(ent, "blocksTravelled", 0.0);
                dh.saveData(ent, PersistentDataType.DOUBLE, "blocksTravelled", newCurrentBlocks - bt);
            }
            bt = 7000;
            if (currentBlocks + 1 >= bt) {
                levelUpSpeed(ent);
                double newCurrentBlocks = dh.readDataDouble(ent, "blocksTravelled", 0.0);
                dh.saveData(ent, PersistentDataType.DOUBLE, "blocksTravelled", newCurrentBlocks - bt);
            }
        } catch (Exception exception) {

        }
    }

    @EventHandler
    public void jump(HorseJumpEvent e) {
        Entity ent = e.getEntity();

        DataHandling dh = new DataHandling(main);
        double currentJumpsJumped = dh.readDataDouble(ent, "jumpsJumped", 0.0);
        dh.saveData(ent, PersistentDataType.DOUBLE, "jumpsJumped", currentJumpsJumped+1);

        double bt = 75;
        if (currentJumpsJumped+1 >= bt) {
            levelUpJump(ent);
            double newCurrentBlocks = dh.readDataDouble(ent, "jumpsJumped", 0.0);
            dh.saveData(ent, PersistentDataType.DOUBLE, "jumpsJumped", newCurrentBlocks-bt);
        }
        bt = 150;
        if (currentJumpsJumped+1 >= bt) {
            levelUpJump(ent);
            double newCurrentBlocks = dh.readDataDouble(ent, "jumpsJumped", 0.0);
            dh.saveData(ent, PersistentDataType.DOUBLE, "jumpsJumped", newCurrentBlocks-bt);
        }
        bt = 350;
        if (currentJumpsJumped+1 >= bt) {
            levelUpJump(ent);
            double newCurrentBlocks = dh.readDataDouble(ent, "jumpsJumped", 0.0);
            dh.saveData(ent, PersistentDataType.DOUBLE, "jumpsJumped", newCurrentBlocks-bt);
        }
        bt = 700;
        if (currentJumpsJumped+1 >= bt) {
            levelUpJump(ent);
            double newCurrentBlocks = dh.readDataDouble(ent, "jumpsJumped", 0.0);
            dh.saveData(ent, PersistentDataType.DOUBLE, "jumpsJumped", newCurrentBlocks-bt);
        }
    }
}
