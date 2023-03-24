package com.github.s0uldsilence.wuxia.datagen;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput gen, String locale) {
        super(gen, Wuxia.MODID, locale);
    }

    @Override
    protected void addTranslations() {


        add(ModItems.MANA_CRYSTAL.get(), "Mana Crystal");
        add(ModItems.GREEN_JADE.get(), "Green Jade");

    }
}