package com.ludwici.copperdeco.datagen;

import com.ludwici.carpetscore.api.datagen.LootTableHelper;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.stream.Collectors;

import static com.ludwici.copperdeco.registry.BlockRegistry.BLOCKS;

public class ModLootTableGenerator extends LootTableHelper {
    protected ModLootTableGenerator(LootTableSubProvider.Context context) {
        super(context);
    }

    @Override
    protected void generate() {
        BLOCKS.getEntries().forEach(block -> createSelfVariantItemTable(block.value()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCKS.getEntries().stream().map(DeferredHolder::value).collect(Collectors.toList());
    }
}
