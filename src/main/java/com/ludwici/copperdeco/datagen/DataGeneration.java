package com.ludwici.copperdeco.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.ludwici.copperdeco.CopperAgeDecoration.MODID;

@EventBusSubscriber(modid = MODID)
public class DataGeneration {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event) {
        event.createProvider(ModBlockStateProvider::new);
        event.createProvider(ModLootTableProvider::new);
        event.createProvider(ModRecipeProvider.Runner::new);
        event.createProvider(ModBlockTagProvider::new);
    }

}
