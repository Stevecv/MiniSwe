package com.stevecv.SWEP.Gaits;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.Main;
import com.stevecv.SWEP.SpeedJump.Updater;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataType;

public class GaitChanges {
    public Main main;
    public GaitChanges(Main main) {
        this.main = main;
    }

    public String upGait(Entity e) {
        DataHandling dh = new DataHandling(main);

        String currentGait = dh.readDataString(e, "gait");

        if (currentGait == null) {
            currentGait = "Walk";
        }
        String nextGait;
        switch (currentGait) {
            case "Walk":
                nextGait = "Trot";
                break;
            case "Trot":
                nextGait = "Canter";
                break;
            case "Canter":
                nextGait = "Gallop";
                break;
            default:
                nextGait = currentGait;
                break;
        }

        dh.saveData(e, PersistentDataType.STRING, "gait", nextGait);
        new Updater(main).updateSpeed(e);

        return nextGait;
    }


    public String downGait(Entity e) {
        DataHandling dh = new DataHandling(main);

        String currentGait = dh.readDataString(e, "gait");

        if (currentGait == null) {
            currentGait = "Trot";
        }
        String nextGait = null;
        switch (currentGait) {
            case "Gallop":
                nextGait = "Canter";
                break;
            case "Canter":
                nextGait = "Trot";
                break;
            case "Trot":
                nextGait = "Walk";
                break;
            default:
                nextGait = currentGait;
                break;
        }

        dh.saveData(e, PersistentDataType.STRING, "gait", nextGait);
        new Updater(main).updateSpeed(e);

        return nextGait;
    }
}
