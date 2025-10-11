package com.ludwici.copperdeco.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

import static com.ludwici.copperdeco.block.BaseCopperTileBlock.WAXABLES;
import static com.ludwici.copperdeco.registry.BlockRegistry.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static RecipeOutput selfRecipeOutput;

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        selfRecipeOutput = recipeOutput;
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, COPPER_BLOCK_TILE.get(), 1)
                .pattern("## ")
                .pattern("## ")
                .pattern("   ")
                .define('#', Items.COPPER_INGOT)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(recipeOutput)
                ;

        copperBase(COPPER_BLOCK_TILE, COPPER_GRATE_TILE, CUT_COPPER_TILE, CHISELED_COPPER_BLOCK_TILE);
        copperBase(EXPOSED_COPPER_TILE, EXPOSED_COPPER_GRATE_TILE, EXPOSED_CUT_COPPER_TILE, EXPOSED_CHISELED_COPPER_TILE);
        copperBase(WEATHERED_COPPER_TILE, WEATHERED_COPPER_GRATE_TILE, WEATHERED_CUT_COPPER_TILE, WEATHERED_CHISELED_COPPER_TILE);
        copperBase(OXIDIZED_COPPER_TILE, OXIDIZED_COPPER_GRATE_TILE, OXIDIZED_CUT_COPPER_TILE, OXIDIZED_CHISELED_COPPER_TILE);

        copperBase(WAXED_COPPER_BLOCK_TILE, WAXED_COPPER_GRATE_TILE, WAXED_CUT_COPPER_TILE, WAXED_CHISELED_COPPER_TILE);
        copperBase(WAXED_EXPOSED_COPPER_TILE, WAXED_EXPOSED_COPPER_GRATE_TILE, WAXED_EXPOSED_CUT_COPPER_TILE, WAXED_EXPOSED_CHISELED_COPPER_TILE);
        copperBase(WAXED_WEATHERED_COPPER_TILE, WAXED_WEATHERED_COPPER_GRATE_TILE, WAXED_WEATHERED_CUT_COPPER_TILE, WAXED_WEATHERED_CHISELED_COPPER_TILE);
        copperBase(WAXED_OXIDIZED_COPPER_TILE, WAXED_OXIDIZED_COPPER_GRATE_TILE, WAXED_OXIDIZED_CUT_COPPER_TILE, WAXED_OXIDIZED_CHISELED_COPPER_TILE);

        WAXABLES.get().forEach(ModRecipeProvider::waxedCopper);
    }

    protected static void copperBase(ItemLike blockTile, ItemLike grateTile, ItemLike cutTile, ItemLike chiseledTile) {
        stonecutterResultFromBase(cutTile, blockTile, 4);
        stonecutterResultFromBase(chiseledTile, blockTile, 4);
        stonecutterResultFromBase(chiseledTile, cutTile);
        stonecutterResultFromBase(grateTile, blockTile, 4);
    }

    protected static void waxedCopper(ItemLike origTile, ItemLike waxedTile) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, waxedTile)
                .requires(Items.HONEYCOMB)
                .requires(origTile)
                .unlockedBy(getHasName(waxedTile), has(waxedTile))
                .save(selfRecipeOutput)
        ;
    }

    protected static void stonecutterResultFromBase(ItemLike result, ItemLike material) {
        stonecutterResultFromBase(result, material, 1);
    }

    protected static void stonecutterResultFromBase(ItemLike result, ItemLike material, int resultCount) {
        stonecutterResultFromBase(selfRecipeOutput, RecipeCategory.BUILDING_BLOCKS, result, material, resultCount);
    }
}
