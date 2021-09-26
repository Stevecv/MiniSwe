package com.stevecv.SWEP.HorseInfo;

import com.stevecv.SWEP.Main;
import com.stevecv.SWEP.Methods.GUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InfoGUI {
    public Main main;
    public InfoGUI(Main main) {
        this.main = main;
    }

    public void checkHorsesInfo(Entity e, Player p) {
        Info info = new Info(main);
        GUI gui = new GUI();

        String name = info.getName(e);

        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + name + "s information");

        gui.setBorder(inv);

        String speed = info.romanNumeralsDouble(info.getSpeedLevel(e));
        String jump = info.romanNumeralsDouble(info.getJumpLevel(e));
        gui.addItemLore(inv, Material.EXPERIENCE_BOTTLE, 11,ChatColor.GREEN + "Speed/Jump", new String[]{
                ChatColor.YELLOW + " • " +  ChatColor.GRAY + "Speed: Lvl " + ChatColor.GREEN + String.valueOf(speed).replace(".0", ""),
                ChatColor.YELLOW + " • " +  ChatColor.GRAY + "Jump: Lvl " + ChatColor.GREEN + String.valueOf(jump).replace(".0", ""),
        });


        double energy = info.getEnergy(e);
        double stamina = info.getStamina(e);
        gui.addItemLore(inv, Material.APPLE, 12,ChatColor.GREEN + "Fitness", new String[]{
                ChatColor.YELLOW + " • " +  ChatColor.GRAY + "Energy: Lvl " + ChatColor.GREEN + String.valueOf(energy).replace(".0", ""),
                ChatColor.YELLOW + " • " +  ChatColor.GRAY + "Stamina: Lvl " + ChatColor.GREEN + String.valueOf(stamina).replace(".0", ""),
        });


        String gender = info.getGender(e);
        double temperament = info.getTemperament(e);
        String traits = new Traits(main).getTraits(e);
        gui.addItemLore(inv, Material.BOOK, 13,ChatColor.GREEN + "Characteristics", new String[]{
                ChatColor.YELLOW + " • " +  ChatColor.GRAY + "Gender: " + ChatColor.GREEN + gender,
                ChatColor.YELLOW + " • " +  ChatColor.GRAY + "Temperament: Lvl " + ChatColor.GREEN + String.valueOf(temperament).replace(".0", ""),
                ChatColor.YELLOW + " • " +  ChatColor.GRAY + "Traits: " + ChatColor.GREEN + traits.replace(",", ", "),
        });

        String food = info.foodStr(e);
        String water = info.waterStr(e);
        gui.addItemLore(inv, Material.WHEAT, 14,ChatColor.GREEN + "Care", new String[]{
                ChatColor.YELLOW + " • " +  ChatColor.GRAY + "Food: " + ChatColor.GREEN + String.valueOf(food).replace(".0", ""),
                ChatColor.YELLOW + " • " +  ChatColor.GRAY + "Water: " + ChatColor.GREEN + String.valueOf(water).replace(".0", ""),
        });


        Player ownerP = info.getOwner(e);
        String owner;
        if (ownerP != null) { owner = ownerP.getName(); } else { owner = "Not claimed"; }
        gui.addItemLore(inv, Material.LAVA_BUCKET, 15,ChatColor.GREEN + "Miscellaneous", new String[]{
                ChatColor.YELLOW + " • " +  ChatColor.GRAY + "Owner: " + ChatColor.GREEN + owner,
        });

        p.openInventory(inv);
    }
}
