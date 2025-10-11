package com.ludwici.copperdeco.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

import static com.ludwici.copperdeco.CopperAgeDecoration.MODID;
import static com.ludwici.copperdeco.block.BaseCopperTileBlock.WAXABLES;
import static com.ludwici.copperdeco.registry.BlockRegistry.*;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.BUILDING_BLOCKS, COPPER_BLOCK_TILE.get(), 1)
                .pattern("## ")
                .pattern("## ")
                .pattern("   ")
                .define('#', Items.COPPER_INGOT)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(output)
                ;

        copperBase(COPPER_BLOCK_TILE, COPPER_GRATE_TILE, CUT_COPPER_TILE, CHISELED_COPPER_BLOCK_TILE);
        copperBase(EXPOSED_COPPER_TILE, EXPOSED_COPPER_GRATE_TILE, EXPOSED_CUT_COPPER_TILE, EXPOSED_CHISELED_COPPER_TILE);
        copperBase(WEATHERED_COPPER_TILE, WEATHERED_COPPER_GRATE_TILE, WEATHERED_CUT_COPPER_TILE, WEATHERED_CHISELED_COPPER_TILE);
        copperBase(OXIDIZED_COPPER_TILE, OXIDIZED_COPPER_GRATE_TILE, OXIDIZED_CUT_COPPER_TILE, OXIDIZED_CHISELED_COPPER_TILE);

        copperBase(WAXED_COPPER_BLOCK_TILE, WAXED_COPPER_GRATE_TILE, WAXED_CUT_COPPER_TILE, WAXED_CHISELED_COPPER_TILE);
        copperBase(WAXED_EXPOSED_COPPER_TILE, WAXED_EXPOSED_COPPER_GRATE_TILE, WAXED_EXPOSED_CUT_COPPER_TILE, WAXED_EXPOSED_CHISELED_COPPER_TILE);
        copperBase(WAXED_WEATHERED_COPPER_TILE, WAXED_WEATHERED_COPPER_GRATE_TILE, WAXED_WEATHERED_CUT_COPPER_TILE, WAXED_WEATHERED_CHISELED_COPPER_TILE);
        copperBase(WAXED_OXIDIZED_COPPER_TILE, WAXED_OXIDIZED_COPPER_GRATE_TILE, WAXED_OXIDIZED_CUT_COPPER_TILE, WAXED_OXIDIZED_CHISELED_COPPER_TILE);

        WAXABLES.get().forEach(this::waxedCopper);
    }

    protected void copperBase(ItemLike blockTile, ItemLike grateTile, ItemLike cutTile, ItemLike chiseledTile) {
        stonecutterResultFromBase(cutTile, blockTile, 4);
        stonecutterResultFromBase(chiseledTile, blockTile, 4);
        stonecutterResultFromBase(chiseledTile, cutTile);
        stonecutterResultFromBase(grateTile, blockTile, 4);
    }

    protected void waxedCopper(ItemLike origTile, ItemLike waxedTile) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, waxedTile)
                .requires(Items.HONEYCOMB)
                .requires(origTile)
                .unlockedBy(getHasName(waxedTile), has(waxedTile))
                .save(output)
        ;
    }

    protected void stonecutterResultFromBase(ItemLike result, ItemLike material) {
        stonecutterResultFromBase(result, material, 1);
    }

    protected void stonecutterResultFromBase(ItemLike result, ItemLike material, int resultCount) {
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, result, material, resultCount);
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return MODID + ":recipe_provider";
        }
    }
}
