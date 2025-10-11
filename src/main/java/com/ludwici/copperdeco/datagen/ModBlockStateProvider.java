package com.ludwici.copperdeco.datagen;

import com.ludwici.carpetscore.datagen.BlockStateHelper;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.ludwici.copperdeco.CopperAgeDecoration.*;
import static com.ludwici.copperdeco.registry.BlockRegistry.*;

public class ModBlockStateProvider extends BlockStateProvider {
    private BlockStateHelper stateHelper;

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MODID, exFileHelper);
        stateHelper = new BlockStateHelper(this);
    }

    @Override
    protected void registerStatesAndModels() {
        stateHelper.createVariantCarpetWithItem(COPPER_BLOCK_TILE.get(), Blocks.COPPER_BLOCK);
        stateHelper.createVariantCarpetWithItem(EXPOSED_COPPER_TILE.get(), Blocks.EXPOSED_COPPER);
        stateHelper.createVariantCarpetWithItem(WEATHERED_COPPER_TILE.get(), Blocks.WEATHERED_COPPER);
        stateHelper.createVariantCarpetWithItem(OXIDIZED_COPPER_TILE.get(), Blocks.OXIDIZED_COPPER);

        stateHelper.createVariantCarpetWithItem(WAXED_COPPER_BLOCK_TILE.get(), Blocks.COPPER_BLOCK);
        stateHelper.createVariantCarpetWithItem(WAXED_EXPOSED_COPPER_TILE.get(), Blocks.EXPOSED_COPPER);
        stateHelper.createVariantCarpetWithItem(WAXED_WEATHERED_COPPER_TILE.get(), Blocks.WEATHERED_COPPER);
        stateHelper.createVariantCarpetWithItem(WAXED_OXIDIZED_COPPER_TILE.get(), Blocks.OXIDIZED_COPPER);

        stateHelper.createVariantCarpetWithItem(CHISELED_COPPER_BLOCK_TILE.get(), Blocks.CHISELED_COPPER);
        stateHelper.createVariantCarpetWithItem(EXPOSED_CHISELED_COPPER_TILE.get(), Blocks.EXPOSED_CHISELED_COPPER);
        stateHelper.createVariantCarpetWithItem(WEATHERED_CHISELED_COPPER_TILE.get(), Blocks.WEATHERED_CHISELED_COPPER);
        stateHelper.createVariantCarpetWithItem(OXIDIZED_CHISELED_COPPER_TILE.get(), Blocks.OXIDIZED_CHISELED_COPPER);

        stateHelper.createVariantCarpetWithItem(WAXED_CHISELED_COPPER_TILE.get(), Blocks.CHISELED_COPPER);
        stateHelper.createVariantCarpetWithItem(WAXED_EXPOSED_CHISELED_COPPER_TILE.get(), Blocks.EXPOSED_CHISELED_COPPER);
        stateHelper.createVariantCarpetWithItem(WAXED_WEATHERED_CHISELED_COPPER_TILE.get(), Blocks.WEATHERED_CHISELED_COPPER);
        stateHelper.createVariantCarpetWithItem(WAXED_OXIDIZED_CHISELED_COPPER_TILE.get(), Blocks.OXIDIZED_CHISELED_COPPER);

        stateHelper.createTranslucentVariantWithItem(COPPER_GRATE_TILE.get(), Blocks.COPPER_GRATE);
        stateHelper.createTranslucentVariantWithItem(EXPOSED_COPPER_GRATE_TILE.get(), Blocks.EXPOSED_COPPER_GRATE);
        stateHelper.createTranslucentVariantWithItem(WEATHERED_COPPER_GRATE_TILE.get(), Blocks.WEATHERED_COPPER_GRATE);
        stateHelper.createTranslucentVariantWithItem(OXIDIZED_COPPER_GRATE_TILE.get(), Blocks.OXIDIZED_COPPER_GRATE);

        stateHelper.createTranslucentVariantWithItem(WAXED_COPPER_GRATE_TILE.get(), Blocks.COPPER_GRATE);
        stateHelper.createTranslucentVariantWithItem(WAXED_EXPOSED_COPPER_GRATE_TILE.get(), Blocks.EXPOSED_COPPER_GRATE);
        stateHelper.createTranslucentVariantWithItem(WAXED_WEATHERED_COPPER_GRATE_TILE.get(), Blocks.WEATHERED_COPPER_GRATE);
        stateHelper.createTranslucentVariantWithItem(WAXED_OXIDIZED_COPPER_GRATE_TILE.get(), Blocks.OXIDIZED_COPPER_GRATE);

        stateHelper.createVariantCarpetWithItem(CUT_COPPER_TILE.get(), Blocks.CUT_COPPER);
        stateHelper.createVariantCarpetWithItem(EXPOSED_CUT_COPPER_TILE.get(), Blocks.EXPOSED_CUT_COPPER);
        stateHelper.createVariantCarpetWithItem(WEATHERED_CUT_COPPER_TILE.get(), Blocks.WEATHERED_CUT_COPPER);
        stateHelper.createVariantCarpetWithItem(OXIDIZED_CUT_COPPER_TILE.get(), Blocks.OXIDIZED_CUT_COPPER);

        stateHelper.createVariantCarpetWithItem(WAXED_CUT_COPPER_TILE.get(), Blocks.CUT_COPPER);
        stateHelper.createVariantCarpetWithItem(WAXED_EXPOSED_CUT_COPPER_TILE.get(), Blocks.EXPOSED_CUT_COPPER);
        stateHelper.createVariantCarpetWithItem(WAXED_WEATHERED_CUT_COPPER_TILE.get(), Blocks.WEATHERED_CUT_COPPER);
        stateHelper.createVariantCarpetWithItem(WAXED_OXIDIZED_CUT_COPPER_TILE.get(), Blocks.OXIDIZED_CUT_COPPER);
    }

}
