package com.ludwici.copperdeco.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

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
        shaped(RecipeCategory.BUILDING_BLOCKS, COPPER_BLOCK_TILE.weathering().unaffected().get(), 1)
                .pattern("##")
                .pattern("##")
                .define('#', Items.COPPER_INGOT)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(output);

        for (boolean waxed : new boolean[] {false, true}) {
            var regular = waxed ? COPPER_BLOCK_TILE.waxed() : COPPER_BLOCK_TILE.weathering();
            var grate = waxed ? COPPER_GRATE_TILE.waxed() : COPPER_GRATE_TILE.weathering();
            var cut = waxed ? CUT_COPPER_TILE.waxed() : CUT_COPPER_TILE.weathering();
            var chiseled = waxed ? CHISELED_COPPER_TILE.waxed() : CHISELED_COPPER_TILE.weathering();

            copperBase(regular.unaffected().get(), grate.unaffected().get(), cut.unaffected().get(), chiseled.unaffected().get());
            copperBase(regular.exposed().get(), grate.exposed().get(), cut.exposed().get(), chiseled.exposed().get());
            copperBase(regular.weathered().get(), grate.weathered().get(), cut.weathered().get(), chiseled.weathered().get());
            copperBase(regular.oxidized().get(), grate.oxidized().get(), cut.oxidized().get(), chiseled.oxidized().get());
        }

        WAXABLES.get().forEach(this::waxedCopper);
    }

    protected void copperBase(ItemLike blockTile, ItemLike grateTile, ItemLike cutTile, ItemLike chiseledTile) {
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, cutTile, blockTile, 4);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, chiseledTile, blockTile, 4);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, chiseledTile, cutTile, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, grateTile, blockTile, 4);
    }

    protected void waxedCopper(ItemLike origTile, ItemLike waxedTile) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, waxedTile)
                .requires(Items.HONEYCOMB)
                .requires(origTile)
                .unlockedBy(getHasName(waxedTile), has(waxedTile))
                .save(output);
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
