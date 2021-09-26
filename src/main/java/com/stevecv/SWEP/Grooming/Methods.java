package com.stevecv.SWEP.Grooming;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;

public class Methods {
    public Material getMaterial(Entity e) {
        String coat = getBaseColour(e);

        switch (coat) {
            case "BLACK":
                return Material.BLACK_STAINED_GLASS_PANE;

            case "BROWN":

            case "CHESTNUT":

            case "CREAMY":

            case "DARK_BROWN":
                return Material.BROWN_STAINED_GLASS_PANE;

            case "GRAY":
                return Material.GRAY_STAINED_GLASS_PANE;

            default:
                return Material.WHITE_STAINED_GLASS_PANE;
        }
    }

    public String getBaseColour(Entity horse) {
        Horse.Color colour = ((Horse) horse).getColor();
        return colour.toString();
    }
}
