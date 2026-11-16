package com.ludwici.copperdeco;

import com.ludwici.copperdeco.registry.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;

public class CopperDecoClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRegistry.ALL_BLOCKS.forEach(block -> {
            String id = BuiltInRegistries.BLOCK.getKey(block).getPath();
            if (id.contains("copper_grate")) {
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.translucent());
            }
        });
    }
}
