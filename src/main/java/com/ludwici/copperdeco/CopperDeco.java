package com.ludwici.copperdeco;

import com.ludwici.copperdeco.registry.BlockRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class CopperDeco implements ModInitializer {
    public static final String MODID = "copperdeco";

    @Override
    public void onInitialize() {
        BlockRegistry.initialize();
        ResourceKey<CreativeModeTab> tabKey = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MODID, "example_tab"));
        CreativeModeTab tab = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable("itemGroup.copperdeco"))

                .icon(() -> BlockRegistry.COPPER_BLOCK_TILE.weathering().unaffected().asItem().getDefaultInstance())
                .displayItems((parameters, output) -> BlockRegistry.ALL_TILES.forEach(tiles -> tiles.forEach(output::accept)))
                .build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, tabKey, tab);
    }
}
