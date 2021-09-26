package com.stevecv.SWEP.HorseInfo;

import com.stevecv.SWEP.Data.DataHandling;
import com.stevecv.SWEP.Main;
import com.stevecv.SWEP.Methods.Random;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.stevecv.SWEP.Methods.Random.ifChance;

public class Traits {
    public Main main;
    public Traits(Main main) {
        this.main = main;
    }

    public String generateTraits(Entity e) {
        String[] goodTraits = { "Energetic", "Playful", "Bombproof", "Sporty" };
        String[] badTraits = { "Foodie", "Flighty", "Lazy", "Mouthy" };

        List<String> goodTraitList = Arrays.asList(goodTraits);
        List<String> badTraitList = Arrays.asList(badTraits);

        Collections.shuffle(goodTraitList);
        Collections.shuffle(badTraitList);

        String traits = goodTraits[0] + "," + badTraits[0];

        if (ifChance(50)) {
            traits = traits + "," + goodTraits[1];
        } else {
            traits = traits + "," + badTraits[1];
        }

        return traits;
    }


    public String getTraits(Entity e) {
        DataHandling dh = new DataHandling(main);
        String traits = dh.readDataString(e, "traits");

        if (traits == null) {
            traits = generateTraits(e);
            dh.saveData(e, PersistentDataType.STRING, "traits", traits);
        }
        return traits;
    }
}
