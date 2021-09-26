package com.stevecv.SWEP.Grooming;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.Main;
import com.stevecv.SWEP.Methods.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

public class GUI {
    public Main main;
    public GUI(Main main) {
        this.main = main;
    }

    public void showGUI(Player p, Entity e) {
        String dirtData = getDirtData(e);

        Inventory inv = Bukkit.createInventory(null, 54, "Grooming GUI");

        int i = 0;
        for (String dataPiece: dirtData.split(",")) {
            Material item = null;
            Methods methods = new Methods();
            if (dataPiece.contains("0")) {
                item = methods.getMaterial(e);
            } else if (dataPiece.contains("1")) {
                item = Material.DIRT;
            } else if (dataPiece.contains("2")) {
                item = Material.PODZOL;
            }

            com.stevecv.SWEP.Methods.GUI gui = new com.stevecv.SWEP.Methods.GUI();
            gui.addItem(inv, item, i, " ");

            i++;
        }

        p.openInventory(inv);
    }


    public void makeDirty(Entity e) {
        String dirtData = getDirtData(e);
        String[] arrayDirtData = dirtData.split(",");

        int rand = new Random().randomRound(54, 0);
        if (arrayDirtData[rand].contains("1")) {
            arrayDirtData[rand] = "2";
        } else {
            arrayDirtData[rand] = "1";
        }

        new DataHandling(main).saveData(e, PersistentDataType.STRING, "grooming", String.join(",", arrayDirtData));
    }


    public String getDirtData(Entity e) {
        DataHandling dh = new DataHandling(main);

        String dirtData = dh.readDataString(e, "grooming");

        if (dirtData == null) {
            dirtData = "0";
            for (int i = 0; i < 53; i++) {
                dirtData = dirtData + ",0";
            }
        }

        return dirtData;
    }
}
