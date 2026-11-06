package com.ludwici.copperdeco.registry;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.ludwici.carpetscore.api.block.CarpetVariantBlock;
import com.ludwici.copperdeco.block.BaseCopperTileBlock;
import com.ludwici.copperdeco.block.WaxedCopperTileBlock;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopperFullBlock;
import net.minecraft.world.level.block.WeatheringCopperGrateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

import static com.ludwici.copperdeco.CopperDeco.MODID;

public class BlockRegistry {
    public static void initialize() {
        OxidizableBlocksRegistry.registerNextStage(COPPER_BLOCK_TILE, EXPOSED_COPPER_TILE);
        OxidizableBlocksRegistry.registerNextStage(EXPOSED_COPPER_TILE, WEATHERED_COPPER_TILE);
        OxidizableBlocksRegistry.registerNextStage(WEATHERED_COPPER_TILE, OXIDIZED_COPPER_TILE);

        OxidizableBlocksRegistry.registerNextStage(CHISELED_COPPER_BLOCK_TILE, EXPOSED_CHISELED_COPPER_TILE);
        OxidizableBlocksRegistry.registerNextStage(EXPOSED_CHISELED_COPPER_TILE, WEATHERED_CHISELED_COPPER_TILE);
        OxidizableBlocksRegistry.registerNextStage(WEATHERED_CHISELED_COPPER_TILE, OXIDIZED_CHISELED_COPPER_TILE);

        OxidizableBlocksRegistry.registerNextStage(COPPER_GRATE_TILE, EXPOSED_COPPER_GRATE_TILE);
        OxidizableBlocksRegistry.registerNextStage(EXPOSED_COPPER_GRATE_TILE, WEATHERED_COPPER_GRATE_TILE);
        OxidizableBlocksRegistry.registerNextStage(WEATHERED_COPPER_GRATE_TILE, OXIDIZED_COPPER_GRATE_TILE);

        OxidizableBlocksRegistry.registerNextStage(CUT_COPPER_TILE, EXPOSED_CUT_COPPER_TILE);
        OxidizableBlocksRegistry.registerNextStage(EXPOSED_CUT_COPPER_TILE, WEATHERED_CUT_COPPER_TILE);
        OxidizableBlocksRegistry.registerNextStage(WEATHERED_CUT_COPPER_TILE, OXIDIZED_CUT_COPPER_TILE);

        OxidizableBlocksRegistry.registerWaxable(COPPER_BLOCK_TILE, WAXED_COPPER_BLOCK_TILE);
        OxidizableBlocksRegistry.registerWaxable(EXPOSED_COPPER_TILE, WAXED_EXPOSED_COPPER_TILE);
        OxidizableBlocksRegistry.registerWaxable(WEATHERED_COPPER_TILE, WAXED_WEATHERED_COPPER_TILE);
        OxidizableBlocksRegistry.registerWaxable(OXIDIZED_COPPER_TILE, WAXED_OXIDIZED_COPPER_TILE);

        OxidizableBlocksRegistry.registerWaxable(CHISELED_COPPER_BLOCK_TILE, WAXED_CHISELED_COPPER_TILE);
        OxidizableBlocksRegistry.registerWaxable(EXPOSED_CHISELED_COPPER_TILE, WAXED_EXPOSED_CHISELED_COPPER_TILE);
        OxidizableBlocksRegistry.registerWaxable(WEATHERED_CHISELED_COPPER_TILE, WAXED_WEATHERED_CHISELED_COPPER_TILE);
        OxidizableBlocksRegistry.registerWaxable(OXIDIZED_CHISELED_COPPER_TILE, WAXED_OXIDIZED_CHISELED_COPPER_TILE);

        OxidizableBlocksRegistry.registerWaxable(COPPER_GRATE_TILE, WAXED_COPPER_GRATE_TILE);
        OxidizableBlocksRegistry.registerWaxable(EXPOSED_COPPER_GRATE_TILE, WAXED_EXPOSED_COPPER_GRATE_TILE);
        OxidizableBlocksRegistry.registerWaxable(WEATHERED_COPPER_GRATE_TILE, WAXED_WEATHERED_COPPER_GRATE_TILE);
        OxidizableBlocksRegistry.registerWaxable(OXIDIZED_COPPER_GRATE_TILE, WAXED_OXIDIZED_COPPER_GRATE_TILE);

        OxidizableBlocksRegistry.registerWaxable(CUT_COPPER_TILE, WAXED_CUT_COPPER_TILE);
        OxidizableBlocksRegistry.registerWaxable(EXPOSED_CUT_COPPER_TILE, WAXED_EXPOSED_CUT_COPPER_TILE);
        OxidizableBlocksRegistry.registerWaxable(WEATHERED_CUT_COPPER_TILE, WAXED_WEATHERED_CUT_COPPER_TILE);
        OxidizableBlocksRegistry.registerWaxable(OXIDIZED_CUT_COPPER_TILE, WAXED_OXIDIZED_CUT_COPPER_TILE);
    }

