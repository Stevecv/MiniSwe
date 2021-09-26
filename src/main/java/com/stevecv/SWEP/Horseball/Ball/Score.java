package com.stevecv.SWEP.Horseball.Ball;

import com.stevecv.SWEP.Horseball.Chat.Sending;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Score {
    public void score(World world, Player score) {
        if (!world.getName().equalsIgnoreCase("Horseball")) { return; }

        new Sending().sendToAllInWorld(score.getName() + " scored!");
    }
}
