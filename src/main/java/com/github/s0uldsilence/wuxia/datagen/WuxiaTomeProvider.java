package com.github.s0uldsilence.wuxia.datagen;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.datagen.BookLangHelper;
import com.klikli_dev.modonomicon.api.datagen.BookProvider;
import com.klikli_dev.modonomicon.api.datagen.EntryLocationHelper;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;

public class WuxiaTomeProvider extends BookProvider {


    /**
     * @param packOutput
     * @param modid
     * @param lang       The LanguageProvider to fill with this book provider. IMPORTANT: the Languag Provider needs to be added to the DataGenerator AFTER the BookProvider.
     */
    public WuxiaTomeProvider(PackOutput packOutput, String modid, LanguageProvider lang) {
        super(packOutput, modid, lang);
    }

    private BookModel makeWuxiaTome(String bookName) {
        //The lang helper keeps track of the "DescriptionIds", that is, the language keys for translations, for us
        var helper = ModonomiconAPI.get().getLangHelper(this.modid);

        //we tell the helper the book we're in.
        helper.book(bookName);

        //Now we create the book with settings of our choosing
        var demoBook = BookModel.create(
                        this.modLoc(bookName), //the id of the book. modLoc() prepends the mod id.
                        helper.bookName() //the name of the book. The lang helper gives us the correct translation key.

                )
                .withTooltip(helper.bookTooltip()) //the hover tooltip for the book. Again we get a translation key.
                .withGenerateBookItem(true) //auto-generate a book item for us.
                .withModel(new ResourceLocation("modonomicon:modonomicon_red"))//use the default red modonomicon icon for the book
                //.withCreativeTab(new ResourceLocation(Wuxia.MODID,"wuxia"))
                .withCreativeTab(new ResourceLocation("modonomicon","modonomicon"))
                //Important: On 1.19.3 the creative tab takes a resource location:
                //           .withCreativeTab(new ResourceLocation("modonomicon","modonomicon"))
                ;
        return demoBook;
    }


    @Override
    protected void generate() {
        //call our code that generates a book with the id "demo"
        var wuxiaTome = this.makeWuxiaTome("wuxia_tome");

        //then add the book to the list of books to generate
        this.add(wuxiaTome);
    }
}