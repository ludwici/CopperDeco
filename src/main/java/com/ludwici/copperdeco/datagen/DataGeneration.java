package com.ludwici.copperdeco.datagen;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

import static com.ludwici.copperdeco.CopperAgeDecoration.MODID;

@EventBusSubscriber(modid = MODID)
public class DataGeneration {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event) {
        event.createProvider(ModBlockStateProvider::new);
        event.createProvider(ModBlockTagProvider::new);
    }

    @SubscribeEvent
    public static void onGatherRegistryEntries(GatherDataRegistryEntriesEvent event) {
        event.recipe(ModRecipeProvider::new);
        event.lootTable(new LootTableProvider.SubProviderEntry(ModLootTableGenerator::new, LootContextParamSets.BLOCK));
    }
}
