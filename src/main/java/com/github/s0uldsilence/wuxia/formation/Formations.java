package com.github.s0uldsilence.wuxia.formation;

import com.github.s0uldsilence.wuxia.block.ModBlocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Formations {
    private static final Map<Integer, Formation> formationsById = new HashMap<>();
    private static final Map<String, Formation> formationsByName = new HashMap<>();

    public static void registerFormation(Formation formation) {
        formationsById.put(formation.getId(), formation);
        formationsByName.put(formation.getName(), formation);
    }

    public static Formation getFormationById(int id) {
        return formationsById.get(id);
    }

    public static Formation getFormationByName(String name) {
        return formationsByName.get(name);
    }

    public static List<Integer> getRegisteredFormationIds() {
        return new ArrayList<>(formationsById.keySet());
    }

    public static List<String> getRegisteredFormationNames() {
        return new ArrayList<>(formationsByName.keySet());
    }

    public static void registerFormations() {
        Formation fireFormation = new Formation("Fire Formation", 1);
        fireFormation.addBlock(ModBlocks.FIRE_ELEMENT_RUNE_BLOCK.get(), 0, 1, 0);
        fireFormation.addBlock(ModBlocks.FIRE_ELEMENT_RUNE_BLOCK.get(), 0, 2, 0);
        fireFormation.addBlock(ModBlocks.FIRE_ELEMENT_RUNE_BLOCK.get(), 0, 3, 0);
        fireFormation.addBlock(ModBlocks.EARTH_ELEMENT_RUNE_BLOCK.get(), 0, 4, 0);
        fireFormation.addBlock(ModBlocks.EARTH_ELEMENT_RUNE_BLOCK.get(), 0, 5, 0);
        Formations.registerFormation(fireFormation);

        Formation waterFormation = new Formation("Water Formation", 2);
        waterFormation.addBlock(ModBlocks.WATER_ELEMENT_RUNE_BLOCK.get(), 0, 1, 0);
        waterFormation.addBlock(ModBlocks.WATER_ELEMENT_RUNE_BLOCK.get(), 0, 2, 0);
        waterFormation.addBlock(ModBlocks.WATER_ELEMENT_RUNE_BLOCK.get(), 0, 3, 0);
        waterFormation.addBlock(ModBlocks.EARTH_ELEMENT_RUNE_BLOCK.get(), 0, 4, 1);
        waterFormation.addBlock(ModBlocks.EARTH_ELEMENT_RUNE_BLOCK.get(), 0, 5, -1);
        Formations.registerFormation(waterFormation);

        Formation myFormation = new Formation("My Formation", 3);
        myFormation.addBlock(ModBlocks.GREEN_JADE_BLOCK.get(), 0, 1, 0);
        myFormation.addBlock(ModBlocks.GREEN_JADE_BLOCK.get(), 0, 2, 0);
// Add more blocks to the formation as necessary

        Formations.registerFormation(myFormation);
    }
}