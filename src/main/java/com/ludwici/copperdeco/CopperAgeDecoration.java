package com.ludwici.copperdeco;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.ludwici.copperdeco.registry.BlockRegistry.ALL_TILES;
import static com.ludwici.copperdeco.registry.BlockRegistry.BLOCKS;
import static com.ludwici.copperdeco.registry.BlockRegistry.COPPER_BLOCK_TILE;

@Mod(CopperAgeDecoration.MODID)
public class CopperAgeDecoration {
    public static final String MODID = "copperdeco";
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.copperdeco"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> COPPER_BLOCK_TILE.weathering().unaffected().get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> ALL_TILES.forEach(tiles -> tiles.forEach(block -> output.accept(block.get()))))
            .build());

    public CopperAgeDecoration(IEventBus modEventBus, ModContainer container) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
