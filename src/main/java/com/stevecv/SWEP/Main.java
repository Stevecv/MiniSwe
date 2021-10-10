package com.stevecv.SWEP;

import com.stevecv.SWEP.Damage.OnDamage;
import com.stevecv.SWEP.FoodWater.FeedWater;
import com.stevecv.SWEP.FoodWater.Remove;
import com.stevecv.SWEP.Gaits.OnDismount;
import com.stevecv.SWEP.Gaits.UpDown;
import com.stevecv.SWEP.Grooming.Clean;
import com.stevecv.SWEP.Grooming.MakeDirty;
import com.stevecv.SWEP.Grooming.OpenGui;
import com.stevecv.SWEP.HorseCommand.Horse;
import com.stevecv.SWEP.HorseInfo.NoClick;
import com.stevecv.SWEP.HorseInfo.OpenGUI;
import com.stevecv.SWEP.Horseball.Ball.*;
import com.stevecv.SWEP.Levels.OnJump;
import com.stevecv.SWEP.Levels.OnMove;
import com.stevecv.SWEP.Poop.Midday;
import com.stevecv.SWEP.Poop.Midnight;
import com.stevecv.SWEP.Refusals.Jump;
import com.stevecv.SWEP.SpeedJump.LevelUp;
import com.stevecv.SWEP.SpeedJump.Mount;
import com.stevecv.SWEP.SpeedJump.Updater;
import com.stevecv.SWEP.Traits.Bites;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public Main main = this;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BallHit(main), main);
        Bukkit.getPluginManager().registerEvents(new ThrowBall(), main);
        Bukkit.getPluginManager().registerEvents(new PickupBall(main), main);
        Bukkit.getPluginManager().registerEvents(new HurtPlayer(), main);
        Bukkit.getPluginManager().registerEvents(new OpenGUI(main), main);
        Bukkit.getPluginManager().registerEvents(new OnMove(main), main);
        Bukkit.getPluginManager().registerEvents(new OnJump(main), main);
        Bukkit.getPluginManager().registerEvents(new OnDamage(), main);
        Bukkit.getPluginManager().registerEvents(new FeedWater(main), main);
        Bukkit.getPluginManager().registerEvents(new Mount(main), main);
        Bukkit.getPluginManager().registerEvents(new LevelUp(main), main);
        Bukkit.getPluginManager().registerEvents(new OpenGui(main), main);
        Bukkit.getPluginManager().registerEvents(new NoClick(), main);
        Bukkit.getPluginManager().registerEvents(new Jump(main), main);
        Bukkit.getPluginManager().registerEvents(new Clean(main), main);
        Bukkit.getPluginManager().registerEvents(new UpDown(main), main);
        Bukkit.getPluginManager().registerEvents(new OnDismount(main), main);


        Bukkit.getPluginCommand("h").setExecutor(new Horse(main));

        new Remove(main).removeFoodWater(main);
        new MakeDirty(main).makeDirty(main);
        new HasBall().snowball(main);

        new Midnight(main).startMidnightPoop(main);
        new Midday().startMidnightPoop(main);
        new Bites(main).startBites(main);
    }

    @Override
    public void onDisable() {

    }
}
