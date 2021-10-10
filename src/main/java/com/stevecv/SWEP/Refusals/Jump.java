package com.stevecv.SWEP.Refusals;

import com.stevecv.SWEP.FallingOff.FallOff;
import com.stevecv.SWEP.HorseInfo.Traits;
import com.stevecv.SWEP.Main;
import com.stevecv.SWEP.Methods.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.HorseJumpEvent;

public class Jump implements Listener {
    Main main;
    public Jump(Main main) {
        this.main = main;
    }


    @EventHandler
    public void onJump(HorseJumpEvent e) {
        Entity ent = e.getEntity();
        if (e.getEntity().getPassengers() == null) { return; }

        Traits t = new Traits(main);
        String traits = t.getTraits(ent);

        if (traits.contains("Bombproof")) {
            if (new Random().randomRound(100.0, 0.0) > 2) { return; }
        } else if (traits.contains("Flighty")) {
            if (new Random().randomRound(100.0, 0.0) > 8) { return; }
        } else {
            if (new Random().randomRound(100.0, 0.0) > 4) { return; }
        }

        Player rider = (Player) ent.getPassengers().get(0);
        e.setCancelled(true);
        rider.sendActionBar(ChatColor.GREEN + "Refusal");
        if (new Random().randomRound(100.0, 0.0) > 15) { return; }
        new FallOff().fallOff(rider.getPlayer());
    }
}
