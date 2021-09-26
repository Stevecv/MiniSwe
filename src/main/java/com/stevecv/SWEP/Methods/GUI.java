package com.stevecv.SWEP.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.Arrays;

public class GUI {
    public void addItem(Inventory gui, Material item, int slot, String name) {
        ItemStack ref1 = new ItemStack(item);
        ItemMeta metaref1 = ref1.getItemMeta();

        metaref1.setDisplayName(name);

        ref1.setItemMeta(metaref1);

        gui.setItem(slot, ref1);
    }

    public void addItemLore(Inventory gui, Material item, int slot, String name, String[] lore) {
        ItemStack ref1 = new ItemStack(item);
        ItemMeta metaref1 = ref1.getItemMeta();

        metaref1.setDisplayName(name);

        ref1.setItemMeta(metaref1);
        ref1.setLore(Arrays.asList(lore));

        gui.setItem(slot, ref1);
    }

    public void addPotionLore(Inventory gui, PotionType pot, int slot, String name, String[] lore) {
        ItemStack ref1 = new Potion(pot, 1).toItemStack(1);
        ItemMeta metaref1 = ref1.getItemMeta();

        metaref1.setDisplayName(name);

        ref1.setItemMeta(metaref1);
        ref1.setLore(Arrays.asList(lore));

        gui.setItem(slot, ref1);
    }


    public void setBorder(Inventory gui) {
        int size = gui.getSize();
        if (size < 26) { return; }

        for (int i = 0; i < 9; i++) {
            addItem(gui, Material.GRAY_STAINED_GLASS_PANE, i, " ");
        }
        addItem(gui, Material.GRAY_STAINED_GLASS_PANE, 9, " ");
        addItem(gui, Material.GRAY_STAINED_GLASS_PANE, 17, " ");

        int start = 18;
        int end = 26;
        if (size-1 == end) {
            for (int i = start; i < end+1; i++) {
                addItem(gui, Material.GRAY_STAINED_GLASS_PANE, i, " ");
            }
        } else if (size-1 > start) {
            addItem(gui, Material.GRAY_STAINED_GLASS_PANE, start, " ");
            addItem(gui, Material.GRAY_STAINED_GLASS_PANE, end, " ");
        }

        start = 27;
        end = 35;
        if (size-1 == end) {
            for (int i = start; i < end+1; i++) {
                addItem(gui, Material.GRAY_STAINED_GLASS_PANE, i, " ");
                Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(i));
            }
        } else if (size-1 > start) {
            addItem(gui, Material.GRAY_STAINED_GLASS_PANE, start, " ");
            addItem(gui, Material.GRAY_STAINED_GLASS_PANE, end, " ");
        }

        start = 36;
        end = 44;
        if (size-1 == end) {
            for (int i = start; i < end+1; i++) {
                addItem(gui, Material.GRAY_STAINED_GLASS_PANE, i, " ");
            }
        } else if (size-1 > start) {
            addItem(gui, Material.GRAY_STAINED_GLASS_PANE, start, " ");
            addItem(gui, Material.GRAY_STAINED_GLASS_PANE, end, " ");
        }

        start = 45;
        end = 53;
        if (size-1 == end) {
            for (int i = start; i < end+1; i++) {
                addItem(gui, Material.GRAY_STAINED_GLASS_PANE, i, " ");
            }
        } else if (size-1 > start) {
            addItem(gui, Material.GRAY_STAINED_GLASS_PANE, start, " ");
            addItem(gui, Material.GRAY_STAINED_GLASS_PANE, end, " ");
        }
    }
}
