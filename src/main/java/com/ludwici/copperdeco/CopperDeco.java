package com.ludwici.copperdeco;

import com.ludwici.copperdeco.registry.BlockRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CopperDeco.MODID)
public class CopperDeco {
    public static final String MODID = "copperdeco";

    public CopperDeco() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockRegistry.BLOCKS.register(eventBus);
        BlockRegistry.ITEMS.register(eventBus);
        BlockRegistry.TABS.register(eventBus);
    }
}
