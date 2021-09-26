package com.stevecv.SWEP.HorseInfo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class NoClick implements Listener {
    @EventHandler
    public void GuiClick(InventoryClickEvent e) {
        if (e.getView().getTitle().contains("info") && e.getClickedInventory() == e.getView().getTopInventory()) {
            e.setCancelled(true);
        }
    }
}
