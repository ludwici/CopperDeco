package com.ludwici.copperdeco;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.ludwici.copperdeco.registry.BlockRegistry.*;

@Mod(CopperAgeDecoration.MODID)
public class CopperAgeDecoration {
    public static final String MODID = "copperdeco";
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.copperdeco"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> COPPER_BLOCK_TILE.get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(COPPER_BLOCK_TILE.get());
                output.accept(CHISELED_COPPER_BLOCK_TILE.get());
                output.accept(COPPER_GRATE_TILE.get());
                output.accept(CUT_COPPER_TILE.get());

                output.accept(EXPOSED_COPPER_TILE.get());
                output.accept(EXPOSED_CHISELED_COPPER_TILE.get());
                output.accept(EXPOSED_COPPER_GRATE_TILE.get());
                output.accept(EXPOSED_CUT_COPPER_TILE.get());

                output.accept(WEATHERED_COPPER_TILE.get());
                output.accept(WEATHERED_CHISELED_COPPER_TILE.get());
                output.accept(WEATHERED_COPPER_GRATE_TILE.get());
                output.accept(WEATHERED_CUT_COPPER_TILE.get());

                output.accept(OXIDIZED_COPPER_TILE.get());
                output.accept(OXIDIZED_CHISELED_COPPER_TILE.get());
                output.accept(OXIDIZED_COPPER_GRATE_TILE.get());
                output.accept(OXIDIZED_CUT_COPPER_TILE.get());

                output.accept(WAXED_COPPER_BLOCK_TILE.get());
                output.accept(WAXED_CHISELED_COPPER_TILE.get());
                output.accept(WAXED_COPPER_GRATE_TILE.get());
                output.accept(WAXED_CUT_COPPER_TILE.get());

                output.accept(WAXED_EXPOSED_COPPER_TILE.get());
                output.accept(WAXED_EXPOSED_CHISELED_COPPER_TILE.get());
                output.accept(WAXED_EXPOSED_COPPER_GRATE_TILE.get());
                output.accept(WAXED_EXPOSED_CUT_COPPER_TILE.get());

                output.accept(WAXED_WEATHERED_COPPER_TILE.get());
                output.accept(WAXED_WEATHERED_CHISELED_COPPER_TILE.get());
                output.accept(WAXED_WEATHERED_COPPER_GRATE_TILE.get());
                output.accept(WAXED_WEATHERED_CUT_COPPER_TILE.get());

                output.accept(WAXED_OXIDIZED_COPPER_TILE.get());
                output.accept(WAXED_OXIDIZED_CHISELED_COPPER_TILE.get());
                output.accept(WAXED_OXIDIZED_COPPER_GRATE_TILE.get());
                output.accept(WAXED_OXIDIZED_CUT_COPPER_TILE.get());
            }).build());

    public CopperAgeDecoration(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
