package com.stevecv.SWEP.HorseCommand;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import javax.xml.crypto.Data;

public class Horse implements CommandExecutor {
    public Main main;
    public Horse(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        DataHandling dh = new DataHandling(main);
        Player owner = (Player) commandSender;

        if (strings.length >= 0) {
            if (strings[0].equalsIgnoreCase("claim") && strings.length >= 1) {
                String name = strings[1];
                Entity horse = owner.getVehicle();

                dh.saveData(horse, PersistentDataType.STRING, "name", name);
                dh.saveData(horse, PersistentDataType.STRING, "owner", owner.getUniqueId().toString());

                horse.setCustomName(name);
                horse.setCustomNameVisible(true);

                owner.sendMessage(ChatColor.GREEN + "Successfully claimed " + name + "!");
            } else {
                owner.sendMessage(ChatColor.RED + "You must name your horse!");
            }

            if (strings[0].equalsIgnoreCase("trust") && strings.length >= 1) {
                Entity e = owner.getVehicle();

                String ownerUUID = dh.readDataString(e, "owner");
                if (ownerUUID == owner.getUniqueId().toString()) {
                    String currentTrustedPlayers = dh.readDataString(e, "trustedPlayers");
                    if (currentTrustedPlayers == null) {
                        currentTrustedPlayers = "";
                    }

                    Player trustee = Bukkit.getPlayer(strings[1]);
                    String trusteeUUID = trustee.getUniqueId().toString();
                    if (!currentTrustedPlayers.contains(trusteeUUID + ",")) {
                        currentTrustedPlayers = currentTrustedPlayers + trusteeUUID + ",";

                        dh.saveData(e, PersistentDataType.STRING, "trustedPlayers", currentTrustedPlayers);
                    } else {
                        owner.sendMessage(ChatColor.RED + "You have already trusted this player!");
                    }
                } else {
                    owner.sendMessage(ChatColor.RED + "You do not own this horse!");
                }
            }
        } else {
            owner.sendMessage(ChatColor.RED + "Choose a sub command; ");
        }
        return true;
    }
}
