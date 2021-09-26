package com.stevecv.SWEP.Grooming;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.Main;
import com.stevecv.SWEP.Methods.GUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class Clean implements Listener {
    public Main main;
    public Clean(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onGuiClick(InventoryClickEvent e) {
        if (e.getView().getTitle().contains("Grooming GUI") && e.getClickedInventory() == e.getView().getTopInventory()) {
            Inventory inv = e.getClickedInventory();
            if (inv.getItem(e.getSlot()).getType() == Material.DIRT ||inv.getItem(e.getSlot()).getType() == Material.PODZOL) {
                if (e.getCursor().getType() == Material.SPONGE) {
                    DataHandling dh = new DataHandling(main);
                    String selectedUUIDEntity = dh.readDataString(e.getWhoClicked(), "selectedEntity");
                    Entity ent = Bukkit.getEntity(UUID.fromString(selectedUUIDEntity));
                    new GUI().addItem(inv, new Methods().getMaterial(ent), e.getSlot(), "");

                    String dirtData = new com.stevecv.SWEP.Grooming.GUI(main).getDirtData(ent);
                    String[] dirtDataArray = dirtData.split(",");

                    dirtDataArray[e.getSlot()] = "0";
                    String newDirtData = String.join(",", dirtDataArray);
                    dh.saveData(ent, PersistentDataType.STRING, "grooming", newDirtData);
                }
            }
            e.setCancelled(true);
        }
    }
}
