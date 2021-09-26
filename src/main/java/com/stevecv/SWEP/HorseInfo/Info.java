package com.stevecv.SWEP.HorseInfo;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.Main;
import com.stevecv.SWEP.Methods.Random;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Info {
    public Main main;
    public Info(Main main) {
        this.main = main;
    }


    public String getName(Entity e) {
        DataHandling dh = new DataHandling(main);

        String name = dh.readDataString(e, "name");

        return name;
    }


    public double getSpeedLevel(Entity e) {
        DataHandling dh = new DataHandling(main);

        double speed = dh.readDataDouble(e, "speed", 1.0);

        return speed;
    }


    public double getJumpLevel(Entity e) {
        DataHandling dh = new DataHandling(main);

        double jump = dh.readDataDouble(e, "jump", 1.0);

        return jump;
    }

    public double getTemperament(Entity e) {
        DataHandling dh = new DataHandling(main);

        double jump = dh.readDataDouble(e, "jump", new Random().randomRound(5, 1));

        return jump;
    }

    public String getGender(Entity e) {
        DataHandling dh = new DataHandling(main);

        String gender = dh.readDataString(e, "gender");

        if (gender == null) {
            if (new Random().ifChance(50)) {
                gender = "Stallion";
            } else {
                gender = "Mare";
            }

            dh.saveData(e, PersistentDataType.STRING, "gender", gender);
        }
        return gender;
    }


    public Player getOwner(Entity e) {
        DataHandling dh = new DataHandling(main);

        String ownerUUID = dh.readDataString(e, "owner");
        if (ownerUUID == null) {
            return null;
        } else {
            return Bukkit.getPlayer(UUID.fromString(ownerUUID));
        }
    }


    public double getStamina(Entity e) {
        DataHandling dh = new DataHandling(main);

        double stamina = dh.readDataDouble(e, "stamina", 1.0);

        return stamina;
    }


    public double getEnergy(Entity e) {
        DataHandling dh = new DataHandling(main);

        double stamina = dh.readDataDouble(e, "energy", 5.0);

        return stamina;
    }

    public double getFoodLevel(Entity e) {
        DataHandling dh = new DataHandling(main);

        double food = dh.readDataDouble(e, "food", 4.0);

        return food;
    }

    public double getWaterLevel(Entity e) {
        DataHandling dh = new DataHandling(main);

        double water = dh.readDataDouble(e, "water", 4.0);

        return water;
    }

    public String foodStr(Entity e) {
        double food = getFoodLevel(e);

        String out = null;
        if (food == 0.0) {
            out = "Emaciated";
        }
        if (food == 1.0) {
            out = "Starving";
        }
        if (food == 2.0) {
            out = "Hungry";
        }
        if (food == 3.0) {
            out = "Fed";
        }
        if (food == 4.0) {
            out = "Well Fed";
        }
        if (out == null) {
            out = String.valueOf(food);
        }

        return out;
    }


    public String waterStr(Entity e) {
        double water = getWaterLevel(e);

        String out = null;
        if (water == 0.0) {
            out = "Hypertonic";
        }
        if (water == 1.0) {
            out = "Dehydrated";
        }
        if (water == 2.0) {
            out = "Thirsty";
        }
        if (water == 3.0) {
            out = "Watered";
        }
        if (water == 4.0) {
            out = "Well Watered";
        }
        if (out == null) {
            out = String.valueOf(water);
        }

        return out;
    }


    public String romanNumeralsDouble(double inp) {
        double rounded = Math.round(inp);
        int Int = Integer.parseInt(String.valueOf(rounded).replace(".0", ""));

        return RomanNumerals(Int);
    }


    public String RomanNumerals(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
            int matches = Int/entry.getValue();
            res += repeat(entry.getKey(), matches);
            Int = Int % entry.getValue();
        }
        return res;
    }

    public static String repeat(String s, int n) {
        if(s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
