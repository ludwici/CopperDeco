package com.ludwici.copperdeco.registry;

import com.blackgear.vanillabackport.common.registries.blocks.ModBlocks;
import com.google.common.base.Suppliers;
import com.ludwici.copperdeco.block.BaseCopperTileBlock;
import com.ludwici.copperdeco.block.WaxedCopperTileBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.ludwici.copperdeco.CopperDeco.MODID;

public final class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public record Family(List<RegistryObject<Block>> normal, List<RegistryObject<Block>> waxed) {
    }

    public static final List<Family> FAMILIES = List.of(
            registerFamily(
                    new String[]{"copper_block", "exposed_copper", "weathered_copper", "oxidized_copper"},
                    new String[]{"waxed_copper_block", "waxed_exposed_copper", "waxed_weathered_copper", "waxed_oxidized_copper"},
                    List.of(() -> Blocks.COPPER_BLOCK, () -> Blocks.EXPOSED_COPPER, () -> Blocks.WEATHERED_COPPER, () -> Blocks.OXIDIZED_COPPER),
                    List.of(() -> Blocks.WAXED_COPPER_BLOCK, () -> Blocks.WAXED_EXPOSED_COPPER, () -> Blocks.WAXED_WEATHERED_COPPER, () -> Blocks.WAXED_OXIDIZED_COPPER)
            ),
            registerFamily(
                    new String[]{"chiseled_copper", "exposed_chiseled_copper", "weathered_chiseled_copper", "oxidized_chiseled_copper"},
                    new String[]{"waxed_chiseled_copper", "waxed_exposed_chiseled_copper", "waxed_weathered_chiseled_copper", "waxed_oxidized_chiseled_copper"},
                    List.of(ModBlocks.CHISELED_COPPER, ModBlocks.EXPOSED_CHISELED_COPPER, ModBlocks.WEATHERED_CHISELED_COPPER, ModBlocks.OXIDIZED_CHISELED_COPPER),
                    List.of(ModBlocks.WAXED_CHISELED_COPPER, ModBlocks.WAXED_EXPOSED_CHISELED_COPPER, ModBlocks.WAXED_WEATHERED_CHISELED_COPPER, ModBlocks.WAXED_OXIDIZED_CHISELED_COPPER)
            ),
            registerFamily(
                    new String[]{"copper_grate", "exposed_copper_grate", "weathered_copper_grate", "oxidized_copper_grate"},
                    new String[]{"waxed_copper_grate", "waxed_exposed_copper_grate", "waxed_weathered_copper_grate", "waxed_oxidized_copper_grate"},
                    List.of(ModBlocks.COPPER_GRATE, ModBlocks.EXPOSED_COPPER_GRATE, ModBlocks.WEATHERED_COPPER_GRATE, ModBlocks.OXIDIZED_COPPER_GRATE),
                    List.of(ModBlocks.WAXED_COPPER_GRATE, ModBlocks.WAXED_EXPOSED_COPPER_GRATE, ModBlocks.WAXED_WEATHERED_COPPER_GRATE, ModBlocks.WAXED_OXIDIZED_COPPER_GRATE)
            ),
            registerFamily(
                    new String[]{"cut_copper", "exposed_cut_copper", "weathered_cut_copper", "oxidized_cut_copper"},
                    new String[]{"waxed_cut_copper", "waxed_exposed_cut_copper", "waxed_weathered_cut_copper", "waxed_oxidized_cut_copper"},
                    List.of(() -> Blocks.CUT_COPPER, () -> Blocks.EXPOSED_CUT_COPPER, () -> Blocks.WEATHERED_CUT_COPPER, () -> Blocks.OXIDIZED_CUT_COPPER),
                    List.of(() -> Blocks.WAXED_CUT_COPPER, () -> Blocks.WAXED_EXPOSED_CUT_COPPER, () -> Blocks.WAXED_WEATHERED_CUT_COPPER, () -> Blocks.WAXED_OXIDIZED_CUT_COPPER)
            )
    );

    public static final List<RegistryObject<Block>> ALL_BLOCKS = FAMILIES.stream()
            .flatMap(family -> java.util.stream.Stream.concat(family.normal().stream(), family.waxed().stream()))
            .toList();

    public static final Supplier<Map<Block, Block>> NEXT = Suppliers.memoize(() -> {
        Map<Block, Block> mapping = new IdentityHashMap<>();
        for (Family family : FAMILIES) {
            for (int index = 0; index < 3; index++) {
                mapping.put(family.normal().get(index).get(), family.normal().get(index + 1).get());
            }
        }
        return mapping;
    });

    public static final Supplier<Map<Block, Block>> PREVIOUS = Suppliers.memoize(() -> {
        Map<Block, Block> mapping = new IdentityHashMap<>();
        NEXT.get().forEach((from, to) -> mapping.put(to, from));
        return mapping;
    });

    public static final Supplier<Map<Block, Block>> WAXABLES = Suppliers.memoize(() -> {
        Map<Block, Block> mapping = new IdentityHashMap<>();
        for (Family family : FAMILIES) {
            for (int index = 0; index < 4; index++) {
                mapping.put(family.normal().get(index).get(), family.waxed().get(index).get());
            }
        }
        return mapping;
    });

    public static final Supplier<Map<Block, Block>> UNWAX = Suppliers.memoize(() -> {
        Map<Block, Block> mapping = new IdentityHashMap<>();
        WAXABLES.get().forEach((from, to) -> mapping.put(to, from));
        return mapping;
    });

    public static final RegistryObject<CreativeModeTab> TAB = TABS.register("tiles", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.copperdeco"))
            .icon(() -> FAMILIES.get(0).normal().get(0).get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> ALL_BLOCKS.forEach(block -> output.accept(block.get())))
            .build());

    private static Family registerFamily(String[] normalNames, String[] waxedNames, List<Supplier<Block>> normalSources, List<Supplier<Block>> waxedSources) {
        List<RegistryObject<Block>> normal = new ArrayList<>();
        List<RegistryObject<Block>> waxed = new ArrayList<>();
        WeatheringCopper.WeatherState[] stages = WeatheringCopper.WeatherState.values();

        for (int index = 0; index < 4; index++) {
            final int stage = index;
            normal.add(register(normalNames[index] + "_tile", () -> new BaseCopperTileBlock(stages[stage], BlockBehaviour.Properties.copy(normalSources.get(stage).get()).noOcclusion())));
            waxed.add(register(waxedNames[index] + "_tile", () -> new WaxedCopperTileBlock(BlockBehaviour.Properties.copy(waxedSources.get(stage).get()).noOcclusion())));
        }

        return new Family(List.copyOf(normal), List.copyOf(waxed));
    }

    private static RegistryObject<Block> register(String name, Supplier<Block> factory) {
        RegistryObject<Block> result = BLOCKS.register(name, factory);
        ITEMS.register(name, () -> new BlockItem(result.get(), new Item.Properties()));
        return result;
    }

    private BlockRegistry() {
    }
}
