package com.ludwici.copperdeco;

import com.ludwici.copperdeco.registry.BlockRegistry;
import net.fabricmc.api.ModInitializer;

public class CopperDeco implements ModInitializer {
    public static final String MODID = "copperdeco";

    @Override
    public void onInitialize() {
        BlockRegistry.initialize();
    }
}