    public static final Block COPPER_BLOCK_TILE = registerOxiBlock(Blocks.COPPER_BLOCK);
    public static final Block CHISELED_COPPER_BLOCK_TILE = registerOxiBlock(Blocks.CHISELED_COPPER);
    public static final Block COPPER_GRATE_TILE = registerOxiGrate(Blocks.COPPER_GRATE);
    public static final Block CUT_COPPER_TILE = registerOxiBlock(Blocks.CUT_COPPER);

    public static final Block EXPOSED_COPPER_TILE = registerOxiBlock(Blocks.EXPOSED_COPPER);
    public static final Block EXPOSED_CHISELED_COPPER_TILE = registerOxiBlock(Blocks.EXPOSED_CHISELED_COPPER);
    public static final Block EXPOSED_COPPER_GRATE_TILE = registerOxiGrate(Blocks.EXPOSED_COPPER_GRATE);
    public static final Block EXPOSED_CUT_COPPER_TILE = registerOxiBlock(Blocks.EXPOSED_CUT_COPPER);

    public static final Block WEATHERED_COPPER_TILE = registerOxiBlock(Blocks.WEATHERED_COPPER);
    public static final Block WEATHERED_CHISELED_COPPER_TILE = registerOxiBlock(Blocks.WEATHERED_CHISELED_COPPER);
    public static final Block WEATHERED_COPPER_GRATE_TILE = registerOxiGrate(Blocks.WEATHERED_COPPER_GRATE);
    public static final Block WEATHERED_CUT_COPPER_TILE = registerOxiBlock(Blocks.WEATHERED_CUT_COPPER);

    public static final Block OXIDIZED_COPPER_TILE = registerOxiBlock(Blocks.OXIDIZED_COPPER);
    public static final Block OXIDIZED_CHISELED_COPPER_TILE = registerOxiBlock(Blocks.OXIDIZED_CHISELED_COPPER);
    public static final Block OXIDIZED_COPPER_GRATE_TILE = registerOxiGrate(Blocks.OXIDIZED_COPPER_GRATE);
    public static final Block OXIDIZED_CUT_COPPER_TILE = registerOxiBlock(Blocks.OXIDIZED_CUT_COPPER);

    public static final Block WAXED_COPPER_BLOCK_TILE = registerBlock(Blocks.WAXED_COPPER_BLOCK);
    public static final Block WAXED_EXPOSED_COPPER_TILE = registerBlock(Blocks.WAXED_EXPOSED_COPPER);
    public static final Block WAXED_WEATHERED_COPPER_TILE = registerBlock(Blocks.WAXED_WEATHERED_COPPER);
    public static final Block WAXED_OXIDIZED_COPPER_TILE = registerBlock(Blocks.WAXED_OXIDIZED_COPPER);

    public static final Block WAXED_CHISELED_COPPER_TILE = registerBlock(Blocks.WAXED_CHISELED_COPPER);
    public static final Block WAXED_EXPOSED_CHISELED_COPPER_TILE = registerBlock(Blocks.WAXED_EXPOSED_CHISELED_COPPER);
    public static final Block WAXED_WEATHERED_CHISELED_COPPER_TILE = registerBlock(Blocks.WAXED_WEATHERED_CHISELED_COPPER);
    public static final Block WAXED_OXIDIZED_CHISELED_COPPER_TILE = registerBlock(Blocks.WAXED_OXIDIZED_CHISELED_COPPER);

    public static final Block WAXED_COPPER_GRATE_TILE = registerBlock(Blocks.WAXED_COPPER_GRATE);
    public static final Block WAXED_EXPOSED_COPPER_GRATE_TILE = registerBlock(Blocks.WAXED_EXPOSED_COPPER_GRATE);
    public static final Block WAXED_WEATHERED_COPPER_GRATE_TILE = registerBlock(Blocks.WAXED_WEATHERED_COPPER_GRATE);
    public static final Block WAXED_OXIDIZED_COPPER_GRATE_TILE = registerBlock(Blocks.WAXED_OXIDIZED_COPPER_GRATE);

    public static final Block WAXED_CUT_COPPER_TILE = registerBlock(Blocks.WAXED_CUT_COPPER);
    public static final Block WAXED_EXPOSED_CUT_COPPER_TILE = registerBlock(Blocks.WAXED_EXPOSED_CUT_COPPER);
    public static final Block WAXED_WEATHERED_CUT_COPPER_TILE = registerBlock(Blocks.WAXED_WEATHERED_CUT_COPPER);
    public static final Block WAXED_OXIDIZED_CUT_COPPER_TILE = registerBlock(Blocks.WAXED_OXIDIZED_CUT_COPPER);

