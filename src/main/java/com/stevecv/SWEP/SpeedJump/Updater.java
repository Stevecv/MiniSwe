package com.stevecv.SWEP.SpeedJump;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.HorseInfo.Info;
import com.stevecv.SWEP.Main;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataType;

public class Updater {
    public Main main;
    public Updater(Main main) {
        this.main = main;
    }

    public void updateSpeedJump(Entity e) {
        updateSpeed(e);
        updateJump(e);
    }

    public void updateSpeed(Entity e) {
        Info info = new Info(main);
        double lvl = info.getSpeedLevel(e);
        double start = 0.1;
        double increment = 0.1;

        double speed = start+increment*lvl;

        DataHandling dh = new DataHandling(main);
        double hunger = dh.readDataDouble(e,"hunger", 4.0);
        double percent = (5-hunger)*20-20;
        speed = speed-(speed*(percent/100));

        AbstractHorse horse = (AbstractHorse) e;

        String gait = dh.readDataString(e, "gait");
        if (gait == null) {
            gait = "Walk";
        }
        if (gait == "Walk") {
            speed = setWalkSpeed(speed);
        } else if (gait == "Trot") {
            speed = setTrotSpeed(speed);
        } else if (gait == "Canter") {
            speed = setCanterSpeed(speed);
        }
        horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
    }

    public void updateJump(Entity e) {
        Info info = new Info(main);
        double lvl = info.getSpeedLevel(e);
        double start = 0.3;
        double increment = 0.1;

        double jump = start+increment*lvl;

        DataHandling dh = new DataHandling(main);
        double hunger = dh.readDataDouble(e,"water", 4.0);
        double percent = (5-hunger)*20.0-20.0;
        jump = jump-(jump*(percent/100.0));

        AbstractHorse horse = (AbstractHorse) e;
        horse.setJumpStrength(jump);
    }


    public double setWalkSpeed(double speed) {
        return (17.63/100.0)*speed;
    }
    public double setTrotSpeed(double speed) {
        return (31.45/100.0)*speed;
    }
    public double setCanterSpeed(double speed) {
        return (51.09/100.0)*speed;
    }
}
