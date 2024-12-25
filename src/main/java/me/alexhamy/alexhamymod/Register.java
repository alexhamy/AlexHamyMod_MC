package me.alexhamy.alexhamymod;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Function;

public class Register {

    private Register() {
    }

    public static final Item PHOSPHOPHYLLITE = register("phosphophyllite", Item::new, new Item.Settings());
    public static final Item SWORD_PHOSPHOPHYLLITE = register("sword_phosphophyllite", Item::new, new Item.Settings());


    public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Alexhamymod.MOD_ID, path));
        return Items.register(registryKey, factory, settings);
    }

    public static final Block ORE_PHOSPHOPHYLLITE = register("ore_phosphophyllite", Block::new,
            Block.Settings
                    .create()
                    .strength(8.0f)
                    .requiresTool()
    );

    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(Alexhamymod.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }

    public static final RegistryKey<PlacedFeature> CUSTOM_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Alexhamymod.MOD_ID,"ore_phosphophyllite"));


    public static void registerModItems(){
        Alexhamymod.LOGGER.info("Registered items");
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, CUSTOM_ORE_PLACED_KEY);
    }
}
