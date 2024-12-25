package me.alexhamy.alexhamymod;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                RegistryEntryLookup<Item> lookup = BuiltinRegistries.createWrapperLookup().getOrThrow(RegistryKeys.ITEM);

                ShapedRecipeJsonBuilder.create(lookup, RecipeCategory.MISC, Register.SWORD_PHOSPHOPHYLLITE, 1)
                        .pattern(" R ")
                        .pattern(" R ")
                        .pattern(" S ")
                        .input('R', Register.PHOSPHOPHYLLITE)
                        .input('S', Items.STICK)
                        .criterion(hasItem(Register.PHOSPHOPHYLLITE), conditionsFromItem(Register.PHOSPHOPHYLLITE))
                        .offerTo(recipeExporter);
            }
        };
    }

    @Override
    public String getName() {
        return Identifier.of(Alexhamymod.MOD_ID,"recipe_provider").toString();
    }

}