    public static final ResourceKey<CreativeModeTab> CUSTOM_CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MODID, "creative_tab")
    );

    public static final CreativeModeTab CUSTOM_CREATIVE_TAB = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.copperdeco"))
            .icon(() -> COPPER_BLOCK_TILE.asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(COPPER_BLOCK_TILE);
                output.accept(CHISELED_COPPER_BLOCK_TILE);
                output.accept(COPPER_GRATE_TILE);
                output.accept(CUT_COPPER_TILE);

                output.accept(EXPOSED_COPPER_TILE);
                output.accept(EXPOSED_CHISELED_COPPER_TILE);
                output.accept(EXPOSED_COPPER_GRATE_TILE);
                output.accept(EXPOSED_CUT_COPPER_TILE);

                output.accept(WEATHERED_COPPER_TILE);
                output.accept(WEATHERED_CHISELED_COPPER_TILE);
                output.accept(WEATHERED_COPPER_GRATE_TILE);
                output.accept(WEATHERED_CUT_COPPER_TILE);

                output.accept(OXIDIZED_COPPER_TILE);
                output.accept(OXIDIZED_CHISELED_COPPER_TILE);
                output.accept(OXIDIZED_COPPER_GRATE_TILE);
                output.accept(OXIDIZED_CUT_COPPER_TILE);

                output.accept(WAXED_COPPER_BLOCK_TILE);
                output.accept(WAXED_CHISELED_COPPER_TILE);
                output.accept(WAXED_COPPER_GRATE_TILE);
                output.accept(WAXED_CUT_COPPER_TILE);

                output.accept(WAXED_EXPOSED_COPPER_TILE);
                output.accept(WAXED_EXPOSED_CHISELED_COPPER_TILE);
                output.accept(WAXED_EXPOSED_COPPER_GRATE_TILE);
                output.accept(WAXED_EXPOSED_CUT_COPPER_TILE);

                output.accept(WAXED_WEATHERED_COPPER_TILE);
                output.accept(WAXED_WEATHERED_CHISELED_COPPER_TILE);
                output.accept(WAXED_WEATHERED_COPPER_GRATE_TILE);
                output.accept(WAXED_WEATHERED_CUT_COPPER_TILE);

                output.accept(WAXED_OXIDIZED_COPPER_TILE);
                output.accept(WAXED_OXIDIZED_CHISELED_COPPER_TILE);
                output.accept(WAXED_OXIDIZED_COPPER_GRATE_TILE);
                output.accept(WAXED_OXIDIZED_CUT_COPPER_TILE);
            })
            .build();


    private static Block registerBlock(Block orig) {
        return registerSimpleBlock(orig, waxed(orig));
    }

    private static Block registerOxiBlock(Block orig) {
        return registerSimpleBlock(orig, oxiCube(orig));
    }

    private static Block registerOxiGrate(Block orig) {
        return registerSimpleBlock(orig, oxiGrate(orig));
    }

    private static <B extends Block> Block registerSimpleBlock(Block orig, Supplier<B> sup) {
        String identifier = BuiltInRegistries.BLOCK.getKey(orig).getPath() + "_tile";
        ResourceKey<Item> itemKey = keyOfItem(identifier);
        Block block = Registry.register(BuiltInRegistries.BLOCK, keyOfBlock(identifier), sup.get());
        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        return block;
    }

    private static <B extends Block> Supplier<BaseCopperTileBlock> oxiCube(B orig) {;
        return () -> new BaseCopperTileBlock(((WeatheringCopperFullBlock)(orig)).getAge(), BlockBehaviour.Properties.ofFullCopy(orig).forceSolidOn().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, getIdentifier(orig)))));
    }

    private static <B extends Block> Supplier<BaseCopperTileBlock> oxiGrate(B orig) {
        return () -> new BaseCopperTileBlock(((WeatheringCopperGrateBlock)(orig)).getAge(), BlockBehaviour.Properties.ofFullCopy(orig).forceSolidOn().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, getIdentifier(orig)))));
//        return () -> new BaseCopperTileBlock(((WeatheringCopperGrateBlock)(orig)).getAge(), BlockBehaviour.Properties.ofFullCopy(orig).forceSolidOn().setId(keyOfBlock(getIdentifier(orig))));
    }

    private static <B extends Block> Supplier<CarpetVariantBlock> variants(B orig) {
        return () -> new CarpetVariantBlock(BlockBehaviour.Properties.ofFullCopy(orig).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, getIdentifier(orig)))));
//        return () -> new CarpetVariantBlock(BlockBehaviour.Properties.ofFullCopy(orig).setId(keyOfBlock(getIdentifier(orig))));
    }

    private static <B extends Block> Supplier<WaxedCopperTileBlock> waxed(B orig) {
        return () -> new WaxedCopperTileBlock(BlockBehaviour.Properties.ofFullCopy(orig).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, getIdentifier(orig)))));
//        return () -> new WaxedCopperTileBlock(BlockBehaviour.Properties.ofFullCopy(orig).setId(keyOfBlock(getIdentifier(orig))));
    }

    private static String getIdentifier(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath() + "_tile";
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, name));
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, name));
    }
}
