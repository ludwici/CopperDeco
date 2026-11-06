package com.ludwici.copperdeco.datagen;

import com.ludwici.carpetscore.api.datagen.BlockStateHelper;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopperCollection;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.ludwici.copperdeco.CopperAgeDecoration.MODID;
import static com.ludwici.copperdeco.registry.BlockRegistry.*;

public class ModBlockStateProvider extends ModelProvider {
    private BlockStateHelper stateHelper;

    public ModBlockStateProvider(PackOutput output) {
        super(output, MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        stateHelper = new BlockStateHelper(blockModels);
        createFamily(COPPER_BLOCK_TILE, Blocks.COPPER_BLOCK, false);
        createFamily(CHISELED_COPPER_TILE, Blocks.CHISELED_COPPER, false);
        createFamily(COPPER_GRATE_TILE, Blocks.COPPER_GRATE, true);
        createFamily(CUT_COPPER_TILE, Blocks.CUT_COPPER, false);
    }

    private void createFamily(WeatheringCopperCollection<DeferredBlock<Block>> tiles, WeatheringCopperCollection<Block> originals, boolean translucent) {
        WeatheringCopperCollection.zipApply(tiles.weathering(), originals.weathering(), (tile, original) -> create(tile.get(), original, translucent));
        WeatheringCopperCollection.zipApply(tiles.waxed(), originals.weathering(), (tile, original) -> create(tile.get(), original, translucent));
    }

    private void create(Block tile, Block original, boolean translucent) {
        if (translucent) {
            stateHelper.createTranslucentVariantWithItem(tile, original);
        } else {
            stateHelper.createVariantCarpetWithItem(tile, original);
        }
    }
}
