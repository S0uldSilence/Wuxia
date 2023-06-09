package com.github.s0uldsilence.wuxia.datagen.Providers;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.datagen.BookLangHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EnUsProvider extends LanguageProvider {

    public EnUsProvider(PackOutput output, String modid) {
        super(output, modid, "en_us");
    }



    private void addDemoBook(){
        //We again set up a lang helper to keep track of the translation keys for us.
        //Forge language provider does not give us access to this.modid, so we get it from our main mod class
        var helper = ModonomiconAPI.get().getLangHelper(Wuxia.MODID);
        helper.book("demo"); //we tell the helper the book we're in.
        this.add(helper.bookName(), "Demo Book"); //and now we add the actual textual book name
        this.add(helper.bookTooltip(), "A book to showcase & test Modonomicon features."); //and the tooltip text

        this.addDemoBookFeaturesCategory(helper);
    }

    private void addDemoBookFeaturesCategory(BookLangHelper helper) {
        helper.category("features"); //tell the helper the category we are in
        this.add(helper.categoryName(), "Features Category"); //annd provide the category name text

        this.addDemoBookMultiblockEntry(helper);
    }

    private void addDemoBookMultiblockEntry(BookLangHelper helper) {
        helper.entry("multiblock"); //tell the helper the entry we are in
        this.add(helper.entryName(), "Multiblock Entry"); //provide the entry name
        this.add(helper.entryDescription(), "An entry showcasing a multiblock."); //and description

        helper.page("intro"); //now we configure the intro page
        this.add(helper.pageTitle(), "Multiblock Page"); //page title
        this.add(helper.pageText(), "Multiblock pages allow to preview multiblocks both in the book and in the world."); //page text

        helper.page("multiblock"); //and finally the multiblock page
        //now provide the multiblock name
        //the lang helper does not handle multiblocks, so we manually add the same key we provided in the DemoBookProvider
        this.add("multiblocks.modonomicon.blockentity", "Blockentity Multiblock.");
        this.add(helper.pageText(), "A sample multiblock."); //and the multiblock page text
    }
    @Override
    protected void addTranslations() {
        this.addDemoBook();

        add("item.wuxia.mana_crystal", "Mana Crystal");
        add("item.wuxia.green_jade", "Green Jade");
        add("block.wuxia.mana_crystal_ore", "Mana Crystal Ore");
        add("block.wuxia.green_jade_block", "Green Jade Block");
        add("block.wuxia.green_jade_ore", "Green Jade Ore");
        add("block.wuxia.deepslate_green_jade_ore", "Deepslate Green Jade Ore");
        add("creativemodetab.wuxia_tab", "Wuxia");
        add("key.category.wuxia.keycategory", "Wuxia Mod");
        add("key.wuxia.cultivate", "Cultivate");
        add("guis.wuxia.basicpillfurnace", "Basic Pill Furnace");
        add("recipe_category.wuxia.basic_pill_furnace", "Basic Pill Furnace Recipes");
        add("screen.wuxia.cultivation_checker", "Your Current Cultivation");
        add("gui.wuxia.prev_page", "Previous Page");
        add("gui.wuxia.next_page", "Next Page");
        add("wuxia.rarity.all", "All");
        add("wuxia.rarity.common", "Common");
        add("wuxia.rarity.uncommon", "Uncommon");
        add("wuxia.rarity.rare", "Rare");
        add("wuxia.rarity.epic", "Epic");
        add("wuxia.rarity.legendary", "Legendary");
        add("gui.wuxia.filter.all", "All");
        add("gui.wuxia.filter.common", "Common");
        add("gui.wuxia.filter.uncommon", "Uncommon");
        add("gui.wuxia.filter.rare", "Rare");
        add("gui.wuxia.filter.epic", "Epic");
        add("gui.wuxia.filter.legendary", "Legendary");

        add("book.wuxia.wuxia_tome.name", "Wuxia Tome");
        add("block.wuxia.essence_storage", "Essence Storage");
    }
}
