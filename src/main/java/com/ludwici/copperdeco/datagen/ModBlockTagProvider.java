package com.ludwici.copperdeco.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.ludwici.copperdeco.CopperAgeDecoration.MODID;
import static com.ludwici.copperdeco.registry.BlockRegistry.*;
import static com.ludwici.copperdeco.registry.BlockRegistry.WAXED_OXIDIZED_CUT_COPPER_TILE;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(COPPER_BLOCK_TILE.get())
                .add(CHISELED_COPPER_BLOCK_TILE.get())
                .add(COPPER_GRATE_TILE.get())
                .add(CUT_COPPER_TILE.get())

                .add(EXPOSED_COPPER_TILE.get())
                .add(EXPOSED_CHISELED_COPPER_TILE.get())
                .add(EXPOSED_COPPER_GRATE_TILE.get())
                .add(EXPOSED_CUT_COPPER_TILE.get())

                .add(WEATHERED_COPPER_TILE.get())
                .add(WEATHERED_CHISELED_COPPER_TILE.get())
                .add(WEATHERED_COPPER_GRATE_TILE.get())
                .add(WEATHERED_CUT_COPPER_TILE.get())

                .add(OXIDIZED_COPPER_TILE.get())
                .add(OXIDIZED_CHISELED_COPPER_TILE.get())
                .add(OXIDIZED_COPPER_GRATE_TILE.get())
                .add(OXIDIZED_CUT_COPPER_TILE.get())

                .add(WAXED_COPPER_BLOCK_TILE.get())
                .add(WAXED_EXPOSED_COPPER_TILE.get())
                .add(WAXED_WEATHERED_COPPER_TILE.get())
                .add(WAXED_OXIDIZED_COPPER_TILE.get())

                .add(WAXED_CHISELED_COPPER_TILE.get())
                .add(WAXED_EXPOSED_CHISELED_COPPER_TILE.get())
                .add(WAXED_WEATHERED_CHISELED_COPPER_TILE.get())
                .add(WAXED_OXIDIZED_CHISELED_COPPER_TILE.get())

                .add(WAXED_COPPER_GRATE_TILE.get())
                .add(WAXED_EXPOSED_COPPER_GRATE_TILE.get())
                .add(WAXED_WEATHERED_COPPER_GRATE_TILE.get())
                .add(WAXED_OXIDIZED_COPPER_GRATE_TILE.get())

                .add(WAXED_CUT_COPPER_TILE.get())
                .add(WAXED_EXPOSED_CUT_COPPER_TILE.get())
                .add(WAXED_WEATHERED_CUT_COPPER_TILE.get())
                .add(WAXED_OXIDIZED_CUT_COPPER_TILE.get())
        ;

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(COPPER_BLOCK_TILE.get())
                .add(CHISELED_COPPER_BLOCK_TILE.get())
                .add(COPPER_GRATE_TILE.get())
                .add(CUT_COPPER_TILE.get())

                .add(EXPOSED_COPPER_TILE.get())
                .add(EXPOSED_CHISELED_COPPER_TILE.get())
                .add(EXPOSED_COPPER_GRATE_TILE.get())
                .add(EXPOSED_CUT_COPPER_TILE.get())

                .add(WEATHERED_COPPER_TILE.get())
                .add(WEATHERED_CHISELED_COPPER_TILE.get())
                .add(WEATHERED_COPPER_GRATE_TILE.get())
                .add(WEATHERED_CUT_COPPER_TILE.get())

                .add(OXIDIZED_COPPER_TILE.get())
                .add(OXIDIZED_CHISELED_COPPER_TILE.get())
                .add(OXIDIZED_COPPER_GRATE_TILE.get())
                .add(OXIDIZED_CUT_COPPER_TILE.get())

                .add(WAXED_COPPER_BLOCK_TILE.get())
                .add(WAXED_EXPOSED_COPPER_TILE.get())
                .add(WAXED_WEATHERED_COPPER_TILE.get())
                .add(WAXED_OXIDIZED_COPPER_TILE.get())

                .add(WAXED_CHISELED_COPPER_TILE.get())
                .add(WAXED_EXPOSED_CHISELED_COPPER_TILE.get())
                .add(WAXED_WEATHERED_CHISELED_COPPER_TILE.get())
                .add(WAXED_OXIDIZED_CHISELED_COPPER_TILE.get())

                .add(WAXED_COPPER_GRATE_TILE.get())
                .add(WAXED_EXPOSED_COPPER_GRATE_TILE.get())
                .add(WAXED_WEATHERED_COPPER_GRATE_TILE.get())
                .add(WAXED_OXIDIZED_COPPER_GRATE_TILE.get())

                .add(WAXED_CUT_COPPER_TILE.get())
                .add(WAXED_EXPOSED_CUT_COPPER_TILE.get())
                .add(WAXED_WEATHERED_CUT_COPPER_TILE.get())
                .add(WAXED_OXIDIZED_CUT_COPPER_TILE.get())
        ;
    }
}
