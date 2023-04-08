package com.github.s0uldsilence.wuxia.formation;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.multiblock.Multiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Formations {
    private static final Map<String, Formation> formationsByName = new HashMap<>();

    public static void registerFormation(Formation formation) {
        formationsByName.put(formation.getName(), formation);
    }

    public static List<String> getRegisteredFormationNames() {
        return new ArrayList<>(formationsByName.keySet());
    }

    public static List<String> getRegisteredFormationPaths() {
        List<String> formationPaths = new ArrayList<>();
        for (Formation formation : formationsByName.values()) {
            formationPaths.add(formation.getPath());
        }
        return formationPaths;
    }

    public static void registerFormations() {
        Formation fireFormation = new Formation("Fire Formation", "placement/fire_formation");
        Formations.registerFormation(fireFormation);

        Formation waterFormation = new Formation("Water Formation", "placement/water_formation");
        Formations.registerFormation(waterFormation);

        Formation growFormation = new Formation("Grow Formation", "placement/grow_formation");
        Formations.registerFormation(growFormation);
    }

    public static String getValidFormationPath(Level level, BlockPos pos){
        for (String path : getRegisteredFormationPaths()) {
            Multiblock formation = ModonomiconAPI.get().getMultiblock(new ResourceLocation(Wuxia.MODID, path));
            if (formation.validate(level, pos) != null) {
                return path;
            }
        }
        return null;
    }
    public static String getValidFormationName(Level level, BlockPos pos) {
        for (Formation formation : formationsByName.values()) {
            Multiblock multiblock = ModonomiconAPI.get().getMultiblock(new ResourceLocation(Wuxia.MODID, formation.getPath()));
            if (multiblock.validate(level, pos) != null) {
                return formation.getName();
            }
        }
        return null;
    }
}