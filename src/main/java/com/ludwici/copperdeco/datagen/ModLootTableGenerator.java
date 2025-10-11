package com.ludwici.copperdeco.datagen;

//import com.ludwici.carpetscore.datagen.LootTableHelper;
import com.ludwici.carpetscore.api.datagen.LootTableHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.stream.Collectors;

import static com.ludwici.copperdeco.registry.BlockRegistry.*;

public class ModLootTableGenerator extends LootTableHelper {
    protected ModLootTableGenerator(HolderLookup.Provider provider) {
        super(provider);
    }

    @Override
    protected void generate() {
        createSelfVariantItemTable(COPPER_BLOCK_TILE);
        createSelfVariantItemTable(CHISELED_COPPER_BLOCK_TILE);
        createSelfVariantItemTable(COPPER_GRATE_TILE);
        createSelfVariantItemTable(CUT_COPPER_TILE);

        createSelfVariantItemTable(EXPOSED_COPPER_TILE);
        createSelfVariantItemTable(EXPOSED_CHISELED_COPPER_TILE);
        createSelfVariantItemTable(EXPOSED_COPPER_GRATE_TILE);
        createSelfVariantItemTable(EXPOSED_CUT_COPPER_TILE);

        createSelfVariantItemTable(WEATHERED_COPPER_TILE);
        createSelfVariantItemTable(WEATHERED_CHISELED_COPPER_TILE);
        createSelfVariantItemTable(WEATHERED_COPPER_GRATE_TILE);
        createSelfVariantItemTable(WEATHERED_CUT_COPPER_TILE);

        createSelfVariantItemTable(OXIDIZED_COPPER_TILE);
        createSelfVariantItemTable(OXIDIZED_CHISELED_COPPER_TILE);
        createSelfVariantItemTable(OXIDIZED_COPPER_GRATE_TILE);
        createSelfVariantItemTable(OXIDIZED_CUT_COPPER_TILE);

        createSelfVariantItemTable(WAXED_COPPER_BLOCK_TILE);
        createSelfVariantItemTable(WAXED_EXPOSED_COPPER_TILE);
        createSelfVariantItemTable(WAXED_WEATHERED_COPPER_TILE);
        createSelfVariantItemTable(WAXED_OXIDIZED_COPPER_TILE);

        createSelfVariantItemTable(WAXED_CHISELED_COPPER_TILE);
        createSelfVariantItemTable(WAXED_EXPOSED_CHISELED_COPPER_TILE);
        createSelfVariantItemTable(WAXED_WEATHERED_CHISELED_COPPER_TILE);
        createSelfVariantItemTable(WAXED_OXIDIZED_CHISELED_COPPER_TILE);

        createSelfVariantItemTable(WAXED_COPPER_GRATE_TILE);
        createSelfVariantItemTable(WAXED_EXPOSED_COPPER_GRATE_TILE);
        createSelfVariantItemTable(WAXED_WEATHERED_COPPER_GRATE_TILE);
        createSelfVariantItemTable(WAXED_OXIDIZED_COPPER_GRATE_TILE);

        createSelfVariantItemTable(WAXED_CUT_COPPER_TILE);
        createSelfVariantItemTable(WAXED_EXPOSED_CUT_COPPER_TILE);
        createSelfVariantItemTable(WAXED_WEATHERED_CUT_COPPER_TILE);
        createSelfVariantItemTable(WAXED_OXIDIZED_CUT_COPPER_TILE);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCKS.getEntries().stream().map(DeferredHolder::value).collect(Collectors.toList());
    }
}
